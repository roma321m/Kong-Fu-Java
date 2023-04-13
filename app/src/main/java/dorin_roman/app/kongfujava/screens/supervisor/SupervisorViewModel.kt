package dorin_roman.app.kongfujava.screens.supervisor

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.models.RequestState.*
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.data.models.UserType.*
import dorin_roman.app.kongfujava.data.repository.UserTypeRepository
import dorin_roman.app.kongfujava.domain.models.users.User
import dorin_roman.app.kongfujava.domain.repository.AuthRepository
import dorin_roman.app.kongfujava.domain.repository.UsersRepository
import dorin_roman.app.kongfujava.ui.toast.ToastLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupervisorViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val usersRepository: UsersRepository,
    private val userTypeRepository: UserTypeRepository,
    private val toastLauncher: ToastLauncher
) : ViewModel() {

    companion object {
        const val TAG = "SupervisorViewModel"
    }

    var userType by mutableStateOf(None)
        private set

    private var currentUser: User? = null
    private val userId get() = authRepository.currentUser?.uid ?: ""

    var isAddUsers by mutableStateOf(false)
        private set

    var revokeAccessRequest by mutableStateOf<RequestState<Boolean>>(Idle)
        private set

    var userDataRequest by mutableStateOf<RequestState<User>>(Idle)
        private set

    var deleteUserRequest by mutableStateOf<RequestState<Boolean>>(Idle)
        private set

    var showLoading by mutableStateOf(false)
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
            is SupervisorEvent.InitData -> initData(event.userType)
            SupervisorEvent.LogOut -> logOut()
            SupervisorEvent.RevokeAccess -> revokeAccess()
            SupervisorEvent.RevokeAccessResponse -> handleRevokeAccessResponse()
            SupervisorEvent.DeleteUserResponse -> handleDeleteUserResponse()
            SupervisorEvent.LoadUserDataResponse -> handleLoadUserDataResponse()
        }
    }

    private fun revokeAccess() = viewModelScope.launch {
        Log.d(TAG, "revokeAccess")
        revokeAccessRequest = Loading
        revokeAccessRequest = authRepository.revokeAccess()
    }

    private fun deleteUser() = viewModelScope.launch {
        Log.d(TAG, "deleteUser")
        deleteUserRequest = Loading
        if (userType == Teacher) {
            deleteUserRequest = usersRepository.deleteTeacher(userId)
        } else if (userType == Parent) {
            deleteUserRequest = usersRepository.deleteParent(userId)
        }
    }

    private fun getTeacherData() = viewModelScope.launch {
        Log.d(TAG, "getTeacherData")
        userDataRequest = Loading
        userDataRequest = usersRepository.getTeacher(userId)
    }

    private fun getParentData() = viewModelScope.launch {
        Log.d(TAG, "getParentData")
        userDataRequest = Loading
        userDataRequest = usersRepository.getParent(userId)
    }

    private fun handleRevokeAccessResponse() {
        Log.d(TAG, "handleRevokeAccessResponse: $revokeAccessRequest")
        when (val revokeAccessResponse = revokeAccessRequest) {
            is Idle -> {}
            is Loading -> showLoading = true
            is Success -> {
                showLoading = false
                if (revokeAccessResponse.data) {
                    // todo - delete all sub users
                    deleteUser()
                }
            }
            is Error ->
                revokeAccessResponse.apply {
                    showLoading = false
                    toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
        }
    }

    private fun handleDeleteUserResponse() {
        Log.d(TAG, "handleDeleteUserResponse: $deleteUserRequest")
        when (val deleteUserResponse = deleteUserRequest) {
            is Idle -> {}
            is Loading -> showLoading = true
            is Success -> {
                showLoading = false
                if (deleteUserResponse.data) {
                    resetUserType()
                    toastLauncher.launch(SupervisorToast.RevokeAccess)
                }
            }
            is Error ->
                deleteUserResponse.apply {
                    showLoading = false
                    toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
        }
    }

    private fun handleLoadUserDataResponse() {
        Log.d(TAG, "handleLoadUserDataResponse: $userDataRequest")
        when (val userDataResponse = userDataRequest) {
            is Idle -> {}
            is Loading -> showLoading = true
            is Success -> {
                showLoading = false
                currentUser = userDataResponse.data
            }
            is Error ->
                userDataResponse.apply {
                    showLoading = false
                    toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
        }
    }

    private fun logOut() {
        Log.d(TAG, "logOut")
        authRepository.signOut()
        toastLauncher.launch(SupervisorToast.LogOut)
        resetUserType()
    }

    private fun resetUserType() {
        Log.d(TAG, "resetUserType")
        viewModelScope.launch(Dispatchers.IO) {
            userTypeRepository.persistUserType(None)
        }
    }

    private fun initData(type: UserType) {
        Log.d(TAG, "initData")
        userType = type

        when (userType) {
            None, Child -> return
            Teacher -> {
                Log.d(TAG, "initData: Loading teacher data")
                getTeacherData()
            }
            Parent -> {
                Log.d(TAG, "initData: Loading parent data")
                getParentData()
            }
        }

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