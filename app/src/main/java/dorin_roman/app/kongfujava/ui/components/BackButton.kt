package dorin_roman.app.kongfujava.ui.components

import androidx.compose.foundation.clickable
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

@Composable
fun BackButton(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = stringResource(id = R.string.back_button),
        tint = MaterialTheme.colors.onBackground,
        modifier = modifier
            .size(40.dp)
            .clickable {
                onBackPressed()
            }
    )
}