package dorin_roman.app.kongfujava.ui.components.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R

@Composable
fun MenuButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Default.MoreVert,
        contentDescription = stringResource(id = R.string.top_bar_menu),
        tint = MaterialTheme.colors.onSecondary,
        modifier = modifier
            .padding(10.dp)
            .size(40.dp)
            .clickable {
                onClick()
            }
    )
}