package dorin_roman.app.kongfujava.screens.supervisor.progress_report.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.ui.components.shimmer.shimmerEffect


@Composable
fun SupervisorProgressReportUserInfoShimmer() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.03f))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .fillMaxHeight(0.1f)
                .shimmerEffect(),
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .fillMaxHeight(0.1f)
                        .shimmerEffect(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .fillMaxHeight(0.1f)
                        .shimmerEffect(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .fillMaxHeight(0.1f)
                        .shimmerEffect(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .fillMaxHeight(0.1f)
                        .shimmerEffect(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .fillMaxHeight(0.1f)
                        .shimmerEffect(),
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .fillMaxHeight(0.5f)
                    .shimmerEffect(),
            )
        }
    }
}