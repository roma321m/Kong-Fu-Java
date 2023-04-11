package dorin_roman.app.kongfujava.screens.supervisor.components.top_bar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.Title

@Composable
fun SupervisorTopBar(
    modifier: Modifier = Modifier,
    title: Int,
    onOpenDrawer: () -> Unit,
    onLogOutClicked: () -> Unit,
    onRevokeAccessClicked: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Title(id = title, modifier = Modifier)
        },
        navigationIcon = {
            IconButton(
                onClick = { onOpenDrawer() }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        },
        actions = {
            MoreOptions(
                onLogOutClicked = onLogOutClicked,
                onRevokeAccessClicked = onRevokeAccessClicked
            )
        },
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onSecondary,
    )
}