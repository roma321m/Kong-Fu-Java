package dorin_roman.app.kongfujava.screens.supervisor.components.top_bar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.Typography
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun MoreOptions(
    onLogOutClicked: () -> Unit,
    onRevokeAccessClicked: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.more_actions)
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            onClick = {
                expanded = false
                onLogOutClicked()
            }
        ) {
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.medium),
                text = stringResource(R.string.log_out),
                style = Typography.subtitle2
            )
        }
        DropdownMenuItem(
            onClick = {
                expanded = false
                onRevokeAccessClicked()
            }
        ) {
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.medium),
                text = stringResource(R.string.revoke_access),
                style = Typography.subtitle2
            )
        }
    }
}