package dorin_roman.app.kongfujava.ui.components.graphs

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import dorin_roman.app.kongfujava.screens.supervisor.progress_report.getWorldName
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun PieGraph(
    modifier: Modifier,
    data: Map<String, Int>,
    baseColor1: Int = MaterialTheme.colors.primary.toArgb(),
    baseColor2: Int = MaterialTheme.colors.secondary.toArgb(),
    radiusOuter: Dp = 90.dp,
    chartBarWidth: Dp = 20.dp,
    animDuration: Int = 2000,
) {

    val totalSum = data.values.sum()
    val floatValue = mutableListOf<Float>()

    data.values.forEachIndexed { index, values ->
        floatValue.add(index, 360 * values.toFloat() / totalSum.toFloat())
    }

    val colors = mutableListOf<Color>()
    repeat(data.size) {
        colors.add(
            Color(
                ColorUtils.blendARGB(
                    baseColor1,
                    baseColor2,
                    1 / data.size.toFloat() * it.toFloat()
                )
            )
        )
    }

    var animationPlayed by remember { mutableStateOf(false) }

    var lastValue = 0f

    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) radiusOuter.value * 2f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 90f * 11f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier.size(animateSize.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${data.map { it.value }.sum()}",
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
            )
            Canvas(
                modifier = Modifier
                    .size(radiusOuter * 2f)
                    .rotate(animateRotation)
            ) {
                floatValue.forEachIndexed { index, value ->
                    drawArc(
                        color = colors[index],
                        startAngle = lastValue,
                        sweepAngle = value,
                        useCenter = false,
                        style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                    )
                    lastValue += value
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(MaterialTheme.spacing.large)
                .padding(start = MaterialTheme.spacing.large)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(MaterialTheme.spacing.small)
        ) {
            items(data.size) { index ->
                val s = data.keys.elementAt(index).split("-")
                var key = data.keys.elementAt(index)
                if (s.size == 2) {
                    key = stringResource(getWorldName(s.first().toInt())) + "-" + s.last()
                }
                DetailsPieChartItem(
                    data = Pair(
                        key,
                        data.values.elementAt(index)
                    ),
                    color = colors[index]
                )
            }
        }
    }
}

@Composable
internal fun DetailsPieChartItem(
    data: Pair<String, Int>,
    color: Color
) {
    Row(
        modifier = Modifier
            .padding(start = MaterialTheme.spacing.extraLarge)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = color,
                    shape = RoundedCornerShape(4.dp)
                )
                .size(20.dp)
        )
        Text(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.medium),
            text = data.first,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.medium),
            text = data.second.toString(),
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.6f)
        )
    }
}