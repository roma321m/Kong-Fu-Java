package dorin_roman.app.kongfujava.screens.level.multi_choice

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.repository.LevelRepository
import dorin_roman.app.kongfujava.domain.models.levels.Answer
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

    private var currentLevelId: Int = -1

    val answer = MutableStateFlow<RequestState<Answer>>(RequestState.Idle)

    var answers by mutableStateOf(listOf<String>())
        private set

    var right by mutableStateOf("")
        private set

    var isRight by mutableStateOf<Boolean?>(null)
        private set

    var shownHints by mutableStateOf(emptyList<String>())
        private set

    var hints by mutableStateOf(listOf<String>())
        private set

    private var hint by mutableStateOf("")

    fun handle(event: MultiEvent) {
        //fixme
        when (event) {
            is MultiEvent.InitAnswers -> initAnswers(event.levelId)
            is MultiEvent.GetHint -> { getHint() }
            is MultiEvent.CheckAnswer -> { checkAnswer(event.answer) }
        }
    }

    private fun initAnswers(levelId: Int) {
        currentLevelId = levelId
        shownHints = emptyList()
        hint = ""
        isRight = null
        loadAnswers()
    }

    private fun loadAnswers() = viewModelScope.launch {
        Log.d(TAG, "loadAnswers")
        answer.value = RequestState.Loading
        try {
            levelRepository.getAnswer(currentLevelId).collect { answer ->
                this@MultiChoiceViewModel.answer.value = RequestState.Success(answer)
                val qAnswers = mutableListOf<String>()
                qAnswers.add(answer.answer1)
                qAnswers.add(answer.answer2)
                qAnswers.add(answer.answer3)
                qAnswers.add(answer.answer4)
                answers = qAnswers.shuffled()
                right = answer.right
                buildHints()
            }
        } catch (e: Exception) {
            answer.value = RequestState.Error(e)
        }
    }

    private fun buildHints() {
        Log.d(TAG, "buildHints")
        val qHints = mutableListOf<String>()
        for (i in 0..3){
            if(answers[i] != right){
                qHints.add(answers[i])
            }
        }
       hints = qHints
    }

    private fun getHint() {
        Log.d(TAG, "getHint")
        hint = hints.random()
        val qHints = shownHints.toMutableList()
        qHints.add(hint)
        hints = hints.filter { it != hint }
        shownHints = qHints
        Log.d(TAG, "shownHints ${shownHints.size}")
    }

    private fun checkAnswer(answer: String) {
        isRight = answer == right
    }

}