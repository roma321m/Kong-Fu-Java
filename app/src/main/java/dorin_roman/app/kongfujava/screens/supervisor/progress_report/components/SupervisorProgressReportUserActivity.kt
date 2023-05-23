package dorin_roman.app.kongfujava.screens.supervisor.progress_report.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.graphs.BarGraph
import dorin_roman.app.kongfujava.ui.theme.elevation
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun SupervisorProgressReportUserActivity(
    data: Map<String, Int>,
    isLoading: Boolean = false,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
    ) {
        Card(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large,
                    top = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.large
                )
                .fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.small,
            elevation = MaterialTheme.elevation.small
        ) {
            if (isLoading) {
                SupervisorProgressReportUserActivityShimmer()
            } else {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.spacing.large),
                        style = MaterialTheme.typography.h5,
                        text = stringResource(R.string.activity),
                        textAlign = TextAlign.Center
                    )

                    if (data.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(bottom = MaterialTheme.spacing.large),
                                style = MaterialTheme.typography.h5,
                                text = stringResource(R.string.no_available_data),
                                textAlign = TextAlign.Center,
                            )
                        }
                    } else {
                        BarGraph(
                            data = data,
                            barColor = MaterialTheme.colors.primary
                        )
                    }
                }
            }
        }
    }
}
