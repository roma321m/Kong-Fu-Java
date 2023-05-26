package dorin_roman.app.kongfujava.screens.top_bar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.data.repository.ChildIdRepository
import dorin_roman.app.kongfujava.data.repository.UserTypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopBarViewModel @Inject constructor(
    private val childIdRepository: ChildIdRepository,
    private val userTypeRepository: UserTypeRepository
) : ViewModel() {

    companion object {
        const val TAG = "WorldsMapViewModel"
    }

    fun handle(event: TopBarEvent) {
        when (event) {
            TopBarEvent.LogOut -> logout()
        }
    }

    private fun logout() {
        Log.d(TAG, "logout")
        persistChildId()
        persistUserType()
    }

    private fun persistChildId() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "persistChildId")
        childIdRepository.persistChildId("")
    }

    private fun persistUserType() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "persistUserType")
        userTypeRepository.persistUserType(UserType.None)
    }

}