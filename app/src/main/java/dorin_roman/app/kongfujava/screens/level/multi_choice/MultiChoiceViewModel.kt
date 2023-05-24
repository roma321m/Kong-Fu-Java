package dorin_roman.app.kongfujava.screens.level.multi_choice

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
class MultiChoiceViewModel @Inject constructor(
    private val levelRepository: LevelRepository,
) : ViewModel() {

    companion object {
        const val TAG = "MultiChoiceViewModel"
    }

    private var currentLevel: Int = -1

    val question = MutableStateFlow<RequestState<Question>>(RequestState.Idle)

    val answer = MutableStateFlow<RequestState<Answer>>(RequestState.Idle)

    var title = mutableStateOf("")
        private set

    var questionTitle = mutableStateOf("")
        private set

    var answers = mutableStateListOf<String>()
        private set

    fun handle(event: LevelEvent) {
        when (event) {
            is LevelEvent.InitLevel -> initLevels(event.levelId)
            else -> {}
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
                    this@MultiChoiceViewModel.question.value = RequestState.Success(question)
                    title.value = question.title
                    questionTitle.value = question.question
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
                    this@MultiChoiceViewModel.answer.value = RequestState.Success(answer)
                    answers.clear()
                    answers.add(answer.answer1)
                    answers.add(answer.answer2)
                    answers.add(answer.answer3)
                    answers.add(answer.answer4)
                    answers.add(answer.right.toString())
                }
            }
        } catch (e: Exception) {
            answer.value = RequestState.Error(e)
        }
    }

}