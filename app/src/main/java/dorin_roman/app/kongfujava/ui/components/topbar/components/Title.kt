package dorin_roman.app.kongfujava.ui.components.topbar.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.onTopBar
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun Title(
    @StringRes id: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = id).uppercase(),
            style = MaterialTheme.typography.h4.copy(
                color = MaterialTheme.colors.onTopBar
            )
        )
        Image(
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
                .size(80.dp, 80.dp),
            painter = painterResource(id = R.drawable.ic_panda_ninja),
            contentDescription = null
        )
    }
}