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
fun CustomLayout2(
    modifier: Modifier = Modifier,
    startTopContent: @Composable () -> Unit,
    startBottomContent: @Composable () -> Unit,
    endContent: @Composable () -> Unit,
    startWeight: Float = 0.4f,
    endWeight: Float = 0.6f,
    startTopWeight: Float = 0.5f,
    startBottomWeight: Float = 0.5f,
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
            Box(
                modifier = Modifier
                    .weight(endWeight)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center,
                content = {
                    endContent()
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
fun CustomLayout2Preview() {
    CustomLayout2(
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
        endContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
            )
        }
    )
}