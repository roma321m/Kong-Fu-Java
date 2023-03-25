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
import dorin_roman.app.kongfujava.domain.model.FirebaseRequestState
import dorin_roman.app.kongfujava.domain.model.FirebaseRequestState.Loading
import dorin_roman.app.kongfujava.domain.model.FirebaseRequestState.Success
import dorin_roman.app.kongfujava.domain.repository.AuthRepository
import dorin_roman.app.kongfujava.ui.toast.ToastLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ParentRegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userTypeRepository: UserTypeRepository,
    private val toastLauncher: ToastLauncher
) : ViewModel() {

    companion object {
        const val TAG = "ParentRegisterViewModel"
    }

    init {
        getAuthState()
    }

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var verifyStep by mutableStateOf(false)
        private set

    var showLoading by mutableStateOf(false)
        private set

    var registerRequest by mutableStateOf<FirebaseRequestState<Boolean>>(Success(false))
        private set

    var sendEmailVerificationRequest by mutableStateOf<FirebaseRequestState<Boolean>>(
        Success(false)
    )
        private set

    var reloadUserRequest by mutableStateOf<FirebaseRequestState<Boolean>>(Success(false))
        private set

    private fun getAuthState() = authRepository.getAuthState(viewModelScope)

    private val isEmailVerified get() = authRepository.currentUser?.isEmailVerified ?: false

    fun handle(event: ParentRegisterEvent) {
        when (event) {
            ParentRegisterEvent.Register -> signUpWithEmailAndPassword()
            ParentRegisterEvent.SendEmailVerification -> sendEmailVerification()
            is ParentRegisterEvent.UpdateEmailText -> updateEmail(event.text)
            is ParentRegisterEvent.UpdatePasswordText -> updatePassword(event.text)
            ParentRegisterEvent.RegisterResponse -> handleRegisterResponse()
            ParentRegisterEvent.SendEmailVerificationResponse -> handleSendEmailVerifiedResponse()
            ParentRegisterEvent.ReloadUser -> reloadUser()
            ParentRegisterEvent.ReloadUserResponse -> handleReloadUserResponse()
        }
    }

    private fun handleRegisterResponse() {
        Log.d(TAG, "handleRegisterResponse")
        when (val signUpResponse = registerRequest) {
            is Loading -> showLoading = true
            is Success -> {
                showLoading = false
                if (signUpResponse.data) {
                    sendEmailVerification()
                }
            }
            is FirebaseRequestState.Failure ->
                signUpResponse.apply {
                    showLoading = false
                    toastLauncher.launch(ParentRegisterToast.SomethingWentWrong)
                    Log.e(TAG, "${e.message}")
                }
        }
    }

    private fun handleSendEmailVerifiedResponse() {
        Log.d(TAG, "handleSendEmailVerifiedResponse")
        when (val emailVerifiedResponse = sendEmailVerificationRequest) {
            is Loading -> showLoading = true
            is Success -> {
                if (emailVerifiedResponse.data) {
                    showLoading = false
                    verifyStep = true
                    toastLauncher.launch(ParentRegisterToast.VerificationEmailSent)
                }
            }
            is FirebaseRequestState.Failure ->
                emailVerifiedResponse.apply {
                    showLoading = false
                    toastLauncher.launch(ParentRegisterToast.SomethingWentWrong)
                    Log.e(TAG, "${e.message}")
                }
        }
    }

    private fun handleReloadUserResponse() {
        Log.d(TAG, "handleReloadUserResponse")
        when (val reloadResponse = reloadUserRequest) {
            is Loading -> showLoading = true
            is Success -> {
                if (reloadResponse.data) {
                    showLoading = false
                    if (isEmailVerified) {
                        toastLauncher.launch(ParentRegisterToast.EmailVerified)
                        persistUserType()
                    } else {
                        toastLauncher.launch(ParentRegisterToast.VerifyYourEmail)
                    }
                }
            }
            is FirebaseRequestState.Failure ->
                reloadResponse.apply {
                    showLoading = false
                    toastLauncher.launch(ParentRegisterToast.SomethingWentWrong)
                    Log.e(TAG, "${e.message}")
                }
        }
    }

    private fun persistUserType() {
        Log.d(TAG, "persistUserType")
        viewModelScope.launch(Dispatchers.IO) {
            userTypeRepository.persistUserType(UserType.Parent)
        }
    }

    private fun sendEmailVerification() = viewModelScope.launch {
        Log.d(TAG, "sendEmailVerification")
        sendEmailVerificationRequest = Loading
        sendEmailVerificationRequest = authRepository.sendEmailVerification()
    }

    private fun signUpWithEmailAndPassword() = viewModelScope.launch {
        Log.d(TAG, "signUpWithEmailAndPassword")
        registerRequest = Loading
        registerRequest =
            authRepository.firebaseSignUpWithEmailAndPassword(email, password)
    }

    private fun reloadUser() = viewModelScope.launch {
        Log.d(TAG, "reloadUser")
        reloadUserRequest = Loading
        reloadUserRequest = authRepository.reloadFirebaseUser()
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