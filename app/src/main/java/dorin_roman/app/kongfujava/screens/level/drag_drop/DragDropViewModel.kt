package dorin_roman.app.kongfujava.screens.level.drag_drop

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.screens.level.drag_drop.components.AnswerItem
import javax.inject.Inject

@HiltViewModel
class DragDropViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val TAG = "DragDropViewModel"
    }

    var isCurrentlyDragging by mutableStateOf(false)
        private set

    var drag by mutableStateOf(emptyList<AnswerItem>())
        private set

    var drop by mutableStateOf(emptyList<AnswerItem>())
        private set

    init {
        drag = listOf(
            AnswerItem("A"),
            AnswerItem("B"),
            AnswerItem("C"),
            AnswerItem("D")
        )

        drop = listOf(
            AnswerItem(""),
            AnswerItem(""),
            AnswerItem(""),
            AnswerItem("")
        )
    }

    fun startDragging(){
        isCurrentlyDragging = true
    }

    fun stopDragging(){
        isCurrentlyDragging = false
    }

    fun checkAnswer(answer : AnswerItem){
        Log.d(TAG, "checkAnswer")
    }
}