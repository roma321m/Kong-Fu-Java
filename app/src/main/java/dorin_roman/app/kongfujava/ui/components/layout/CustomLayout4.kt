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
    modifier: Modifier = Modifier,
    startTopContent: @Composable () -> Unit,
    startBottomContent: @Composable () -> Unit,
    endTopContent: @Composable () -> Unit,
    endBottomContent: @Composable () -> Unit,
    startWeight: Float = 0.5f,
    endWeight: Float = 0.5f,
    startTopWeight: Float = 0.5f,
    startBottomWeight: Float = 0.5f,
    endTopWeight: Float = 0.5f,
    endBottomWeight: Float = 0.5f,
) {
    Surface(modifier) {
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
                        startTopContent()
                    }
                )
                Box(
                    modifier = Modifier
                        .weight(startBottomWeight)
                        .fillMaxWidth(),
                    content = {
                        startBottomContent()
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
                        endTopContent()
                    }
                )
                Box(
                    modifier = Modifier
                        .weight(endBottomWeight)
                        .fillMaxWidth(),
                    content = {
                        endBottomContent()
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
        startTopContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue)
            )
        },
        startBottomContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
            )
        },
        endTopContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
            )
        },
        endBottomContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
            )
        }
    )
}