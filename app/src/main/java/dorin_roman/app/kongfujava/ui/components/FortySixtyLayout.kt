package dorin_roman.app.kongfujava.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun FortySixtyLayout(
    fortyLayout: @Composable () -> Unit,
    sixtyLayout: @Composable () -> Unit
) {
    Surface {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight()
                    .background(MaterialTheme.colors.background)
            ) {
                fortyLayout()
            }
            Box(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxHeight()
                    .background(MaterialTheme.colors.primary),
                contentAlignment = Alignment.Center
            ) {
                sixtyLayout()
            }
        }
    }
}