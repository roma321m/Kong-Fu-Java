package dorin_roman.app.kongfujava.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R

@Composable
fun BottomBar() {
    BottomAppBar(
        modifier = Modifier,
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onSecondary,
    ) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = stringResource(id = R.string.top_bar_settings),
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .padding(10.dp)
                .size(40.dp)
                .clickable {}
        )

        Icon(
            imageVector = Icons.Default.MusicNote,
            contentDescription = stringResource(id = R.string.top_bar_music),
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .padding(10.dp)
                .size(40.dp)
                .clickable {}
        )

        Icon(
            imageVector = Icons.Default.Help,
            contentDescription = stringResource(id = R.string.top_bar_help),
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .padding(10.dp)
                .size(40.dp)
                .clickable {}
        )
    }
}