package dorin_roman.app.kongfujava.screens.level

import android.util.Log
import androidx.compose.runtime.mutableStateOf
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

    private var currentLevelId: Int = -1

    private val currentLevel = MutableStateFlow<RequestState<Level>>(RequestState.Idle)

    val question = MutableStateFlow<RequestState<Question>>(RequestState.Idle)

    var title = mutableStateOf("")
        private set

    var questionTitle = mutableStateOf("")
        private set

    var score = mutableStateOf(0)
        private set

    var state = mutableStateOf(PointState.LOCK)
        private set

    var type = mutableStateOf(LevelType.TUTORIAL)
        private set

    var hint = mutableStateOf(0)
        private set

    var mistakes = mutableStateOf(0)
        private set

    fun handle(event: LevelEvent) {
        when (event) {
            LevelEvent.FinishLevel -> {
                updateScore()
                updateState()
            }

            is LevelEvent.InitLevel -> initLevels(event.levelId)
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
        }
    }

    private fun initLevels(levelId: Int) {
        Log.d(TAG, "initLevels")
        currentLevelId = levelId
        getQuestion()
        getLevel()
    }

    private fun getQuestion() {
        Log.d(TAG, "getQuestion")
        question.value = RequestState.Loading
        viewModelScope.launch {
            try {
                levelRepository.getQuestion(currentLevelId).collect { question ->
                    this@LevelViewModel.question.value = RequestState.Success(question)
                    title.value = question.title
                    questionTitle.value = question.question
                }
            } catch (e: Exception) {
                question.value = RequestState.Error(e)
            }
        }
    }

    private fun getLevel() {
        Log.d(TAG, "getLevel")
        currentLevel.value = RequestState.Loading
        viewModelScope.launch {
            try {
                levelRepository.getLevel(currentLevelId).collect { level ->
                    this@LevelViewModel.currentLevel.value = RequestState.Success(level)
                    score.value = level.score
                    type.value = LevelLogic.getLevelType(level.type)
                    state.value = LevelLogic.getLevelState(level.state)
                }
            } catch (e: Exception) {
                question.value = RequestState.Error(e)
            }
        }
    }

    private fun updateScore() {
        Log.d(TAG, "updateScore")
        if (type.value.ordinal != 0) {
            score.value = LevelLogic.getScore(hint.value, mistakes.value)
        } else {
            score.value = 6
        }
        viewModelScope.launch(Dispatchers.IO) {
            levelRepository.updateScore(currentLevelId, score.value)
        }
    }

    private fun updateState() {
        Log.d(TAG, "updateState")
        LevelLogic.getState(score.value)
        viewModelScope.launch(Dispatchers.IO) {
            levelRepository.updateState(currentLevelId, state.value.ordinal)
            levelRepository.updateState(currentLevelId + 1, PointState.ZERO.ordinal)
        }
    }

    private fun updateHint() {
        Log.d(TAG, "updateHint ${hint.value}")
        if (hint.value == 3)
            return

        hint.value += 1
    }

    private fun updateMistakes() {
        Log.d(TAG, "updateMistake ${mistakes.value}")
        if (mistakes.value < 3) {
            mistakes.value += 1
        }
    }

}