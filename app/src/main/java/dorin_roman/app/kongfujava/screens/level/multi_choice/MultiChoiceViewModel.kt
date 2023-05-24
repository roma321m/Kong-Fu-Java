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

    var shownHints by mutableStateOf(listOf<String>())
        private set

    var hints by mutableStateOf(listOf<String>())
        private set
    var hintCount by mutableStateOf(0)
        private set

    var hint by mutableStateOf("")
        private set

    fun handle(event: MultiEvent) {
        //fixme
        when (event) {
            is MultiEvent.InitAnswers -> initAnswers(event.levelId)
            is MultiEvent.GetHint -> { getHint() }
        }
    }

    private fun initAnswers(levelId: Int) {
        currentLevelId = levelId
        hintCount = 0
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
                qAnswers.add(answer.right)
                answers = qAnswers

                buildHints()
            }
        } catch (e: Exception) {
            answer.value = RequestState.Error(e)
        }
    }

    private fun buildHints() {
        val qHints = mutableListOf<String>()
        for (i in 0..3){
            if(answers[i] != answers[answers.size-1]){
                qHints.add(answers[i])
            }
        }
       hints = qHints
    }

    private fun getHint() {
        hint = hints.random()
        hints.toMutableList().remove(hint)
        shownHints.toMutableList().add(hint)
    }


}