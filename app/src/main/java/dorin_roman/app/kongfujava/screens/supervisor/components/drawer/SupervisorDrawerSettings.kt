package dorin_roman.app.kongfujava.screens.supervisor.components.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.popup.DisplayAlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SupervisorDrawerSettings(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    onLogOutClicked: () -> Unit,
    onRevokeAccessClicked: () -> Unit,
) {
    var openRevokeAccessDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(R.string.supervisor_revoke_access),
        message = stringResource(R.string.supervisor_revoke_access_dialog_content),
        openDialog = openRevokeAccessDialog,
        closeDialog = { openRevokeAccessDialog = false },
        onYesClicked = onRevokeAccessClicked
    )

    Column(
        modifier = modifier
    ) {
        Divider(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .fillMaxWidth(0.3f),
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.4f),
            thickness = 1.dp
        )
        SupervisorDrawerMenuItem(
            imageVector = Icons.Filled.Logout,
            text = stringResource(R.string.supervisor_log_out),
            onItemClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
                onLogOutClicked()
            },
            selected = false
        )
        SupervisorDrawerMenuItem(
            imageVector = Icons.Filled.DeleteForever,
            text = stringResource(R.string.supervisor_revoke_access),
            onItemClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
                openRevokeAccessDialog = true
            },
            selected = false
        )
    }
}