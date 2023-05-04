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
fun CustomLayout3(
    modifier: Modifier = Modifier,
    startContent: @Composable () -> Unit,
    endTopContent: @Composable () -> Unit,
    endBottomContent: @Composable () -> Unit,
    startWeight: Float = 0.6f,
    endWeight: Float = 0.4f,
    endTopWeight: Float = 0.5f,
    endBottomWeight: Float = 0.5f,
) {
    Surface(modifier) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(startWeight)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center,
                content = {
                    startContent()
                }
            )
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
fun CustomLayout3Preview() {
    CustomLayout3(
        startContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue)
            )
        },
        endTopContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
            )
        },
        endBottomContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
            )
        }
    )
}