package dorin_roman.app.kongfujava.screens.supervisor.progress_report.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.graphs.BarGraph
import dorin_roman.app.kongfujava.ui.components.graphs.BarType
import dorin_roman.app.kongfujava.ui.theme.elevation
import dorin_roman.app.kongfujava.ui.theme.spacing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SupervisorProgressReportLevelsInfo(
    currentLevel: LevelStatsModel,
    levels: Array<Int>,
    onLevelChange: (String) -> Unit
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
                    top = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.large,
                    bottom = MaterialTheme.spacing.small
                )
                .fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.small,
            elevation = MaterialTheme.elevation.small
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                var expanded by remember {
                    mutableStateOf(false)
                }

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.spacing.large),
                        style = MaterialTheme.typography.h5,
                        text = stringResource(R.string.level) +
                                " ${currentLevel.number} " +
                                stringResource(R.string.statistics),
                        textAlign = TextAlign.Center
                    )

                    Box(
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.large)
                    ) {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    }

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        levels.forEach { level ->
                            DropdownMenuItem(
                                onClick = {
                                    onLevelChange(level.toString())
                                    expanded = false
                                }
                            ) {
                                Text(text = stringResource(R.string.level) + " $level")
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text( //fixme - change to stars
                        maxLines = 1,
                        style = MaterialTheme.typography.h6,
                        text = "Stars: " + currentLevel.stars
                    )
                    Text(
                        maxLines = 1,
                        style = MaterialTheme.typography.h6,
                        text = "Invested Time: " + currentLevel.timeInMinutes
                    )
                }

                Spacer(modifier = Modifier.size(MaterialTheme.spacing.large))

                BarGraph(
                    data = listOf(
                        Pair(currentLevel.helps, stringResource(R.string.helps)),
                        Pair(currentLevel.attempts, stringResource(R.string.attempts)),
                        Pair(currentLevel.mistakes, stringResource(R.string.mistakes)),
                    ),
                    roundType = BarType.TOP_CURVED,
                    barWidth = 20.dp,
                    barColor = MaterialTheme.colors.primary,
                    barArrangement = Arrangement.SpaceEvenly
                )
            }
        }
    }
}