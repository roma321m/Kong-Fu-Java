package dorin_roman.app.kongfujava.ui.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun CustomLayout5(
    itemContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    numberOfRows: Int = 6,
    numberOfColumns: Int = 5,
) {
    val density = LocalDensity.current
    var itemHeightDp by remember {
        mutableStateOf(140.dp)
    }
    var itemWidthDp by remember {
        mutableStateOf(171.dp)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                itemHeightDp = with(density) { (coordinates.size.height / numberOfRows).toDp() }
                itemWidthDp = with(density) { (coordinates.size.height / numberOfColumns).toDp() }
            }
    ) {
        if (itemHeightDp != 0.dp && itemWidthDp != 0.dp) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                repeat(numberOfRows) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(itemWidthDp)
                    ) {
                        repeat(numberOfColumns) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(itemHeightDp)
                            ) {
                                itemContent()
                            }
                        }
                    }
                }
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
fun CustomLayout5Preview() {
    CustomLayout5(
        itemContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(getRandomColor())
            )
        }
    )
}

fun getRandomColor(): Color {
    return Color(
        red = Random.nextInt(256),
        green = Random.nextInt(256),
        blue = Random.nextInt(256),
        alpha = 255
    )
}