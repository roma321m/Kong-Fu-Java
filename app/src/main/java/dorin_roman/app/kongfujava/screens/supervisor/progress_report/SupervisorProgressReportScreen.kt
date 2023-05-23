package dorin_roman.app.kongfujava.screens.supervisor.progress_report

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.screens.supervisor.StudentModel
import dorin_roman.app.kongfujava.screens.supervisor.progress_report.components.SupervisorProgressReportUserActivity
import dorin_roman.app.kongfujava.screens.supervisor.progress_report.components.SupervisorProgressReportUserInfo
import dorin_roman.app.kongfujava.screens.supervisor.progress_report.components.SupervisorProgressReportUserLevelsInfo
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
                world = viewModel.currentWorld,
                level = viewModel.currentLevelNumber,
                isLoading = viewModel.isLevelsStatsLoading || viewModel.isActiveTimeLoading
            )
        },
        endTopContent = {
            SupervisorProgressReportUserLevelsInfo(
                currentLevel = viewModel.currentLevelInfoStats,
                levels = viewModel.levelStatsModelList,
                onLevelChange = { levelId ->
                    viewModel.handle(SupervisorProgressReportEvent.UpdateSelectedLevelInfo(levelId))
                },
                isLoading = viewModel.isLevelsStatsLoading || viewModel.isActiveTimeLoading
            )
        },
        startBottomContent = {
            SupervisorProgressReportUserActivity(
                data = viewModel.currentActivityStats,
                isLoading = viewModel.isLevelsStatsLoading || viewModel.isActiveTimeLoading
            )
        },
        endBottomContent = {
            SupervisorProgressReportUserOverallStats(
                items = viewModel.items,
                selectedItem = viewModel.selectedOverallStats,
                onItemClick = { index ->
                    viewModel.handle(SupervisorProgressReportEvent.UpdateSelectedOverallStats(index))
                },
                currentData = viewModel.currentOverallStats,
                isLoading = viewModel.isLevelsStatsLoading || viewModel.isActiveTimeLoading
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