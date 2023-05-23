package dorin_roman.app.kongfujava.screens.supervisor.progress_report.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.ui.components.shimmer.shimmerEffect

@Composable
fun SupervisorProgressReportUserLevelsInfoShimmer() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.05f)
                .fillMaxHeight(0.5f)
                .shimmerEffect(),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.05f)
                .fillMaxHeight(0.3f)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.05f)
                .fillMaxHeight(0.7f)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.05f)
                .fillMaxHeight(0.8f)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.05f)
                .fillMaxHeight(0.4f)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.05f)
                .fillMaxHeight(0.5f)
                .shimmerEffect(),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.05f)
                .fillMaxHeight(0.3f)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.05f)
                .fillMaxHeight(0.7f)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.05f)
                .fillMaxHeight(0.8f)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.05f)
                .fillMaxHeight(0.4f)
                .shimmerEffect()
        )
    }
}