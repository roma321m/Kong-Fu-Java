package dorin_roman.app.kongfujava.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HorizontalFortySixtyLayout(
    fortyLayout: @Composable () -> Unit,
    sixtyLayout: @Composable () -> Unit
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
            ) {
                fortyLayout()
            }
            Box(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary),
                contentAlignment = Alignment.Center
            ) {
                sixtyLayout()
            }
        }
    }
}