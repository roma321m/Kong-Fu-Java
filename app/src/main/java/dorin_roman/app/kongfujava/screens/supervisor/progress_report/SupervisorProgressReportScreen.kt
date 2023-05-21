package dorin_roman.app.kongfujava.screens.supervisor.progress_report

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.screens.supervisor.StudentModel
import dorin_roman.app.kongfujava.screens.supervisor.progress_report.components.LevelStatsModel
import dorin_roman.app.kongfujava.screens.supervisor.progress_report.components.SupervisorProgressReportLevelsInfo
import dorin_roman.app.kongfujava.screens.supervisor.progress_report.components.SupervisorProgressReportUserActivity
import dorin_roman.app.kongfujava.screens.supervisor.progress_report.components.SupervisorProgressReportUserInfo
import dorin_roman.app.kongfujava.screens.supervisor.progress_report.components.SupervisorProgressReportUserOverallStats
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.layout.CustomLayout4
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun SupervisorProgressReportScreen(
    modifier: Modifier = Modifier,
    studentModel: StudentModel,
    viewModel: SupervisorProgressReportViewModel = hiltViewModel()
) {

    CustomLayout4(
        modifier = modifier,
        startTopContent = {
            SupervisorProgressReportUserInfo(
                studentModel = viewModel.selectedStudent,
                world = "Variables", // fixme - firebase
                level = 5 // fixme - firebase
            )
        },
        endTopContent = {
            SupervisorProgressReportLevelsInfo(
                currentLevel = LevelStatsModel(
                    number = 1,
                    stars = 3,
                    timeInMinutes = 7,
                    helps = 1,
                    mistakes = 0,
                    attempts = 2
                ), // fixme - viewModel
                levels = arrayOf(1, 2, 3, 4), // fixme - firebase
                onLevelChange = {} // fixme - viewModel
            )
        },
        startBottomContent = {
            SupervisorProgressReportUserActivity(
                data = listOf( // fixme - temp data, need to come from firebase
                    Pair(30, "02/08"), // time in minutes, data
                    Pair(60, "03/08"),
                    Pair(90, "05/08"),
                    Pair(50, "09/08"),
                    Pair(70, "11/08"),
                    Pair(30, "02/08"),
                    Pair(60, "03/08"),
                    Pair(90, "05/08"),
                    Pair(50, "09/08"),
                    Pair(70, "11/08"),
                    Pair(75, "12/08")
                )
            )
        },
        endBottomContent = {
            SupervisorProgressReportUserOverallStats(
                data = mapOf(
                    // fixme - temp data, need to come from firebase
                    Pair("Level-1", 80),
                    Pair("Level-2", 120),
                    Pair("Level-3", 110),
                    Pair("Level-4", 170),
                    Pair("Level-5", 30),
                    Pair("Level-6", 40),
                    Pair("Level-7", 50),
                    Pair("Level-8", 10),
                )
            )
        }
    )

    LaunchedEffect(studentModel) {
        viewModel.handle(SupervisorProgressReportEvent.UpdateSelectedStudent(studentModel))
    }
}

@DevicePreviews
@Composable
fun SupervisorProgressReportScreenPreview() {
    KongFuJavaTheme {
        SupervisorProgressReportScreen(
            studentModel = StudentModel(
                id = "id",
                name = "name",
                age = 10,
                privateCode = "ABCDEF",
                selected = true
            )
        )
    }
}