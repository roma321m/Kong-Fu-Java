package dorin_roman.app.kongfujava.screens.level.tutorial

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.LevelLogic
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.repository.LevelRepository
import dorin_roman.app.kongfujava.domain.models.Level
import dorin_roman.app.kongfujava.domain.models.Question
import dorin_roman.app.kongfujava.screens.level.LevelEvent
import dorin_roman.app.kongfujava.screens.level.levels_map.LevelsMapViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TutorialViewModel @Inject constructor(
    private val levelRepository: LevelRepository,
): ViewModel() {

    companion object {
        const val TAG = "TutorialViewModel"
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


    fun handle(event: LevelEvent) {
        when (event) {
            is LevelEvent.InitLevel -> initLevels(event.levelId)
            is LevelEvent.UpdateLevelScore -> {}
            is LevelEvent.UpdateLevelState -> {}
        }
    }

    private fun initLevels(levelId: Int) {
        currentLevelId = levelId
        getQuestion()
        getLevel()
    }

    private fun getQuestion() {
        Log.d(LevelsMapViewModel.TAG, "getQuestion")
        question.value = RequestState.Loading
        try {
            viewModelScope.launch {
                levelRepository.getQuestion(currentLevelId).collect { question ->
                    this@TutorialViewModel.question.value = RequestState.Success(question)
                    title.value = question.title
                    questionTitle.value = question.question
                }
            }
        } catch (e: Exception) {
            question.value = RequestState.Error(e)
        }
    }

    private fun getLevel() {
        Log.d(LevelsMapViewModel.TAG, "getLevel")
        currentLevel.value = RequestState.Loading
        try {
            viewModelScope.launch {
                levelRepository.getLevel(currentLevelId).collect { level ->
                    this@TutorialViewModel.currentLevel.value = RequestState.Success(level)
                    score.value = level.score
                    state.value = LevelLogic.getLevelState(level.state)
                }
            }
        } catch (e: Exception) {
            question.value = RequestState.Error(e)
        }
    }





}