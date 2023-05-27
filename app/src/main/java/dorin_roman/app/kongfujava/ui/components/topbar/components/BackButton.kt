package dorin_roman.app.kongfujava.ui.components.topbar.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun BackButton(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier
            .padding(MaterialTheme.spacing.large)
            .size(40.dp)
            .clickable {
                onBackPressed()
            },
        imageVector = Icons.Default.ArrowBack,
        contentDescription = stringResource(id = R.string.top_bar_back_button),
        tint = MaterialTheme.colors.onSecondary,
    )
}