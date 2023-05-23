package dorin_roman.app.kongfujava.screens.register

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
import dorin_roman.app.kongfujava.domain.repository.AuthRepository
import dorin_roman.app.kongfujava.ui.toast.ToastLauncher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val toastLauncher: ToastLauncher
) : ViewModel() {

    companion object {
        const val TAG = "RegisterViewModel"
    }

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var verifyStep by mutableStateOf(false)
        private set

    var showLoading by mutableStateOf(false)
        private set

    var registerRequest by mutableStateOf<RequestState<Boolean>>(Idle)
        private set

    var sendEmailVerificationRequest by mutableStateOf<RequestState<Boolean>>(Idle)
        private set

    fun handle(event: RegisterEvent) {
        when (event) {
            RegisterEvent.Register -> signUpWithEmailAndPassword()
            RegisterEvent.SendEmailVerification -> sendEmailVerification()
            is RegisterEvent.UpdateEmailText -> updateEmail(event.text)
            is RegisterEvent.UpdatePasswordText -> updatePassword(event.text)
        }
    }

    private fun sendEmailVerification() = viewModelScope.launch {
        Log.d(TAG, "sendEmailVerification")
        sendEmailVerificationRequest = Loading
        showLoading = true
        sendEmailVerificationRequest = authRepository.sendEmailVerification()
            .also { response ->
                showLoading = false
                if (response is Success) {
                    if (response.data) {
                        verifyStep = true
                        toastLauncher.launch(RegisterToast.VerificationEmailSent)
                    }
                } else if (response is Error) {
                    response.apply {
                        toastLauncher.launch(RegisterToast.SomethingWentWrong)
                        Log.e(TAG, "${error.message}")
                    }
                }
            }
    }

    private fun signUpWithEmailAndPassword() = viewModelScope.launch {
        Log.d(TAG, "signUpWithEmailAndPassword")
        registerRequest = Loading
        showLoading = true
        registerRequest = authRepository.firebaseSignUpWithEmailAndPassword(
            email = email,
            password = password
        ).also { response ->
            showLoading = false
            if (response is Success) {
                if (response.data) {
                    sendEmailVerification()
                }
            } else if (response is Error) {
                response.apply {
                    toastLauncher.launch(RegisterToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun updateEmail(text: String) {
        Log.d(TAG, "updateEmail")
        email = text
    }

    private fun updatePassword(text: String) {
        Log.d(TAG, "updatePassword")
        password = text
    }
}