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

    var type by mutableStateOf(LevelType.TUTORIAL)
        private set

    var hint by mutableStateOf(0)
        private set

    var mistakes by mutableStateOf(0)
        private set

    private var currentWorldId: Int = -1

    private var currentLevelId: Int = -1

    private val currentLevel = MutableStateFlow<RequestState<Level>>(RequestState.Idle)

    private val question = MutableStateFlow<RequestState<Question>>(RequestState.Idle)


    fun handle(event: LevelEvent) {
        when (event) {
            LevelEvent.FinishLevel -> {
                updateScore()
                updateState()
            }

            is LevelEvent.InitLevel -> initLevels(event.levelId, event.worldId)

            is LevelEvent.UpdateLevelScore -> {
                updateScore()
            }

            is LevelEvent.UpdateLevelState -> {
                updateState()
            }

            is LevelEvent.UpdateLevelMistakes -> {
                updateMistakes()
            }

            is LevelEvent.UpdateLevelHint -> {
                updateHint()
            }

            else -> {}
        }
    }

    private fun initLevels(levelId: Int, worldId: Int) {
        Log.d(TAG, "initLevels")
        currentLevelId = levelId
        currentWorldId = worldId
        hint = 0
        mistakes = 0
        loadQuestion()
        loadLevel()
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
                type = LevelLogic.getLevelType(level.type)
                state = LevelLogic.getLevelState(level.state)
            }
        } catch (e: Exception) {
            question.value = RequestState.Error(e)
            Log.e(TAG, "${e.message}")
        }
    }


    private fun updateScore() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "updateScore")
        score = if (type != LevelType.TUTORIAL) {
            LevelLogic.getScore(hint, mistakes)
        } else {
            6
        }
        levelRepository.updateScore(currentLevelId, score)
    }

    private fun updateState() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "updateState")
        state = LevelLogic.getState(score)
        levelRepository.updateState(currentLevelId, state.ordinal)
        //fixme when last level need to open new world
        levelRepository.updateState(currentLevelId + 1, PointState.ZERO.ordinal)
    }

    private fun updateHint() {
        Log.d(TAG, "updateHint $hint")
        if (hint == 3)
            return

        hint += 1
    }

    private fun updateMistakes() {
        Log.d(TAG, "updateMistake $mistakes")
        if (mistakes < 3) {
            mistakes += 1
        }
    }

    private fun updateStatistics() {

    }

}