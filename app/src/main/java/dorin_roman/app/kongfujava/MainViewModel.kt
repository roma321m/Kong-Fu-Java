package dorin_roman.app.kongfujava

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.DateStoreRequestState
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.data.repository.UserTypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userTypeRepository: UserTypeRepository
) : ViewModel() {

    private val _userType =
        MutableStateFlow<DateStoreRequestState<UserType>>(DateStoreRequestState.Idle)
    val userType: StateFlow<DateStoreRequestState<UserType>> = _userType

    init {
        readUserType()
    }

    fun handle(event: MainEvent) {
        when (event) {
            MainEvent.Child -> persistUserType(UserType.Child)
            MainEvent.Parent -> persistUserType(UserType.Parent)
            MainEvent.Teacher -> persistUserType(UserType.Teacher)
        }
    }

    private fun persistUserType(userType: UserType) {
        viewModelScope.launch(Dispatchers.IO) {
            userTypeRepository.persistUserType(userType)
        }
    }

    private fun readUserType() {
        _userType.value = DateStoreRequestState.Loading
        try {
            viewModelScope.launch {
                userTypeRepository.readUserType
                    .map { UserType.valueOf(it) }
                    .collect { _userType.value = DateStoreRequestState.Success(it) }
            }
        } catch (exception: Exception) {
            _userType.value = DateStoreRequestState.Error(exception)
        }
    }

}