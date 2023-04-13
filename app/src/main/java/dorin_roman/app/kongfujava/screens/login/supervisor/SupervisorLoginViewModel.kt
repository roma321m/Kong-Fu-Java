package dorin_roman.app.kongfujava.screens.login.supervisor

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
import dorin_roman.app.kongfujava.data.repository.UserTypeRepository
import dorin_roman.app.kongfujava.domain.repository.AuthRepository
import dorin_roman.app.kongfujava.ui.toast.ToastLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupervisorLoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userTypeRepository: UserTypeRepository,
    private val toastLauncher: ToastLauncher
) : ViewModel() {

    // TODO - add reset password option

    companion object {
        const val TAG = "SupervisorLoginViewModel"
    }

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var showLoading by mutableStateOf(false)
        private set

    var userType by mutableStateOf(UserType.None)
        private set

    var loginRequest by mutableStateOf<RequestState<Boolean>>(Idle)
        private set

    fun handle(event: SupervisorLoginEvent) {
        when (event) {
            SupervisorLoginEvent.Login -> loginWithEmailAndPassword()
            SupervisorLoginEvent.LoginResponse -> handleLoginResponse()
            is SupervisorLoginEvent.UpdateEmailText -> updateEmail(event.text)
            is SupervisorLoginEvent.UpdatePasswordText -> updatePassword(event.text)
            is SupervisorLoginEvent.UpdateUserTypeLogin -> updateUserType(event.userType)
        }
    }

    private fun handleLoginResponse() {
        Log.d(TAG, "handleLoginResponse")
        when (val loginResponse = loginRequest) {
            is Idle -> {}
            is Loading -> showLoading = true
            is Success -> {
                showLoading = false
                if (loginResponse.data) {
                    toastLauncher.launch(SupervisorLoginToast.LoginSuccessfully)
                    persistUserType()
                }
            }
            is Error ->
                loginResponse.apply {
                    showLoading = false
                    toastLauncher.launch(SupervisorLoginToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
        }
    }

    private fun persistUserType() {
        Log.d(TAG, "persistUserType")
        viewModelScope.launch(Dispatchers.IO) {
            userTypeRepository.persistUserType(userType)
        }
    }

    private fun loginWithEmailAndPassword() = viewModelScope.launch {
        Log.d(TAG, "loginWithEmailAndPassword")
        loginRequest = Loading
        loginRequest = authRepository.firebaseSignInWithEmailAndPassword(email, password)
    }

    private fun updateEmail(text: String) {
        Log.d(TAG, "updateEmail")
        email = text
    }

    private fun updatePassword(text: String) {
        Log.d(TAG, "updatePassword")
        password = text
    }

    private fun updateUserType(newUserType: UserType) {
        userType = newUserType
    }
}