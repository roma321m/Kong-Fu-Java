package dorin_roman.app.kongfujava.screens.progress_report

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.progress_report.components.content.ProgressReportContent
import dorin_roman.app.kongfujava.screens.progress_report.components.drawer.ProgressReportDrawer
import dorin_roman.app.kongfujava.screens.progress_report.components.drawer.ProgressReportDrawerShape
import dorin_roman.app.kongfujava.screens.progress_report.components.top_bar.ProgressReportTopBar
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import kotlinx.coroutines.launch

@Composable
fun ProgressReportScreen(
    navigateToAddUsers: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ProgressReportTopBar(
                title = R.string.progress_report,
                onOpenDrawer = {
                    coroutineScope.launch { scaffoldState.drawerState.open() }
                },
                onLogOutClicked = {
                    // todo - viewModel handle
                },
                onRevokeAccessClicked = {
                    // todo - viewModel handle
                }
            )
        },
        drawerContent = {
            ProgressReportDrawer(
                // fixme - temp list - viewModel
                studentsModelList = listOf(
                    StudentModel("id1", "Roman Michailov"),
                    StudentModel("id2", "Dorin Dosman")
                ),
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope
            )
        },
        drawerShape = ProgressReportDrawerShape(0.dp, 0.41f),
        drawerGesturesEnabled = true,
        content = { paddingValues ->
            ProgressReportContent(
                navigateToAddUsers = navigateToAddUsers,
                paddingValues = paddingValues
            )
        }
    )
}

@DevicePreviews
@Composable
fun ProgressReportScreenPreview() {
    KongFuJavaTheme {
        ProgressReportScreen({})
    }
}