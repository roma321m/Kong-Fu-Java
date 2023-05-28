package dorin_roman.app.kongfujava.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.mediumGray

@Composable
fun LevelButtons(
    onClickHint: () -> Unit,
    OnClickNext: () -> Unit,
    hintsCount: Int,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    var columnWidthDp by remember {
        mutableStateOf(0.dp)
    }

    Row(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                columnWidthDp = with(density) { coordinates.size.width.toDp() }
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier
                .fillMaxHeight()
                .width((columnWidthDp - 10.dp) / 2)
                .then(
                    if (hintsCount < 3) {
                        Modifier.clickable {
                            onClickHint()
                        }
                    } else {
                        Modifier
                    }
                ),
            imageVector = Icons.Default.Lightbulb,
            contentDescription = stringResource(id = R.string.hint),
            tint = if (hintsCount < 3) {
                MaterialTheme.colors.primary
            } else {
                MaterialTheme.colors.mediumGray
            }
        )

        Icon(
            modifier = Modifier
                .fillMaxHeight()
                .width((columnWidthDp - 10.dp) / 2)
                .mirror()
                .clickable {
                    OnClickNext()
                },
            imageVector = Icons.Default.SkipNext,
            contentDescription = stringResource(id = R.string.next),
            tint = MaterialTheme.colors.primary,
        )

    }
}