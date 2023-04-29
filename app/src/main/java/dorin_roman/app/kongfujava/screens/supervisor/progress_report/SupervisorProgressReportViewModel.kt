package dorin_roman.app.kongfujava.screens.supervisor.progress_report

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.screens.supervisor.StudentModel
import javax.inject.Inject


@HiltViewModel
class SupervisorProgressReportViewModel @Inject constructor(

) : ViewModel() {

    var selectedStudent by mutableStateOf(StudentModel())
        private set

    companion object {
        const val TAG = "SupervisorAddUsersViewModel"
    }

    fun handle(event: SupervisorProgressReportEvent) {
        when (event) {
            is SupervisorProgressReportEvent.UpdateSelectedStudent -> updateSelectedStudent(event.student)
        }
    }

    private fun updateSelectedStudent(student: StudentModel) {
        Log.d(TAG, "updateSelectedStudent")
        selectedStudent = student
    }

}