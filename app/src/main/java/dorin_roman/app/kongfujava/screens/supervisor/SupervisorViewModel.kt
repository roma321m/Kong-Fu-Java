package dorin_roman.app.kongfujava.screens.supervisor

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SupervisorViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val TAG = "SupervisorViewModel"
    }

    var isAddUsers by mutableStateOf(false)
        private set

    var studentsModelList by mutableStateOf(listOf<StudentModel>())
        private set

    var selectedStudent by mutableStateOf(
        StudentModel(
            "",
            "",
            false
        )
    )
        private set

    fun handle(event: SupervisorEvent) {
        when (event) {
            is SupervisorEvent.AddUsersSelected -> updatedIsAddUsers(event.selected)
            is SupervisorEvent.SelectStudent -> selectStudent(event.student)
            SupervisorEvent.InitSelectedStudent -> initSelectedStudent()
        }
    }

    private fun initSelectedStudent() {
        val idList =
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19) // temp
        studentsModelList = idList.map {
            StudentModel(
                "id$it",
                "name $it",
                false
            )
        } // FIXME - temp data, need to come from firebase

        if (studentsModelList.isNotEmpty()) {
            selectStudent(studentsModelList.first())
        }
    }

    private fun selectStudent(student: StudentModel) {
        Log.d(TAG, "selectStudent")
        if (isAddUsers) {
            isAddUsers = false
        }
        val newList = resetStudentSelected()
        selectStudent(student, newList)
        studentsModelList = newList
        selectedStudent = student
    }

    private fun resetStudentSelected(): List<StudentModel> {
        Log.d(TAG, "resetStudentSelected")
        return studentsModelList.map {
            StudentModel(
                id = it.id,
                name = it.name,
                selected = false
            )
        }
    }

    private fun selectStudent(student: StudentModel, list: List<StudentModel>) {
        list.find {
            it.id == student.id
        }?.selected = true
    }

    private fun updatedIsAddUsers(selected: Boolean) {
        Log.d(TAG, "updatedIsAddUsers")
        isAddUsers = selected
        selectedStudent = StudentModel(
            id = "",
            name = "",
            selected = false
        )
        if (selected) {
            studentsModelList.forEach {
                it.selected = false
            }
        }
    }
}