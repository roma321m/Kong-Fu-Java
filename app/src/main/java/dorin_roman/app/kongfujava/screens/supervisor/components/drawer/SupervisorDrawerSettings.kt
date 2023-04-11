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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
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
            text = stringResource(R.string.log_out),
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
            text = stringResource(R.string.revoke_access),
            onItemClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
                onRevokeAccessClicked()
            },
            selected = false
        )
    }
}