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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun CustomLayout4(
    startTopLayout: @Composable () -> Unit,
    startBottomLayout: @Composable () -> Unit,
    endTopLayout: @Composable () -> Unit,
    endBottomLayout: @Composable () -> Unit,
    startWeight: Float = 0.5f,
    endWeight: Float = 0.5f,
    startTopWeight: Float = 0.5f,
    startBottomWeight: Float = 0.5f,
    endTopWeight: Float = 0.5f,
    endBottomWeight: Float = 0.5f,
) {
    Surface {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(startWeight)
                    .fillMaxHeight(),
            ) {
                Box(
                    modifier = Modifier
                        .weight(startTopWeight)
                        .fillMaxWidth(),
                    content = {
                        startTopLayout()
                    }
                )
                Box(
                    modifier = Modifier
                        .weight(startBottomWeight)
                        .fillMaxWidth(),
                    content = {
                        startBottomLayout()
                    }
                )
            }
            Column(
                modifier = Modifier
                    .weight(endWeight)
                    .fillMaxHeight(),
            ) {
                Box(
                    modifier = Modifier
                        .weight(endTopWeight)
                        .fillMaxWidth(),
                    content = {
                        endTopLayout()
                    }
                )
                Box(
                    modifier = Modifier
                        .weight(endBottomWeight)
                        .fillMaxWidth(),
                    content = {
                        endBottomLayout()
                    }
                )
            }
        }
    }
}

@Preview(
    device = Devices.NEXUS_9,
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun CustomLayout4Preview() {
    CustomLayout4(
        startTopLayout = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue)
            )
        },
        startBottomLayout = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
            )
        },
        endTopLayout = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
            )
        },
        endBottomLayout = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
            )
        }
    )
}