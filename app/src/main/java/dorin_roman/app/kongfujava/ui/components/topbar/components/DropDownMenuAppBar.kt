package dorin_roman.app.kongfujava.ui.components.topbar.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.MusicOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun DropDownMenuAppBar(
    showMenu: Boolean,
    isPlaying: Boolean,
    onDismissRequest: () -> Unit,
    onLogOutClicked: () -> Unit,
    onHelpClicked: () -> Unit,
    onMusicClicked: () -> Unit,
) {
    DropdownMenu(
        modifier = Modifier.padding(MaterialTheme.spacing.medium),
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
                    imageVector = Icons.Default.Info,
                    contentDescription = stringResource(id = R.string.top_bar_about),
                    tint = MaterialTheme.colors.onBackground,
                )
                Text(
                    modifier = Modifier.padding(MaterialTheme.spacing.large),
                    text = stringResource(id = R.string.top_bar_about),
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
                    imageVector = if (isPlaying) {
                        Icons.Default.MusicOff
                    } else {
                        Icons.Default.MusicNote
                    },
                    contentDescription = stringResource(id = R.string.top_bar_music),
                    tint = MaterialTheme.colors.onBackground,
                )
                Text(
                    modifier = Modifier.padding(MaterialTheme.spacing.large),
                    text = stringResource(id = R.string.top_bar_music),
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
                    contentDescription = stringResource(id = R.string.top_bar_logout),
                    tint = MaterialTheme.colors.onBackground,
                )
                Text(
                    modifier = Modifier.padding(MaterialTheme.spacing.large),
                    text = stringResource(id = R.string.top_bar_logout),
                    style = MaterialTheme.typography.h6
                )
            }
        }

    }
}