package dorin_roman.app.kongfujava.screens.register.teacher

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.data.repository.UserTypeRepository
import dorin_roman.app.kongfujava.domain.models.FirebaseRequestState
import dorin_roman.app.kongfujava.domain.models.users.Teacher
import dorin_roman.app.kongfujava.domain.repository.AuthRepository
import dorin_roman.app.kongfujava.domain.repository.UsersRepository
import dorin_roman.app.kongfujava.screens.register.RegisterToast
import dorin_roman.app.kongfujava.ui.toast.ToastLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherRegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val usersRepository: UsersRepository,
    private val userTypeRepository: UserTypeRepository,
    private val toastLauncher: ToastLauncher
) : ViewModel() {

    companion object {
        const val TAG = "TeacherRegisterViewModel"
    }

    init {
        getAuthState()
    }

    var reloadUserRequest by mutableStateOf<FirebaseRequestState<Boolean>>(
        FirebaseRequestState.Success(
            false
        )
    )
        private set

    var saveUserRequest by mutableStateOf<FirebaseRequestState<Boolean>>(
        FirebaseRequestState.Success(
            false
        )
    )
        private set

    var showLoading by mutableStateOf(false)
        private set

    var className by mutableStateOf("")
        private set

    var schoolName by mutableStateOf("")
        private set

    private fun getAuthState() = authRepository.getAuthState(viewModelScope)

    private val isEmailVerified get() = authRepository.currentUser?.isEmailVerified ?: false
    private val userId get() = authRepository.currentUser?.uid
    private val userEmail get() = authRepository.currentUser?.email ?: ""

    fun handle(event: TeacherRegisterEvent) {
        when (event) {
            TeacherRegisterEvent.ReloadUser -> reloadUser()
            TeacherRegisterEvent.ReloadUserResponse -> handleReloadUserResponse()
            TeacherRegisterEvent.SaveUserToDatabaseResponse -> handleSaveUserToDatabaseResponse()
            is TeacherRegisterEvent.UpdateClassText -> updateClassName(event.text)
            is TeacherRegisterEvent.UpdateSchoolText -> updateSchoolName(event.text)
        }
    }

    private fun reloadUser() = viewModelScope.launch {
        Log.d(TAG, "reloadUser")
        reloadUserRequest = FirebaseRequestState.Loading
        reloadUserRequest = authRepository.reloadFirebaseUser()
    }

    private fun handleReloadUserResponse() {
        Log.d(TAG, "handleReloadUserResponse")
        when (val reloadResponse = reloadUserRequest) {
            is FirebaseRequestState.Loading -> showLoading = true
            is FirebaseRequestState.Success -> {
                showLoading = false
                if (reloadResponse.data) {
                    if (isEmailVerified) {
                        toastLauncher.launch(RegisterToast.EmailVerified)
                        saveUserToDatabase()
                    } else {
                        toastLauncher.launch(RegisterToast.VerifyYourEmail)
                    }
                }
            }
            is FirebaseRequestState.Failure ->
                reloadResponse.apply {
                    showLoading = false
                    toastLauncher.launch(RegisterToast.SomethingWentWrong)
                    Log.e(TAG, "${e.message}")
                }
        }
    }

    private fun handleSaveUserToDatabaseResponse() {
        Log.d(TAG, "handleSaveUserToDatabaseResponse")
        when (val saveUserResponse = saveUserRequest) {
            is FirebaseRequestState.Loading -> showLoading = true
            is FirebaseRequestState.Success -> {
                showLoading = false
                if (saveUserResponse.data) {
                    persistUserType()
                }
            }
            is FirebaseRequestState.Failure ->
                saveUserResponse.apply {
                    showLoading = false
                    toastLauncher.launch(RegisterToast.SomethingWentWrong)
                    Log.e(TAG, "${e.message}")
                }
        }
    }

    private fun saveUserToDatabase() = viewModelScope.launch {
        Log.d(TAG, "saveUserToDatabase")
        userId?.let { id ->
            saveUserRequest = FirebaseRequestState.Loading
            saveUserRequest = usersRepository.createTeacher(
                id = id,
                teacher = Teacher(userEmail, className, schoolName)
            )
        }
    }

    private fun persistUserType() {
        Log.d(TAG, "persistUserType - Teacher")
        viewModelScope.launch(Dispatchers.IO) {
            userTypeRepository.persistUserType(UserType.Teacher)
        }
    }

    private fun updateClassName(text: String) {
        Log.d(TAG, "updateClassName")
        className = text
    }

    private fun updateSchoolName(text: String) {
        Log.d(TAG, "updateSchoolName")
        schoolName = text
    }

}