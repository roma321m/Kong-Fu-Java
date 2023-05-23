package dorin_roman.app.kongfujava.screens.level.drag_drop

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.LevelLogic
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.repository.LevelRepository
import dorin_roman.app.kongfujava.domain.models.Answer
import dorin_roman.app.kongfujava.domain.models.Question
import dorin_roman.app.kongfujava.screens.level.LevelEvent
import dorin_roman.app.kongfujava.screens.level.levels_map.LevelsMapViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DragDropViewModel @Inject constructor(
    private val levelRepository: LevelRepository,
) : ViewModel() {

    companion object {
        const val TAG = "DragDropViewModel"
    }

    private var currentLevel: Int = -1

    private val question = MutableStateFlow<RequestState<Question>>(RequestState.Idle)

    private val answer = MutableStateFlow<RequestState<Answer>>(RequestState.Idle)

    var isCurrentlyDragging by mutableStateOf(false)
        private set

    var drag by mutableStateOf(emptyList<String>())
        private set

    var drop by mutableStateOf(emptyList<String>())
        private set

    init {
//        drag = listOf(
//            AnswerItem("A"),
//            AnswerItem("B"),
//            AnswerItem("C"),
//            AnswerItem("D")
//        )
//
//        drop = listOf(
//            AnswerItem(""),
//            AnswerItem(""),
//            AnswerItem(""),
//            AnswerItem("")
//        )

        drag = listOf(
           "A",
           "B",
           "C",
           "D"
        )

        drop = listOf(
            "",
            "",
            "",
            "",
        )
    }

    fun handle(event: LevelEvent) {
        when (event) {
            is LevelEvent.InitLevel -> initLevels(event.levelId)
            is LevelEvent.UpdateLevelScore -> {}
            is LevelEvent.UpdateLevelState -> {}
        }
    }

    private fun initLevels(levelId: Int) {
        currentLevel = levelId
        getQuestion()
        getAnswer()
    }

    private fun getQuestion() {
        Log.d(LevelsMapViewModel.TAG, "getQuestion")
        question.value = RequestState.Loading
        try {
            viewModelScope.launch {
                levelRepository.getQuestion(currentLevel).collect { question ->
                    this@DragDropViewModel.question.value = RequestState.Success(question)
                }
            }
        } catch (e: Exception) {
            question.value = RequestState.Error(e)
        }
    }

    private fun getAnswer() {
        Log.d(LevelsMapViewModel.TAG, "getAnswer")
        answer.value = RequestState.Loading
        try {
            viewModelScope.launch {
                levelRepository.getAnswer(currentLevel).collect { answer ->
                    this@DragDropViewModel.answer.value = RequestState.Success(answer)
                    getDrag(answer)
                }
            }
        } catch (e: Exception) {
            answer.value = RequestState.Error(e)
        }
    }

    private fun getDrag(answer: Answer) {
        drag = listOf(
            answer.answer1,
            answer.answer2,
            answer.answer3,
            answer.answer4,
        )
    }

    fun startDragging(){
        isCurrentlyDragging = true
    }

    fun stopDragging(){
        isCurrentlyDragging = false
    }

    fun checkAnswer(answer: String?){
        Log.d(TAG, "checkAnswer")
    }
}