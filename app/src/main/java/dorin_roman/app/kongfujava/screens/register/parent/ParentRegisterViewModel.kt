package dorin_roman.app.kongfujava.screens.register.parent

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
import dorin_roman.app.kongfujava.domain.models.users.Parent
import dorin_roman.app.kongfujava.domain.repository.AuthRepository
import dorin_roman.app.kongfujava.domain.repository.UsersRepository
import dorin_roman.app.kongfujava.screens.register.RegisterToast
import dorin_roman.app.kongfujava.ui.toast.ToastLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ParentRegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val usersRepository: UsersRepository,
    private val userTypeRepository: UserTypeRepository,
    private val toastLauncher: ToastLauncher
) : ViewModel() {

    companion object {
        const val TAG = "ParentRegisterViewModel"
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

    private fun getAuthState() = authRepository.getAuthState(viewModelScope)

    private val isEmailVerified get() = authRepository.currentUser?.isEmailVerified ?: false
    private val userId get() = authRepository.currentUser?.uid
    private val userEmail get() = authRepository.currentUser?.email ?: ""

    fun handle(event: ParentRegisterEvent) {
        when (event) {
            ParentRegisterEvent.ReloadUser -> reloadUser()
            ParentRegisterEvent.SaveUserToDatabaseResponse -> handleSaveUserToDatabaseResponse()
            ParentRegisterEvent.ReloadUserResponse -> handleReloadUserResponse()
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
                if (reloadResponse.data) {
                    showLoading = false
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
            saveUserRequest = usersRepository.createParent(
                id = id,
                parent = Parent(userEmail)
            )
        }
    }

    private fun persistUserType() {
        Log.d(TAG, "persistUserType - Parent")
        viewModelScope.launch(Dispatchers.IO) {
            userTypeRepository.persistUserType(UserType.Parent)
        }
    }

}