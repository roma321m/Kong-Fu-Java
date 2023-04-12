package dorin_roman.app.kongfujava.screens.supervisor.components.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SupervisorDrawerAddUsers(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    isAddUsers: Boolean,
    onAddUsersSelected: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        SupervisorDrawerMenuItem(
            imageVector = Icons.Filled.Create,
            text = stringResource(R.string.add_users),
            onItemClick = {
                onAddUsersSelected(true)
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            },
            selected = isAddUsers
        )
        Divider(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(0.3f),
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.4f),
            thickness = 1.dp
        )
    }
}