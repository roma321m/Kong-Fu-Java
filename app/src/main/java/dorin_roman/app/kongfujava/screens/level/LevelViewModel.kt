package dorin_roman.app.kongfujava.screens.level

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.LevelLogic
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.repository.ChildIdRepository
import dorin_roman.app.kongfujava.data.repository.LevelRepository
import dorin_roman.app.kongfujava.domain.models.child_stats.ActiveTime
import dorin_roman.app.kongfujava.domain.models.child_stats.LevelStats
import dorin_roman.app.kongfujava.domain.models.levels.Level
import dorin_roman.app.kongfujava.domain.models.levels.question.Question
import dorin_roman.app.kongfujava.domain.repository.ChildStatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt


@HiltViewModel
class LevelViewModel @Inject constructor(
    private val levelRepository: LevelRepository,
    private val childStatsRepository: ChildStatsRepository,
    private val childIdRepository: ChildIdRepository
) : ViewModel() {

    companion object {
        const val TAG = "LevelViewModel"
    }

    var title by mutableStateOf("")
        private set

    var questionTitle by mutableStateOf("")
        private set

    var score by mutableStateOf(0)
        private set

    var state by mutableStateOf(PointState.LOCK)
        private set

    var isExit by mutableStateOf(false)
        private set

    private var currentWorldId: Int = -1

    private var currentLevelId: Int = -1

    private var currentLevel = MutableStateFlow<RequestState<Level>>(RequestState.Idle)

    private var nextLevel = Level(-1, 0, 0, 0, 0, 0)

    private var question = MutableStateFlow<RequestState<Question>>(RequestState.Idle)

    private var currentLevelStats = LevelStats()

    private var startTime: Long = 0

    private var childId = ""

    private var levelNumber = 0

    private val currentTime get() = System.currentTimeMillis()

    init {
        readChildId()
    }

    fun handle(event: LevelEvent) {
        when (event) {
            is LevelEvent.InitLevel -> initLevels(event.levelId, event.worldId)
            is LevelEvent.UpdateLevelScore -> updateScore(event.hintCount, event.mistakesCount)
            is LevelEvent.UpdateLevelState -> updateState()
            is LevelEvent.HandleExit -> isExit = !isExit
        }
    }

    private fun initLevels(levelId: Int, worldId: Int) {
        Log.d(TAG, "initLevels")
        resetVariables()
        currentLevelId = levelId
        currentWorldId = worldId
        loadQuestion()
        loadLevel()
        loadNextLevel()
        if (childId.isNotBlank() && currentLevelId != -1) {
            readLevelStats(childId)
        }
    }

    private fun resetVariables() {
        title = ""
        questionTitle = ""
        score = 0
        state = PointState.LOCK
        isExit = false
        currentWorldId = -1
        currentLevelId = -1
        currentLevel = MutableStateFlow(RequestState.Idle)
        question = MutableStateFlow(RequestState.Idle)
        startTime = currentTime
        currentLevelStats = LevelStats()
        levelNumber = 0
    }

    private fun loadQuestion() = viewModelScope.launch {
        Log.d(TAG, "loadQuestion")
        question.value = RequestState.Loading
        try {
            levelRepository.getQuestion(currentLevelId).collect { question ->
                this@LevelViewModel.question.value = RequestState.Success(question)
                title = question.title
                questionTitle = question.question
            }
        } catch (e: Exception) {
            question.value = RequestState.Error(e)
            Log.e(TAG, "${e.message}")
        }
    }


    private fun loadLevel() = viewModelScope.launch {
        Log.d(TAG, "loadLevel")
        currentLevel.value = RequestState.Loading
        try {
            levelRepository.getLevel(currentLevelId).collect { level ->
                this@LevelViewModel.currentLevel.value = RequestState.Success(level)
                levelNumber = level.number
                score = level.score
                state = LevelLogic.getLevelState(level.state)
            }
        } catch (e: Exception) {
            currentLevel.value = RequestState.Error(e)
            Log.e(TAG, "${e.message}")
        }
    }

    private fun loadNextLevel() = viewModelScope.launch {
        Log.d(TAG, "loadNextLevel")
        try {
            levelRepository.getLevel(currentLevelId + 1).collect { level ->
                if (level != null) {
                    nextLevel = level
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "${e.message}")
        }
    }


    private fun updateScore(
        hintCount: Int,
        mistakesCount: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "updateScore")
        score = LevelLogic.getLevelScore(hintCount, mistakesCount)
        levelRepository.updateLevelScore(currentLevelId, score)
        updateState() // room
        updateStatistics(
            helps = hintCount,
            mistakes = mistakesCount,
            stars = score
        ) // firebase
    }

    private fun updateState() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "updateState")
        state = LevelLogic.getLevelStateByScore(score)
        levelRepository.updateLevelState(currentLevelId, state.ordinal)

        if (nextLevel.id != -1) {
            if (nextLevel.state == PointState.LOCK.ordinal) {
                levelRepository.updateLevelState(nextLevel.id, PointState.ZERO.ordinal)
            }
            // if (nextLevel.worldId != currentWorldId) // fixme - open next world
        }
    }

    private fun readChildId() = viewModelScope.launch {
        Log.d(TAG, "readChildId")
        try {
            childIdRepository.readChildId
                .map { it }
                .collect {
                    childId = it
                    if (it.isNotBlank() && currentLevelId != -1) {
                        readLevelStats(it)
                    }
                }
        } catch (exception: Exception) {
            Log.e(TAG, "${exception.message}")
        }
    }

    private fun readLevelStats(id: String) = viewModelScope.launch {
        Log.d(TAG, "readLevelStats")
        childStatsRepository.getLevelStats(
            childId = id,
            levelId = currentLevelId.toString()
        ).also { response ->
            if (response is RequestState.Success) {
                Log.d(TAG, "readLevelStats: ${response.data}")
                currentLevelStats = if (response.data.id == -1) {
                    LevelStats(
                        id = currentLevelId,
                        worldId = currentWorldId
                    )
                } else {
                    response.data
                }
            } else if (response is RequestState.Error) {
                response.apply {
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun updateStatistics(helps: Int, mistakes: Int, stars: Int) {
        Log.d(TAG, "updateStatistics")
        if (childId.isBlank() || currentLevelId == -1) {
            return
        }

        val endTime = currentTime
        addActiveTime(endTime)
        addLevelStats(helps, mistakes, stars, endTime)
    }

    private fun addActiveTime(endTime: Long) = viewModelScope.launch {
        Log.d(TAG, "addActiveTime")
        childStatsRepository.addActiveTime(
            childId = childId,
            activeTime = ActiveTime(
                fromInMilli = startTime,
                toInMilli = endTime,
            )
        ).also { response ->
            if (response is RequestState.Success) {
                Log.d(TAG, "Active Time added")
            } else if (response is RequestState.Error) {
                response.apply {
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun addLevelStats(helps: Int, mistakes: Int, stars: Int, endTime: Long) =
        viewModelScope.launch {
            Log.d(TAG, "addLevelStats")
            currentLevelStats.number = levelNumber
            currentLevelStats.help += helps
            currentLevelStats.mistakes += mistakes
            currentLevelStats.stars = stars
            currentLevelStats.attempts++
            val timeInMinutes = ((endTime - startTime).toFloat() / 60_000f).roundToInt()
            currentLevelStats.timeInMinutes += if (timeInMinutes != 0) {
                timeInMinutes
            } else {
                1
            }

            childStatsRepository.addLevelStats(
                childId = childId,
                levelStats = currentLevelStats
            ).also { response ->
                if (response is RequestState.Success) {
                    Log.d(TAG, "Level Stats added")
                } else if (response is RequestState.Error) {
                    response.apply {
                        Log.e(TAG, "${error.message}")
                    }
                }
            }
        }

}