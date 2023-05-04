package dorin_roman.app.kongfujava.ui.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun CustomLayout1(
    topStartLayout: @Composable () -> Unit,
    topEndLayout: @Composable () -> Unit,
    bottomLayout: @Composable () -> Unit,
    bottomWeight: Float = 0.6f,
    topWeight: Float = 0.4f,
    topStartWeight: Float = 0.5f,
    topEndWeight: Float = 0.5f,
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .weight(topWeight)
                    .fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .weight(topStartWeight)
                        .fillMaxHeight(),
                    content = {
                        topStartLayout()
                    }
                )
                Box(
                    modifier = Modifier
                        .weight(topEndWeight)
                        .fillMaxHeight(),
                    content = {
                        topEndLayout()
                    }
                )
            }
            Box(
                modifier = Modifier
                    .weight(bottomWeight)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
                content = {
                    bottomLayout()
                }
            )
        }
    }
}

@Preview(
    device = Devices.NEXUS_9,
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun CustomLayout1Preview() {
    CustomLayout1(
        topStartLayout = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue)
            )
        },
        topEndLayout = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
            )
        },
        bottomLayout = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
            )
        }
    )
}