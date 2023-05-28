package dorin_roman.app.kongfujava.screens.supervisor

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.models.RequestState.Error
import dorin_roman.app.kongfujava.data.models.RequestState.Idle
import dorin_roman.app.kongfujava.data.models.RequestState.Loading
import dorin_roman.app.kongfujava.data.models.RequestState.Success
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.data.models.UserType.None
import dorin_roman.app.kongfujava.data.models.UserType.Parent
import dorin_roman.app.kongfujava.data.models.UserType.Teacher
import dorin_roman.app.kongfujava.data.repository.UserTypeRepository
import dorin_roman.app.kongfujava.domain.models.users.User
import dorin_roman.app.kongfujava.domain.repository.AuthRepository
import dorin_roman.app.kongfujava.domain.repository.LinkedAccountsRepository
import dorin_roman.app.kongfujava.domain.repository.ProfileImageRepository
import dorin_roman.app.kongfujava.domain.repository.UsersRepository
import dorin_roman.app.kongfujava.ui.toast.ToastLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupervisorViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val profileImageRepository: ProfileImageRepository,
    private val usersRepository: UsersRepository,
    private val userTypeRepository: UserTypeRepository,
    private val linkedAccountsRepository: LinkedAccountsRepository,
    private val toastLauncher: ToastLauncher
) : ViewModel() {

    companion object {
        const val TAG = "SupervisorViewModel"
    }

    var userType by mutableStateOf(None)
        private set

    var isAddUsers by mutableStateOf(true)
        private set

    var studentsModelList by mutableStateOf(listOf<StudentModel>())
        private set

    var supervisorModel by mutableStateOf(SupervisorModel())
        private set

    var selectedStudent by mutableStateOf(StudentModel())
        private set

    var isRefreshing by mutableStateOf(false)
        private set

    private var revokeAccessRequest by mutableStateOf<RequestState<Boolean>>(Idle)

    private var userDataRequest by mutableStateOf<RequestState<User>>(Idle)

    private var deleteUserRequest by mutableStateOf<RequestState<Boolean>>(Idle)

    private var linkedAccountsRequest by mutableStateOf<RequestState<List<String>>>(Idle)

    private var listOfChildren: MutableList<String> = mutableListOf()

    private var currentUser: User? = null

    private val userId get() = authRepository.currentUser?.uid ?: ""

    fun handle(event: SupervisorEvent) {
        Log.d(TAG, "handle: $event")
        when (event) {
            is SupervisorEvent.AddUsersSelected -> updatedIsAddUsers(event.selected)
            is SupervisorEvent.SelectStudent -> selectStudent(event.student)
            is SupervisorEvent.InitData -> initData(event.userType)
            is SupervisorEvent.ImageSelected -> uploadImage(event.image)
            SupervisorEvent.LogOut -> logOut()
            SupervisorEvent.RevokeAccess -> revokeAccess()
            SupervisorEvent.RefreshChildrenList -> {
                updatedIsAddUsers(true)
                getLinkedAccounts()
            }
        }
    }

    private fun initData(type: UserType) {
        Log.d(TAG, "initData")
        resetData()
        getUserData(type)
        getLinkedAccounts()
    }

    private fun resetData() {
        Log.d(TAG, "resetData")
        userType = None
        isAddUsers = true
        studentsModelList = listOf()
        supervisorModel = SupervisorModel()
        selectedStudent = StudentModel()
        isRefreshing = false
        revokeAccessRequest = Idle
        userDataRequest = Idle
        deleteUserRequest = Idle
        linkedAccountsRequest = Idle
        listOfChildren = mutableListOf()
        currentUser = null
    }

    private fun getLinkedAccounts() = viewModelScope.launch {
        Log.d(TAG, "getLinkedAccounts")
        linkedAccountsRequest = Loading
        isRefreshing = true
        linkedAccountsRequest = linkedAccountsRepository.getLinkedAccounts(userId)
            .also { response ->
                if (response is Success) {
                    listOfChildren = response.data.toMutableList()
                    loadChildrenData()
                } else if (response is Error) {
                    response.apply {
                        toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                        Log.e(TAG, "${error.message}")
                    }
                }
            }
    }

    private fun uploadImage(image: Uri) = viewModelScope.launch {
        Log.d(TAG, "uploadImage")
        profileImageRepository.addImageToCouldStorage(
            uid = userId,
            imageUri = image
        ).also { response ->
            if (response is Success) {
                updateUser(response.data)
            } else if (response is Error) {
                response.apply {
                    toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun updateUser(url: String) {
        Log.d(TAG, "updateUser")
        if (userType == Teacher) {
            updateTeacherUser(url)
        } else if (userType == Parent) {
            updateParentUser(url)
        }
    }

    private fun updateParentUser(url: String) = viewModelScope.launch {
        Log.d(TAG, "updateParentUser")
        usersRepository.updateParentImage(
            userId,
            url
        ).also { response ->
            if (response is Success) {
                if (response.data) {
                    getParentData()
                }
            } else if (response is Error) {
                response.apply {
                    toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun updateTeacherUser(url: String) = viewModelScope.launch {
        Log.d(TAG, "updateTeacherUser")
        usersRepository.updateTeacherImage(
            userId,
            url
        ).also { response ->
            if (response is Success) {
                if (response.data) {
                    getTeacherData()
                }
            } else if (response is Error) {
                response.apply {
                    toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun loadChildrenData() = viewModelScope.launch {
        Log.d(TAG, "loadChildrenData")
        val newList: MutableList<StudentModel> = mutableListOf()
        listOfChildren.forEach { childId ->
            usersRepository.getChild(childId)
                .also { response ->
                    if (response is Success) {
                        newList.add(
                            StudentModel(
                                id = response.data.id,
                                name = response.data.name ?: "",
                                age = response.data.age ?: 0,
                                imageUrl = response.data.imageUrl,
                                privateCode = response.data.privateCode,
                                selected = false
                            )
                        )
                    }
                }
        }.also {
            studentsModelList = newList
            isRefreshing = false
        }
    }

    private fun revokeAccess() = viewModelScope.launch {
        Log.d(TAG, "revokeAccess")
        revokeAccessRequest = Loading
        revokeAccessRequest = authRepository.revokeAccess()
            .also { response ->
                if (response is Success) {
                    if (response.data) {
                        // todo - delete all sub users
                        deleteUser()
                    }
                } else if (response is Error) {
                    response.apply {
                        toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                        Log.e(TAG, "${error.message}")
                    }
                }
            }
    }

    private fun deleteUser() {
        Log.d(TAG, "deleteUser")
        deleteUserRequest = Loading
        if (userType == Teacher) {
            deleteTeacherUser()
        } else if (userType == Parent) {
            deleteParentUser()
        }
    }

    private fun deleteParentUser() = viewModelScope.launch {
        Log.d(TAG, "deleteParentUser")
        deleteUserRequest = usersRepository.deleteParent(userId)
            .also { response ->
                if (response is Success) {
                    if (response.data) {
                        resetUserType()
                        toastLauncher.launch(SupervisorToast.RevokeAccess)
                    }
                } else if (response is Error) {
                    response.apply {
                        toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                        Log.e(TAG, "${error.message}")
                    }
                }
            }
    }

    private fun deleteTeacherUser() = viewModelScope.launch {
        Log.d(TAG, "deleteTeacherUser")
        deleteUserRequest = usersRepository.deleteTeacher(userId)
            .also { response ->
                if (response is Success) {
                    if (response.data) {
                        resetUserType()
                        toastLauncher.launch(SupervisorToast.RevokeAccess)
                    }
                } else if (response is Error) {
                    response.apply {
                        toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                        Log.e(TAG, "${error.message}")
                    }
                }
            }
    }

    private fun getUserData(type: UserType) {
        Log.d(TAG, "getUserData")
        userType = type
        if (userType == Teacher) {
            Log.d(TAG, "getUserData: Loading teacher data")
            getTeacherData()
        } else if (userType == Parent) {
            Log.d(TAG, "getUserData: Loading parent data")
            getParentData()
        }
    }

    private fun getTeacherData() = viewModelScope.launch {
        Log.d(TAG, "getTeacherData")
        userDataRequest = Loading
        userDataRequest = usersRepository.getTeacher(userId)
            .also { response ->
                if (response is Success) {
                    currentUser = response.data
                    supervisorModel = SupervisorModel(
                        id = response.data.id,
                        email = response.data.email ?: "",
                        imageUrl = response.data.imageUrl,
                        className = response.data.className ?: "",
                        schoolName = response.data.schoolName ?: "",
                    )
                } else if (response is Error) {
                    response.apply {
                        toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                        Log.e(TAG, "${error.message}")
                    }
                }
            }
    }

    private fun getParentData() = viewModelScope.launch {
        Log.d(TAG, "getParentData")
        userDataRequest = Loading
        userDataRequest = usersRepository.getParent(userId)
            .also { response ->
                if (response is Success) {
                    currentUser = response.data
                    supervisorModel = SupervisorModel(
                        id = response.data.id,
                        email = response.data.email ?: "",
                        imageUrl = response.data.imageUrl
                    )
                } else if (response is Error) {
                    response.apply {
                        toastLauncher.launch(SupervisorToast.SomethingWentWrong)
                        Log.e(TAG, "${error.message}")
                    }
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

    private fun selectStudent(student: StudentModel) {
        Log.d(TAG, "selectStudent")
        if (isAddUsers) {
            isAddUsers = false
        }
        resetStudentSelected(student)
        selectedStudent = student
    }

    private fun resetStudentSelected(student: StudentModel) {
        Log.d(TAG, "resetStudentSelected")
        val newList: MutableList<StudentModel> = mutableListOf()
        newList.addAll(
            studentsModelList.map {
                StudentModel(
                    id = it.id,
                    name = it.name,
                    age = it.age,
                    imageUrl = it.imageUrl,
                    privateCode = it.privateCode,
                    selected = it == student
                )
            }
        )
        studentsModelList = newList
    }

    private fun updatedIsAddUsers(selected: Boolean) {
        Log.d(TAG, "updatedIsAddUsers")
        isAddUsers = selected
        selectedStudent = StudentModel()
        if (selected) {
            studentsModelList.forEach {
                it.selected = false
            }
        }
    }
}