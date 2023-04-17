package dorin_roman.app.kongfujava.screens.login.child

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.data.repository.UserTypeRepository
import dorin_roman.app.kongfujava.di.provider.IdProvider
import dorin_roman.app.kongfujava.domain.models.codes.Code
import dorin_roman.app.kongfujava.domain.repository.CodeRepository
import dorin_roman.app.kongfujava.ui.toast.ToastLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChildLoginViewModel @Inject constructor(
    private val idProvider: IdProvider,
    private val codeRepository: CodeRepository,
    private val userTypeRepository: UserTypeRepository,
    private val toastLauncher: ToastLauncher,
) : ViewModel() {

    companion object {
        const val TAG = "ChildLoginViewModel"
    }

    var studentCode by mutableStateOf("")
        private set

    var studentName by mutableStateOf("")
        private set

    var studentAge by mutableStateOf("")
        private set

    var codeRequest by mutableStateOf<RequestState<Code>>(RequestState.Idle)
        private set

    var showLoading by mutableStateOf(false)
        private set

    var stepState by mutableStateOf(ChildLoginStepState.CODE)
        private set

    val childId = idProvider.provide()

    lateinit var currentCode: Code

    fun handle(event: ChildLoginEvent) {
        when (event) {
            ChildLoginEvent.CodeResponse -> handleCodeDataResponse()
            is ChildLoginEvent.OnAgeChange -> updateAge(event.age)
            is ChildLoginEvent.OnCodeChange -> updateTextCode(event.code)
            is ChildLoginEvent.OnNameChange -> updateName(event.name)
            is ChildLoginEvent.OnNextClick -> updateStep(event.step)
        }
    }

    private fun getCodeData(code: String) = viewModelScope.launch {
        Log.d(TAG, "getCode")
        codeRequest = RequestState.Loading
        codeRequest = codeRepository.getPublicCode(code)
    }

    private fun handleCodeDataResponse() {
        Log.d(TAG, "handleCodeDataResponse: $codeRequest")
        when (val codeResponse = codeRequest) {
            is RequestState.Idle -> {}
            is RequestState.Loading -> showLoading = true
            is RequestState.Success -> {
                showLoading = false
                currentCode = codeResponse.data
            }

            is RequestState.Error ->
                codeResponse.apply {
                    showLoading = false
                    toastLauncher.launch(ChildToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
        }
    }

    private fun persistUserType() {
        Log.d(TAG, "persistUserType - Teacher")
        viewModelScope.launch(Dispatchers.IO) {
            userTypeRepository.persistUserType(UserType.Child)
        }
    }

    private fun updateStep(newStepState: ChildLoginStepState) {
        Log.d(TAG, "updateStep: $newStepState")
        stepState = newStepState
        showLoading = stepState == ChildLoginStepState.FINAL // fixme - temp
        if (showLoading)
            persistUserType() // fixme temp
    }

    private fun updateName(name: String) {
        Log.d(TAG, "updateName")
        studentName = name
    }

    private fun updateTextCode(code: String) {
        Log.d(TAG, "updateTextCode")
        studentCode = code
    }

    private fun updateAge(age: String) {
        Log.d(TAG, "updateAge")
        studentAge = age
    }

}