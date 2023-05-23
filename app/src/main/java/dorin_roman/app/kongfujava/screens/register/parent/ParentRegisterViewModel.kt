package dorin_roman.app.kongfujava.screens.register.parent

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
import dorin_roman.app.kongfujava.data.repository.UserTypeRepository
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

    var reloadUserRequest by mutableStateOf<RequestState<Boolean>>(Idle)
        private set

    var saveUserRequest by mutableStateOf<RequestState<Boolean>>(Idle)
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
        }
    }

    private fun reloadUser() = viewModelScope.launch {
        Log.d(TAG, "reloadUser")
        reloadUserRequest = Loading
        showLoading = true
        reloadUserRequest = authRepository.reloadFirebaseUser()
            .also { response ->
                showLoading = false
                if (response is Success) {
                    if (response.data) {
                        if (isEmailVerified) {
                            toastLauncher.launch(RegisterToast.EmailVerified)
                            saveUserToDatabase()
                        } else {
                            toastLauncher.launch(RegisterToast.VerifyYourEmail)
                        }
                    }
                } else if (response is Error) {
                    response.apply {
                        toastLauncher.launch(RegisterToast.SomethingWentWrong)
                        Log.e(TAG, "${error.message}")
                    }
                }
            }
    }

    private fun saveUserToDatabase() = viewModelScope.launch {
        Log.d(TAG, "saveUserToDatabase")
        userId?.let { id ->
            saveUserRequest = Loading
            showLoading = true
            saveUserRequest = usersRepository.createParent(
                Parent(
                    id = id,
                    email = userEmail
                )
            ).also { response ->
                showLoading = false
                if (response is Success) {
                    if (response.data) {
                        persistUserType()
                    }
                } else if (response is Error) {
                    response.apply {
                        toastLauncher.launch(RegisterToast.SomethingWentWrong)
                        Log.e(TAG, "${error.message}")
                    }
                }
            }
        }
    }

    private fun persistUserType() {
        Log.d(TAG, "persistUserType - Parent")
        viewModelScope.launch(Dispatchers.IO) {
            userTypeRepository.persistUserType(UserType.Parent)
        }
    }

}