package dorin_roman.app.kongfujava.screens.login.child

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.logic.LevelLogic
import dorin_roman.app.kongfujava.data.logic.WorldLogic
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.data.repository.ChildIdRepository
import dorin_roman.app.kongfujava.data.repository.LevelRepository
import dorin_roman.app.kongfujava.data.repository.UserTypeRepository
import dorin_roman.app.kongfujava.data.repository.WorldRepository
import dorin_roman.app.kongfujava.di.provider.CodeProvider
import dorin_roman.app.kongfujava.di.provider.IdProvider
import dorin_roman.app.kongfujava.domain.models.LinkedAccounts
import dorin_roman.app.kongfujava.domain.models.child_stats.LevelStats
import dorin_roman.app.kongfujava.domain.models.codes.Code
import dorin_roman.app.kongfujava.domain.models.codes.PrivateCode
import dorin_roman.app.kongfujava.domain.models.codes.PublicCode
import dorin_roman.app.kongfujava.domain.models.users.Child
import dorin_roman.app.kongfujava.domain.repository.ChildStatsRepository
import dorin_roman.app.kongfujava.domain.repository.CodeRepository
import dorin_roman.app.kongfujava.domain.repository.LinkedAccountsRepository
import dorin_roman.app.kongfujava.domain.repository.ProfileImageRepository
import dorin_roman.app.kongfujava.domain.repository.UsersRepository
import dorin_roman.app.kongfujava.ui.toast.ToastLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChildLoginViewModel @Inject constructor(
    idProvider: IdProvider,
    codeProvider: CodeProvider,
    private val childIdRepository: ChildIdRepository,
    private val profileImageRepository: ProfileImageRepository,
    private val codeRepository: CodeRepository,
    private val userTypeRepository: UserTypeRepository,
    private val usersRepository: UsersRepository,
    private val linkedAccountsRepository: LinkedAccountsRepository,
    private val childStatsRepository: ChildStatsRepository,
    private val worldRepository: WorldRepository,
    private val levelRepository: LevelRepository,
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

    private var studentImage: Uri by mutableStateOf(Uri.EMPTY)

    private var imageUrl: String = ""

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
            is ChildLoginEvent.OnImageChange -> updateImage(event.imageUri)
            is ChildLoginEvent.OnNextClick -> updateStep(event.step)
        }
    }

    private fun updateStep(newStepState: ChildLoginStepState) {
        Log.d(TAG, "updateStep: $newStepState")
        when (newStepState) {
            ChildLoginStepState.CODE -> {}
            ChildLoginStepState.NAME -> codeEntered()
            ChildLoginStepState.AGE -> nameEntered()
            ChildLoginStepState.IMAGE -> ageEntered()
            ChildLoginStepState.FINAL -> imageEntered()
        }
    }

    private fun codeEntered() = viewModelScope.launch {
        Log.d(TAG, "codeEntered: $studentCode")
        codeRequest = RequestState.Loading
        codeRequest = codeRepository.getPrivateCode(studentCode)
            .also { response ->
                if (response is RequestState.Success) {
                    if (response.data.childId.isNotBlank()) {
                        loadLevels(response.data.childId)
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

    private fun loadLevels(childId: String) = viewModelScope.launch {
        Log.d(TAG, "login to: $childId")
        Log.d(TAG, "private code login step 1: loadLevels")
        childStatsRepository.getLevelStatsList(
            childId
        ).also { response ->
            if (response is RequestState.Success) {
                updateLocalDB(childId, response.data)
            } else if (response is RequestState.Error) {
                response.apply {
                    toastLauncher.launch(ChildToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun updateLocalDB(childId: String, data: List<LevelStats>) {
        Log.d(TAG, "private code login step 2: updateLocalDB")
        if (data.isEmpty()) {
            persistChildId(childId)
            return
        }
        updateLocalDBWorlds(childId, data)
    }

    private fun updateLocalDBWorlds(
        childId: String,
        data: List<LevelStats>
    ) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "private code login step 3: updateLocalDBWorlds")
        var worldScore = 0
        data.forEach { levelStats ->
            worldScore += levelStats.stars
        }
        val worldState = WorldLogic.getWorldStateByScore(worldScore)
        worldRepository.updateWorld(
            id = 0, // fixme - only for world 1
            state = worldState.ordinal,
            score = worldScore
        ).also {
            updateLocalDBLevels(childId, data)
        }
    }

    private fun updateLocalDBLevels(
        childId: String,
        data: List<LevelStats>
    ) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "private code login step 4: updateLocalDBLevels score and state")
        data.forEach { levelStats ->
            val state = LevelLogic.getLevelStateByScore(levelStats.stars)
            levelRepository.updateLevelScore(levelStats.id, levelStats.stars)
            levelRepository.updateLevelState(levelStats.id, state.ordinal)
        }.also {
            if (data.size != 30) { // fixme - only for world 1
                openLastLevel(childId, data.size) // fixme - only for world 1
            } else {
                persistChildId(childId)
            }
        }
    }

    private fun openLastLevel(
        childId: String,
        id: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "private code login step 5: openLastLevel")
        levelRepository.updateLevelState(id, PointState.ZERO.ordinal).also {
            persistChildId(childId)
        }
    }

    private fun uploadImage() = viewModelScope.launch {
        Log.d(TAG, "public code login step 1: uploadImage")
        profileImageRepository.addImageToCouldStorage(
            uid = childId,
            imageUri = studentImage
        ).also { response ->
            if (response is RequestState.Success) {
                imageUrl = response.data
                createPrivateCode()
            } else if (response is RequestState.Error) {
                response.apply {
                    toastLauncher.launch(ChildToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun createPrivateCode() = viewModelScope.launch {
        Log.d(TAG, "public code login step 2: createPrivateCode")
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

    private fun saveUserToDatabase() = viewModelScope.launch {
        Log.d(TAG, "public code login step 3: saveUserToDatabase")
        saveUserRequest = RequestState.Loading
        saveUserRequest = usersRepository.createChild(
            Child(
                id = childId,
                imageUrl = imageUrl,
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
        Log.d(TAG, "public code login step 4: saveLinkedAccounts")
        saveLinkedAccountsRequest = RequestState.Loading
        saveLinkedAccountsRequest = linkedAccountsRepository.addChild(
            LinkedAccounts(
                supervisorId = supervisorId,
                childrenId = childId,
            )
        ).also { response ->
            if (response is RequestState.Success) {
                if (response.data) {
                    persistChildId(childId)
                }
            } else if (response is RequestState.Error) {
                response.apply {
                    toastLauncher.launch(ChildToast.SomethingWentWrong)
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun persistChildId(id: String) {
        Log.d(TAG, "login step: persistChildId")
        viewModelScope.launch(Dispatchers.IO) {
            childIdRepository.persistChildId(id)
        }.also {
            persistUserType()
        }
    }

    private fun persistUserType() {
        Log.d(TAG, "login step: persistUserType")
        viewModelScope.launch(Dispatchers.IO) {
            userTypeRepository.persistUserType(UserType.Child)
        }
    }

    private fun ageEntered() {
        Log.d(TAG, "ageEntered: $studentAge")
        if (studentAge.isBlank()) {
            toastLauncher.launch(ChildToast.FillYourAge)
            studentAge = ""
        } else {
            stepState = ChildLoginStepState.IMAGE
        }
    }

    private fun imageEntered() {
        if (studentImage == Uri.EMPTY) {
            toastLauncher.launch(ChildToast.AddAnImage)
        } else {
            stepState = ChildLoginStepState.FINAL
            uploadImage()
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

    private fun updateImage(imageUri: Uri) {
        Log.d(TAG, "updateImage: $imageUri")
        studentImage = imageUri
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