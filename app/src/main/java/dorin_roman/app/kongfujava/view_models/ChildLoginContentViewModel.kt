package dorin_roman.app.kongfujava.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.ui.screens.login.child.content.ChildLoginContentEvent
import dorin_roman.app.kongfujava.util.LoginStudentContent
import javax.inject.Inject

@HiltViewModel
class ChildLoginContentViewModel @Inject constructor() : ViewModel() {
    var studentCode by mutableStateOf("")
        private set

    var studentCodeVisible by mutableStateOf(true)
        private set

    var studentName by mutableStateOf("")
        private set

    var studentNameVisible by mutableStateOf(false)
        private set

    var studentAge by mutableStateOf(0)
        private set

    var studentAgeVisible by mutableStateOf(false)
        private set

    fun handle(event: ChildLoginContentEvent) {
        when (event) {
            is ChildLoginContentEvent.EnterStudentCode -> enterStudentCode(event.code)
            is ChildLoginContentEvent.EnterStudentName -> enterStudentName(event.name)
            is ChildLoginContentEvent.EnterStudentAge -> enterStudentAge(event.age)
        }
    }

    private fun enterStudentCode(code: String) {
        //TODO ADD CHECK CODE
        studentCode = if (code.isEmpty()) {
            "Enter Code"
        } else {
            setContentVisible(LoginStudentContent.CODE)
            code
        }
    }

    private fun enterStudentName(name: String) {
        studentName = if (name.isEmpty()) {
            "Enter Name"
        } else {
            setContentVisible(LoginStudentContent.NAME)
            name
        }
    }

    private fun enterStudentAge(age: Int) {
        studentAge = if (age in 9..14) {
            setContentVisible(LoginStudentContent.AGE)
            age
        } else {
            0
        }
    }

    private fun setContentVisible(content: LoginStudentContent) {
        when (content) {
            LoginStudentContent.CODE -> {
                studentCodeVisible = false
                studentNameVisible = true
            }
            LoginStudentContent.NAME -> {
                studentNameVisible = false
                studentAgeVisible = true

            }
            LoginStudentContent.AGE -> {
                studentAgeVisible = false
            }
        }
    }


}