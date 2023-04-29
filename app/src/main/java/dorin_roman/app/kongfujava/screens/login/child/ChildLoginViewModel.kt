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
import dorin_roman.app.kongfujava.di.provider.CodeProvider
import dorin_roman.app.kongfujava.di.provider.IdProvider
import dorin_roman.app.kongfujava.domain.models.LinkedAccounts
import dorin_roman.app.kongfujava.domain.models.codes.Code
import dorin_roman.app.kongfujava.domain.models.codes.PrivateCode
import dorin_roman.app.kongfujava.domain.models.codes.PublicCode
import dorin_roman.app.kongfujava.domain.models.users.Child
import dorin_roman.app.kongfujava.domain.repository.CodeRepository
import dorin_roman.app.kongfujava.domain.repository.LinkedAccountsRepository
import dorin_roman.app.kongfujava.domain.repository.UsersRepository
import dorin_roman.app.kongfujava.ui.toast.ToastLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChildLoginViewModel @Inject constructor(
    idProvider: IdProvider,
    codeProvider: CodeProvider,
    private val codeRepository: CodeRepository,
    private val userTypeRepository: UserTypeRepository,
    private val usersRepository: UsersRepository,
    private val linkedAccountsRepository: LinkedAccountsRepository,
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

    var stepState by mutableStateOf(ChildLoginStepState.CODE)
        private set

    private val privateCode = codeProvider.provide()

    private var codeRequest by mutableStateOf<RequestState<Code>>(RequestState.Idle)

    private var saveUserRequest by mutableStateOf<RequestState<Boolean>>(RequestState.Idle)

    private var saveLinkedAccountsRequest by mutableStateOf<RequestState<Boolean>>(RequestState.Idle)

    private val currentTime: Long
        get() = System.currentTimeMillis()

    private val childId = idProvider.provide()

    private lateinit var supervisorId: String

    fun handle(event: ChildLoginEvent) {
        when (event) {
            is ChildLoginEvent.OnAgeChange -> updateAge(event.age)
            is ChildLoginEvent.OnCodeChange -> updateTextCode(event.code)
            is ChildLoginEvent.OnNameChange -> updateName(event.name)
            is ChildLoginEvent.OnNextClick -> updateStep(event.step)
        }
    }

    private fun updateStep(newStepState: ChildLoginStepState) {
        Log.d(TAG, "updateStep: $newStepState")
        when (newStepState) {
            ChildLoginStepState.CODE -> {}
            ChildLoginStepState.NAME -> codeEntered()
            ChildLoginStepState.AGE -> nameEntered()
            ChildLoginStepState.FINAL -> ageEntered()
        }
    }

    private fun codeEntered() = viewModelScope.launch {
        Log.d(TAG, "codeEntered: $studentCode")
        codeRequest = RequestState.Loading
        codeRequest = codeRepository.getPrivateCode(studentCode)
            .also { response ->
                if (response is RequestState.Success) {
                    if (response.data.childId.isNotBlank()) {
                        login(response.data.childId)
                    } else {
                        checkPublicCode()
                    }
                } else if (response is RequestState.Error) {
                    response.apply {
                        resetCode(ChildToast.CodeNotValid)
                        Log.e(TAG, "${error.message}")
                    }
                }
            }
    }

    private suspend fun checkPublicCode() {
        Log.d(TAG, "checkPublicCode")
        codeRequest = codeRepository.getPublicCode(studentCode)
            .also { response ->
                if (response is RequestState.Success) {
                    checkCodeTime(response.data)
                } else if (response is RequestState.Error) {
                    response.apply {
                        resetCode(ChildToast.CodeNotValid)
                        Log.e(TAG, "${error.message}")
                    }
                }
            }
    }

    private fun login(childId: String) {
        Log.d(TAG, "login: $childId")
        // TODO - save child id to data store preference
        persistUserType()
    }

    private fun saveUserToDatabase() = viewModelScope.launch {
        Log.d(TAG, "saveUserToDatabase")
        saveUserRequest = RequestState.Loading
        saveUserRequest = usersRepository.createChild(
            Child(
                id = childId,
                privateCode = privateCode,
                name = studentName,
                age = studentAge.toInt(),
            )
        ).also { response ->
            if (response is RequestState.Success) {
                if (response.data) {
                    saveLinkedAccounts()
                }
            } else if (response is RequestState.Error) {
                response.apply {
                    toastLauncher.launch(ChildToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun saveLinkedAccounts() = viewModelScope.launch {
        Log.d(TAG, "saveLinkedAccounts")
        saveLinkedAccountsRequest = RequestState.Loading
        saveLinkedAccountsRequest = linkedAccountsRepository.addChild(
            LinkedAccounts(
                supervisorId = supervisorId,
                childrenId = childId,
            )
        ).also { response ->
            if (response is RequestState.Success) {
                if (response.data) {
                    // TODO - save child id to data store preference
                    persistUserType()
                }
            } else if (response is RequestState.Error) {
                response.apply {
                    toastLauncher.launch(ChildToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun persistUserType() {
        Log.d(TAG, "persistUserType - Teacher")
        viewModelScope.launch(Dispatchers.IO) {
            userTypeRepository.persistUserType(UserType.Child)
        }
    }

    private fun createPrivateCode() = viewModelScope.launch {
        Log.d(TAG, "createPrivateCode")
        codeRepository.createPrivateCode(
            PrivateCode(
                code = privateCode,
                supervisorId = supervisorId,
                childId = childId,
            )
        ).also { response ->
            if (response is RequestState.Success) {
                saveUserToDatabase()
            } else if (response is RequestState.Error) {
                response.apply {
                    toastLauncher.launch(ChildToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun ageEntered() {
        Log.d(TAG, "ageEntered: $studentAge")
        if (studentAge.isBlank()) {
            toastLauncher.launch(ChildToast.FillYourAge)
            studentAge = ""
        } else {
            stepState = ChildLoginStepState.FINAL
            createPrivateCode()
        }
    }

    private fun nameEntered() {
        Log.d(TAG, "nameEntered: $studentName")
        if (studentName.length < 2) {
            toastLauncher.launch(ChildToast.FillYourName)
            studentName = ""
        } else {
            stepState = ChildLoginStepState.AGE
        }
    }

    private fun checkCodeTime(code: PublicCode) {
        Log.d(TAG, "checkCodeTime: $code")
        if (code.timeMilli + 300_000 > currentTime) {
            supervisorId = code.supervisorId
            stepState = ChildLoginStepState.NAME
        } else {
            resetCode(ChildToast.CodeExpired)
        }
    }

    private fun resetCode(toast: ChildToast) {
        Log.d(TAG, "resetCode: $toast")
        toastLauncher.launch(toast)
        studentCode = ""
    }

    private fun updateName(name: String) {
        Log.d(TAG, "updateName: $name")
        studentName = name
    }

    private fun updateTextCode(code: String) {
        Log.d(TAG, "updateTextCode: $code")
        studentCode = code
    }

    private fun updateAge(age: String) {
        Log.d(TAG, "updateAge: $age")
        studentAge = age
    }

}