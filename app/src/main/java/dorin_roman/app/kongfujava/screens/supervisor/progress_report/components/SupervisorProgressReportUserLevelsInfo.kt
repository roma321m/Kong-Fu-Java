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
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.supervisor.progress_report.LevelStatsModel
import dorin_roman.app.kongfujava.ui.components.OneLevel
import dorin_roman.app.kongfujava.ui.components.ThreeLevel
import dorin_roman.app.kongfujava.ui.components.TwoLevel
import dorin_roman.app.kongfujava.ui.components.ZeroLevelYellow
import dorin_roman.app.kongfujava.ui.components.graphs.BarGraph
import dorin_roman.app.kongfujava.ui.theme.elevation
import dorin_roman.app.kongfujava.ui.theme.spacing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SupervisorProgressReportUserLevelsInfo(
    currentLevel: LevelStatsModel,
    levels: Array<LevelStatsModel>,
    onLevelChange: (Int) -> Unit,
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
            if (isLoading) {
                SupervisorProgressReportUserLevelsInfoShimmer()
            } else {

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
                            text = if (currentLevel.id != -1) {
                                "${currentLevel.world} " +
                                        stringResource(R.string.progress_report_level) +
                                        " ${currentLevel.number} "
                            } else {
                                stringResource(R.string.progress_report_level) + " "
                            } +
                                    stringResource(R.string.progress_report_statistics),
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
                                        onLevelChange(level.id)
                                        expanded = false
                                    }
                                ) {
                                    Text(text = "${level.world} " + stringResource(R.string.progress_report_level) + " ${level.number}")
                                }
                            }
                        }
                    }

                    if (currentLevel.id == -1) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(bottom = MaterialTheme.spacing.large),
                                style = MaterialTheme.typography.h5,
                                text = stringResource(R.string.progress_report_no_available_data),
                                textAlign = TextAlign.Center,
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            when (currentLevel.stars) {
                                1 -> OneLevel("")
                                2 -> TwoLevel("")
                                3 -> ThreeLevel("")
                                else -> ZeroLevelYellow("")
                            }
                        }

                        Spacer(modifier = Modifier.size(MaterialTheme.spacing.large))

                        BarGraph(
                            data = mapOf(
                                Pair(
                                    stringResource(R.string.progress_report_helps),
                                    currentLevel.helps
                                ),
                                Pair(
                                    stringResource(R.string.progress_report_attempts),
                                    currentLevel.attempts
                                ),
                                Pair(
                                    stringResource(R.string.progress_report_mistakes),
                                    currentLevel.mistakes
                                ),
                                Pair(
                                    stringResource(R.string.progress_report_time),
                                    currentLevel.timeInMinutes
                                ),
                            ),
                            barColor = MaterialTheme.colors.primary
                        )
                    }
                }
            }
        }
    }
}