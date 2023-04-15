package dorin_roman.app.kongfujava.screens.supervisor.add_users

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SupervisorAddUsersViewModel @Inject constructor(

) : ViewModel() {

    companion object {
        const val TAG = "SupervisorAddUsersViewModel"
    }

    var canShowNotification by mutableStateOf(false)
        private set

    fun handle(event: SupervisorAddUsersEvent) {
        when (event) {
            is SupervisorAddUsersEvent.CanShowNotification -> updateCanShowNotifications(event.state)
        }
    }

    private fun updateCanShowNotifications(state: Boolean) {
        Log.d(TAG, "updateCanShowNotifications: $state")
        canShowNotification = state
    }
}