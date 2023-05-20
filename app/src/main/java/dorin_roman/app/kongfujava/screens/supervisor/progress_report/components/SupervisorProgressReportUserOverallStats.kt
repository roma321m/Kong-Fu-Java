package dorin_roman.app.kongfujava.screens.supervisor.progress_report.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.graphs.PieGraph
import dorin_roman.app.kongfujava.ui.theme.elevation
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun SupervisorProgressReportUserOverallStats(
    data: Map<String, Int>,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
    ) {
        Card(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small,
                    top = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.large,
                    bottom = MaterialTheme.spacing.large
                )
                .fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.small,
            elevation = MaterialTheme.elevation.small
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.large),
                    style = MaterialTheme.typography.h5,
                    text = stringResource(R.string.overall_statistics),
                    textAlign = TextAlign.Center
                )

                val items = arrayListOf(
                    "Stars", "Time", "Helps", "Attempts", "Mistakes"
                )
                Row(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .fillMaxSize(),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.medium)
                            .fillMaxWidth(0.33f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        repeat(items.size) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(4.dp)
                                    .clickable { },
                                backgroundColor = MaterialTheme.colors.background,
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 8.dp),
                                    text = items.elementAt(it),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    PieGraph(
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.medium)
                            .fillMaxSize(),
                        data = data
                    )
                }
            }
        }
    }
}