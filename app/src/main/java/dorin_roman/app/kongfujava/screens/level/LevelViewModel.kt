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
import dorin_roman.app.kongfujava.data.repository.LevelRepository
import dorin_roman.app.kongfujava.domain.models.levels.Level
import dorin_roman.app.kongfujava.domain.models.levels.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelViewModel @Inject constructor(
    private val levelRepository: LevelRepository
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

//    var type by mutableStateOf(LevelType.TUTORIAL)
//        private set

    var isExit by mutableStateOf(false)
        private set

    private var currentWorldId: Int = -1

    private var currentLevelId: Int = -1

    private var currentLevel = MutableStateFlow<RequestState<Level>>(RequestState.Idle)

    private var question = MutableStateFlow<RequestState<Question>>(RequestState.Idle)


    fun handle(event: LevelEvent) {
        when (event) {
            is LevelEvent.InitLevel -> initLevels(event.levelId, event.worldId)

            is LevelEvent.UpdateLevelScore -> {
                updateScore(event.hintCount, event.mistakesCount)
            }

            is LevelEvent.UpdateLevelState -> {
                updateState()
            }

            is LevelEvent.HandleExit -> {
                isExit = !isExit
            }

        }
    }

    private fun initLevels(levelId: Int, worldId: Int) {
        Log.d(TAG, "initLevels")
        resetVariables()
        currentLevelId = levelId
        currentWorldId = worldId
        loadQuestion()
        loadLevel()
    }

    private fun resetVariables(){
        title = ""
        questionTitle = ""
        score = 0
        state = PointState.LOCK
        isExit = false
        currentWorldId = -1
        currentLevelId = -1
        currentLevel = MutableStateFlow(RequestState.Idle)
        question = MutableStateFlow(RequestState.Idle)
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
                score = level.score
                //type = LevelLogic.getLevelType(level.type)
                state = LevelLogic.getLevelState(level.state)
            }
        } catch (e: Exception) {
            currentLevel.value = RequestState.Error(e)
            Log.e(TAG, "${e.message}")
        }
    }


    private fun updateScore(hintCount: Int, mistakesCount: Int) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "updateScore")
        score = LevelLogic.getScore(hintCount, mistakesCount)
        levelRepository.updateScore(currentLevelId, score)
        updateState()
    }

    private fun updateState() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "updateState")
        state = LevelLogic.getState(score)
        levelRepository.updateState(currentLevelId, state.ordinal)
        //fixme when last level need to open new world
        levelRepository.updateState(currentLevelId + 1, PointState.ZERO.ordinal)
    }

    private fun updateStatistics() {
        //todo
        // fixme - remove
//        fun addActiveTime() = viewModelScope.launch {
//            Log.d(TAG, "addActiveTime")
//            childStatsRepository.addActiveTime(
//                childId = selectedStudent.id,
//                activeTime = ActiveTime(
//                    fromInMilli = currentTime,
//                    toInMilli = currentTime + 120_000,
//                )
//            ).also { response ->
//                if (response is RequestState.Success) {
//                    // added
//                } else if (response is RequestState.Error) {
//                    response.apply {
//                        toastLauncher.launch(SupervisorToast.SomethingWentWrong)
//                        Log.e(TAG, "${error.message}")
//                    }
//                }
//            }
//        }
//
//        // fixme - remove
//        fun addLevelStats() = viewModelScope.launch {
//            Log.d(TAG, "addLevelStats")
//            childStatsRepository.addLevelStats(
//                childId = selectedStudent.id,
//                levelStats = LevelStats(
//                    id = 2,
//                    world = "Variables",
//                    number = 1,
//                    stars = 2,
//                    timeInMinutes = 5,
//                    help = 0,
//                    attempts = 1,
//                    mistakes = 0
//                )
//            ).also { response ->
//                if (response is RequestState.Success) {
//                    // added
//                } else if (response is RequestState.Error) {
//                    response.apply {
//                        toastLauncher.launch(SupervisorToast.SomethingWentWrong)
//                        Log.e(TAG, "${error.message}")
//                    }
//                }
//            }
//        }
    }

}