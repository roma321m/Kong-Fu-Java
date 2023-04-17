package dorin_roman.app.kongfujava

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.data.repository.UserTypeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userTypeRepository: UserTypeRepository
) : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private val _userType =
        MutableStateFlow<RequestState<UserType>>(RequestState.Idle)
    val userType: StateFlow<RequestState<UserType>> = _userType

    init {
        Log.d(TAG, "init")
        readUserType()
    }

    private fun readUserType() {
        Log.d(TAG, "readUserType")
        _userType.value = RequestState.Loading
        try {
            viewModelScope.launch {
                userTypeRepository.readUserType
                    .map { UserType.valueOf(it) }
                    .collect { _userType.value = RequestState.Success(it) }
            }
        } catch (exception: Exception) {
            _userType.value = RequestState.Error(exception)
        }
    }

}