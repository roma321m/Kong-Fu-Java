package dorin_roman.app.kongfujava.ui.components.graphs

import android.graphics.Paint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dorin_roman.app.kongfujava.ui.theme.spacing
import kotlin.math.round

@Composable
fun BarGraph(
    data: Map<String, Int>,
    barColor: Color,
    barWidth: Dp = 20.dp,
    barArrangement: Arrangement.Horizontal = Arrangement.SpaceEvenly,
    roundType: BarType = BarType.TOP_CURVED,
    animDuration: Int = 2000,
) {
    val graphBarData = mutableListOf<Float>()
    val xAxisScaleData = mutableListOf<String>()
    data.forEach {
        graphBarData.add(it.value.toFloat() / data.values.max().toFloat())
        xAxisScaleData.add(it.key)
    }

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val width = configuration.screenWidthDp.dp

    val barShape = when (roundType) {
        BarType.CIRCULAR_TYPE -> CircleShape
        BarType.TOP_CURVED -> RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp)
    }

    val hashColor = MaterialTheme.colors.onBackground.hashCode()

    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }

    Box(
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                columnHeightDp = with(density) { coordinates.size.height.toDp() }
            }
    ) {
        if (columnHeightDp != 0.dp) {
            // Y-Axis Scale
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {

                val yCoordinates = mutableListOf<Float>()
                (0..3).forEach { i ->
                    // Y Text
                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            round((data.values.max().toFloat() / 3f) * i).toInt().toString(),
                            30.dp.toPx(),
                            size.height - 50.dp.toPx() - i * size.height / 4f,
                            Paint().apply {
                                color = hashColor
                                textAlign = Paint.Align.CENTER
                                textSize = density.run { 12.sp.toPx() }
                            }
                        )
                    }

                    yCoordinates.add(size.height - 55.dp.toPx() - i * size.height / 4f)

                    if (i == 0) {
                        // Y Base Line
                        drawLine(
                            start = Offset(x = 55.dp.toPx(), y = yCoordinates[i]),
                            end = Offset(x = size.width, y = yCoordinates[i]),
                            color = Color.Gray,
                            strokeWidth = 10f
                        )
                    } else {
                        // Y dot lines
                        drawLine(
                            start = Offset(x = 55.dp.toPx(), y = yCoordinates[i]),
                            end = Offset(x = size.width, y = yCoordinates[i]),
                            color = Color.Gray,
                            strokeWidth = 5f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                        )
                    }
                }
            }

            // X-Axis Scale
            Box(
                modifier = Modifier
                    .padding(start = 50.dp)
                    .width(width - 100.dp)
                    .height(columnHeightDp),
                contentAlignment = TopCenter
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = barArrangement
                ) {

                    // Graph
                    graphBarData.forEachIndexed { index, value ->

                        var animationTriggered by remember {
                            mutableStateOf(false)
                        }
                        val graphBarHeight by animateFloatAsState(
                            targetValue = if (animationTriggered) value else 0f,
                            animationSpec = tween(
                                durationMillis = animDuration,
                                delayMillis = 0
                            )
                        )
                        LaunchedEffect(key1 = true) {
                            if (value > 0) {
                                animationTriggered = true
                            }
                        }

                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Top,
                            horizontalAlignment = CenterHorizontally
                        ) {

                            // X Graph
                            Box(
                                modifier = Modifier
                                    .clip(barShape)
                                    .width(barWidth)
                                    .height(columnHeightDp - 55.dp)
                                    .background(Color.Transparent),
                                contentAlignment = BottomCenter
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(barShape)
                                        .fillMaxWidth()
                                        .fillMaxHeight(graphBarHeight)
                                        .background(barColor)
                                )
                            }

                            Column(
                                modifier = Modifier.height(55.dp),
                                verticalArrangement = Top,
                                horizontalAlignment = CenterHorizontally
                            ) {

                                // Small Y Line below base
                                Box(
                                    modifier = Modifier
                                        .clip(
                                            RoundedCornerShape(
                                                bottomStart = 2.dp,
                                                bottomEnd = 2.dp
                                            )
                                        )
                                        .width(5.dp)
                                        .height(10.dp)
                                        .background(color = Color.Gray)
                                )

                                // X text
                                Text(
                                    modifier = Modifier
                                        .padding(top = 15.dp)
                                        .height(40.dp),
                                    text = xAxisScaleData[index],
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class BarType {
    CIRCULAR_TYPE,
    TOP_CURVED
}