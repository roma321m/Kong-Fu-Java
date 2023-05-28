package dorin_roman.app.kongfujava.screens.level.drag_drop

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.repository.LevelRepository
import dorin_roman.app.kongfujava.domain.models.levels.answer.Answer
import dorin_roman.app.kongfujava.screens.level.multi_choice.components.ColorState
import dorin_roman.app.kongfujava.ui.toast.ToastLauncher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DragDropViewModel @Inject constructor(
    private val levelRepository: LevelRepository,
    private val toastLauncher: ToastLauncher
) : ViewModel() {

    companion object {
        const val TAG = "DragDropViewModel"
    }

    var isCurrentlyDragging by mutableStateOf(false)
        private set

    var drag by mutableStateOf(emptyList<String>())
        private set

    var dragButtonColors by mutableStateOf(listOf(ColorState.REGULAR, ColorState.REGULAR, ColorState.REGULAR, ColorState.REGULAR))
        private set

    var dropButtonColors by mutableStateOf(listOf(ColorState.DROP, ColorState.DROP, ColorState.DROP, ColorState.DROP))
        private set

    var drop by mutableStateOf(emptyList<String>())
        private set

    var answers by mutableStateOf(emptyList<String>())
        private set

    var questionLeft by mutableStateOf("")
        private set

    var questionRight by mutableStateOf("")
        private set

    var isFinish by mutableStateOf(false)
        private set

    var mistakesCount by mutableStateOf(0)
        private set

    var hintCount by mutableStateOf(0)
        private set

    private var hints by mutableStateOf(listOf<String>())

    private var hint by mutableStateOf("")

    private var isRight by mutableStateOf(listOf(false, false, false, false))

    private var currentLevel: Int = -1

    private val answer = MutableStateFlow<RequestState<Answer>>(RequestState.Idle)

    fun handle(event: DragDropEvent) {
        when (event) {
            is DragDropEvent.InitAnswers -> initAnswers(event.levelId)
            is DragDropEvent.UpdateQuestion -> updateQuestion(event.question)
            DragDropEvent.CheckAnswer -> checkAnswer()
            DragDropEvent.GetHint -> getHint()
            is DragDropEvent.UpdateDragging -> updateDragging(event.draggingStatus)
            is DragDropEvent.SetAnswer -> setAnswer(event.answer, event.index)
            is DragDropEvent.DeleteAnswer -> deleteAnswer(event.answer, event.index)
        }
    }

    private fun initAnswers(levelId: Int) {
        Log.d(TAG, "initAnswers")
        currentLevel = levelId
        loadAnswer()
    }

    private fun updateQuestion(question: String) {
        Log.d(TAG, "updateQuestion")
        val array = question.split("<")
        if (array.size == 2) {
            questionLeft = array.first()
            questionRight = array.last()
        }
    }

    private fun loadAnswer() {
        Log.d(TAG, "loadAnswer")
        answer.value = RequestState.Loading
        try {
            viewModelScope.launch {
                levelRepository.getAnswer(currentLevel).collect { answer ->
                    this@DragDropViewModel.answer.value = RequestState.Success(answer)
                    buildDrag(answer)
                    buildDrop()
                    buildAnswers(answer)
                    buildHints(answer)
                }
            }
        } catch (e: Exception) {
            answer.value = RequestState.Error(e)
        }
    }

    private fun buildHints(answer: Answer) {
        Log.d(TAG, "buildHints")
        hints = listOf(
            answer.answer1,
            answer.answer2,
            answer.answer3,
            answer.answer4,
        )
    }

    private fun buildAnswers(answer: Answer) {
        Log.d(TAG, "buildAnswers")
        answers = listOf(
            answer.answer1,
            answer.answer2,
            answer.answer3,
            answer.answer4,
        )
    }

    private fun buildDrop() {
        Log.d(TAG, "buildDrop")
        drop = listOf(
            "1",
            "2",
            "3",
            "4"
        )
    }

    private fun buildDrag(answer: Answer) {
        Log.d(TAG, "buildDrag")
        drag = listOf(
            answer.answer1,
            answer.answer2,
            answer.answer3,
            answer.answer4,
        ).shuffled()
    }

    private fun updateDragging(status: Boolean) {
        Log.d(TAG, "updateDragging")
        isCurrentlyDragging = status
    }

    private fun checkAnswer() {
        Log.d(TAG, "checkAnswer")
        if (dragButtonColors.contains(ColorState.REGULAR)) {
            isFinish = false
            return
        }

        val right = isRight.toMutableList()
        for (i in drop.indices) {
            if (drop[i] != answers[i]) {
                right[i] = false
                setDropColor(i, ColorState.MISTAKE)
                mistakesCount += 1
            } else {
                setDropColor(i, ColorState.RIGHT)
                right[i] = true
            }
        }

        isRight = right
        isFinish = true
    }

    private fun setAnswer(answer: String, index: Int) {
        Log.d(TAG, "setAnswer")
        val dropAnswer = drop.toMutableList()

        if (checkIfDropTaken(index)) {
            deleteAnswer(drop[index], index)
        }

        if (drop.contains(answer)) {
            dropAnswer[index] = (index + 1).toString()
            drop = dropAnswer
            toastLauncher.launch(DragDropToast.AnswerExits)
            return
        }

        dropAnswer[index] = answer
        drop = dropAnswer
        setDropColor(index, ColorState.REGULAR)
        setDragColor(answer, ColorState.TAKEN)
    }

    private fun checkIfDropTaken(index: Int): Boolean {
        Log.d(TAG, "checkIfDropTaken")
        if (drop[index] != (index + 1).toString()) {
            return true
        }
        return false
    }

    private fun deleteAnswer(answer: String, index: Int) {
        Log.d(TAG, "deleteAnswer")
        val dropAnswer = drop.toMutableList()
        dropAnswer[index] = (index + 1).toString()
        drop = dropAnswer
        setDragColor(answer, ColorState.REGULAR)
        setDropColor(index, ColorState.DROP)
    }

    private fun setDragColor(answer: String, colorState: ColorState) {
        Log.d(TAG, "setDragColor")
        val qButtonColors = dragButtonColors.toMutableList()
        qButtonColors[drag.indexOf(answer)] = colorState
        dragButtonColors = qButtonColors
    }

    private fun setDropColor(index: Int, colorState: ColorState) {
        Log.d(TAG, "setDropColor")
        val qButtonColors = dropButtonColors.toMutableList()
        qButtonColors[index] = colorState
        dropButtonColors = qButtonColors
    }

    private fun getHint() {
        Log.d(TAG, "getHint")
        if (!dropButtonColors.contains(ColorState.DROP))
            return

        if (hintCount == 4)
            return

        var indexDrop = -1
        hint = hints.random()
        while (drop.contains(hint)) {
            if(drop.indexOf(hint) == answers.indexOf(hint)) {
                hint = hints.random()
            }else{
                indexDrop = drop.indexOf(hint)
                deleteAnswer(hint, indexDrop)
                break
            }
        }

        indexDrop = answers.indexOf(hint)
        setDragColor(hint, ColorState.TAKEN)
        setAnswer(hint, indexDrop)
        setDropColor(indexDrop, ColorState.RIGHT)

        hints = hints.filter { it != hint }
        hintCount += 1
    }


}