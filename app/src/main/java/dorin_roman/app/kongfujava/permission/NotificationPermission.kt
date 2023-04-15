package dorin_roman.app.kongfujava.permission

import android.Manifest
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import dorin_roman.app.kongfujava.screens.supervisor.add_users.SupervisorAddUsersEvent
import dorin_roman.app.kongfujava.screens.supervisor.add_users.SupervisorAddUsersViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationPermission(
    viewModel: SupervisorAddUsersViewModel
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val notificationPermissionState = rememberPermissionState(
            permission = Manifest.permission.POST_NOTIFICATIONS
        )
        when (notificationPermissionState.status) {
            PermissionStatus.Granted -> {
                viewModel.handle(SupervisorAddUsersEvent.CanShowNotification(true))
            }

            is PermissionStatus.Denied -> {
                LaunchedEffect(true) {
                    notificationPermissionState.launchPermissionRequest()
                }
            }
        }
    } else {
        viewModel.handle(SupervisorAddUsersEvent.CanShowNotification(true))
    }
}