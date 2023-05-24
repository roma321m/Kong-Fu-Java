package dorin_roman.app.kongfujava.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import javax.annotation.Untainted

@Composable
fun LevelButtons(modifier: Modifier, onClickHint: () -> Unit, OnClickNext: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.Lightbulb,
            contentDescription = stringResource(id = R.string.hint),
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(10.dp)
                .size(40.dp)
                .clickable {
                    onClickHint()
                }
        )

        Icon(
            imageVector = Icons.Default.SkipNext,
            contentDescription = stringResource(id = R.string.next),
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(10.dp)
                .size(40.dp)
                .clickable {
                    OnClickNext()
                }
        )

    }
}