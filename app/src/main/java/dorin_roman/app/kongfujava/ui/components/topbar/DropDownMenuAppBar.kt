package dorin_roman.app.kongfujava.ui.components.topbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R

@Composable
fun DropDownMenuAppBar(
    showMenu: Boolean,
    onDismissRequest: () -> Unit,
    onLogOutClicked: () -> Unit,
    onHelpClicked: () -> Unit,
    onMusicClicked: () -> Unit,
) {
    DropdownMenu(
        modifier = Modifier.padding(8.dp),
        expanded = showMenu,
        onDismissRequest = onDismissRequest
    ) {
        DropdownMenuItem(onClick = onHelpClicked) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.Help,
                    contentDescription = stringResource(id = R.string.help),
                    tint = MaterialTheme.colors.onBackground,
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = stringResource(id = R.string.help),
                    style = MaterialTheme.typography.h6
                )
            }
        }

        DropdownMenuItem(onClick = onMusicClicked) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = stringResource(id = R.string.music),
                    tint = MaterialTheme.colors.onBackground,
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = stringResource(id = R.string.music),
                    style = MaterialTheme.typography.h6
                )
            }
        }

        DropdownMenuItem(onClick = onLogOutClicked) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.Logout,
                    contentDescription = stringResource(id = R.string.logout),
                    tint = MaterialTheme.colors.onBackground,
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = stringResource(id = R.string.logout),
                    style = MaterialTheme.typography.h6
                )
            }
        }

    }
}