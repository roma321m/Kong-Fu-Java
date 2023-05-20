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
    // fixme - not showing correctly
    data: List<Pair<Int, String>>,
    roundType: BarType,
    barWidth: Dp,
    barColor: Color,
    barArrangement: Arrangement.Horizontal,
    animDuration: Int = 2000,
) {
    // set data
    val barData by remember {
        mutableStateOf(data.map { it.first } + 0)
    }
    val max = data.maxBy { it.first }
    val graphBarData = mutableListOf<Float>()
    val xAxisScaleData = mutableListOf<String>()
    data.forEach { pair ->
        graphBarData.add(pair.first.toFloat() / max.first.toFloat())
        xAxisScaleData.add(pair.second)
    }

    // for getting screen width and height you can use LocalConfiguration
    val configuration = LocalConfiguration.current
    // getting screen width
    val width = configuration.screenWidthDp.dp

    // bottom height of the X-Axis Scale
    val xAxisScaleHeight = 40.dp

    val yAxisScaleSpacing by remember {
        mutableStateOf(100f)
    }
    val yAxisTextWidth by remember {
        mutableStateOf(100.dp)
    }

    // bar shape
    val barShap =
        when (roundType) {
            BarType.CIRCULAR_TYPE -> CircleShape
            BarType.TOP_CURVED -> RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp)
        }

    val density = LocalDensity.current
    val hashColor = MaterialTheme.colors.onBackground.hashCode()
    // y-axis scale text paint
    val textPaint = remember(density) {
        Paint().apply {
            color = hashColor
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    // for y coordinates of y-axis scale to create horizontal dotted line indicating y-axis scale
    val yCoordinates = mutableListOf<Float>()
    // for dotted line effect
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    // height of vertical line over x-axis scale connecting x-axis horizontal line
    val lineHeightXAxis = 10.dp
    // height of horizontal line over x-axis scale
    val horizontalLineHeight = 5.dp


    val localDensity = LocalDensity.current
    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }

    Box(
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                columnHeightDp =
                    with(localDensity) { coordinates.size.height.toDp() - 30.dp } // fixme -30 temp to see
            }
    ) {
        if (columnHeightDp != 0.dp) {
            // y-axis scale and horizontal dotted lines on graph indicating y-axis scale
            Column(
                modifier = Modifier
                    .padding(top = xAxisScaleHeight, end = 3.dp)
                    .fillMaxSize(),
                horizontalAlignment = CenterHorizontally
            ) {

                Canvas(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxSize()
                ) {

                    // Y-Axis Scale Text
                    val yAxisScaleText = (barData.max()) / 3f
                    (0..3).forEach { i ->
                        drawContext.canvas.nativeCanvas.apply {
                            drawText(
                                round(barData.min() + yAxisScaleText * i).toString(),
                                30f,
                                size.height - yAxisScaleSpacing - i * size.height / 3f,
                                textPaint
                            )
                        }
                        yCoordinates.add(size.height - yAxisScaleSpacing - i * size.height / 3f)
                    }

                    // horizontal dotted lines on graph indicating y-axis scale
                    (1..3).forEach {
                        drawLine(
                            start = Offset(x = yAxisScaleSpacing + 30f, y = yCoordinates[it]),
                            end = Offset(x = size.width, y = yCoordinates[it]),
                            color = Color.Gray,
                            strokeWidth = 5f,
                            pathEffect = pathEffect
                        )
                    }

                }

            }

            // Graph with Bar Graph and X-Axis Scale
            Box(
                modifier = Modifier
                    .padding(start = 50.dp)
                    .width(width - yAxisTextWidth)
                    .height(columnHeightDp + xAxisScaleHeight),
                contentAlignment = BottomCenter
            ) {

                Row(
                    modifier = Modifier
                        .width(width - yAxisTextWidth),
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
                            animationTriggered = true
                        }

                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Top,
                            horizontalAlignment = CenterHorizontally
                        ) {

                            // Each Graph
                            Box(
                                modifier = Modifier
                                    .padding(bottom = 5.dp)
                                    .clip(barShap)
                                    .width(barWidth)
                                    .height(columnHeightDp - 10.dp)
                                    .background(Color.Transparent),
                                contentAlignment = BottomCenter
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(barShap)
                                        .fillMaxWidth()
                                        .fillMaxHeight(graphBarHeight)
                                        .background(barColor)
                                )
                            }

                            // scale x-axis and bottom part of graph
                            Column(
                                modifier = Modifier
                                    .height(xAxisScaleHeight),
                                verticalArrangement = Top,
                                horizontalAlignment = CenterHorizontally
                            ) {

                                // small vertical line joining the horizontal x-axis line
                                Box(
                                    modifier = Modifier
                                        .clip(
                                            RoundedCornerShape(
                                                bottomStart = 2.dp,
                                                bottomEnd = 2.dp
                                            )
                                        )
                                        .width(horizontalLineHeight)
                                        .height(lineHeightXAxis)
                                        .background(color = Color.Gray)
                                )

                                // scale x-axis
                                Text(
                                    modifier = Modifier.padding(bottom = 3.dp),
                                    text = xAxisScaleData[index],
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                // horizontal line on x-axis below the graph
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = xAxisScaleHeight + 3.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .fillMaxWidth()
                            .height(horizontalLineHeight)
                            .background(Color.Gray)
                    )
                }
            }
        }
    }
}

enum class BarType {
    CIRCULAR_TYPE,
    TOP_CURVED
}