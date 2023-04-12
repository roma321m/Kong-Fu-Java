package dorin_roman.app.kongfujava.screens.supervisor

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.supervisor.components.content.add_users.SupervisorAddUsers
import dorin_roman.app.kongfujava.screens.supervisor.components.content.progress_report.SupervisorProgressReport
import dorin_roman.app.kongfujava.screens.supervisor.components.drawer.SupervisorDrawer
import dorin_roman.app.kongfujava.screens.supervisor.components.drawer.SupervisorDrawerShape
import dorin_roman.app.kongfujava.screens.supervisor.components.top_bar.SupervisorTopBar
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import kotlinx.coroutines.launch

@Composable
fun SupervisorScreen(
    viewModel: SupervisorViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val screenTitle = getTitle(viewModel.isAddUsers)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SupervisorTopBar(
                title = screenTitle,
                onOpenDrawer = {
                    coroutineScope.launch { scaffoldState.drawerState.open() }
                }
            )
        },
        drawerContent = {
            SupervisorDrawer(
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                studentsModelList = viewModel.studentsModelList,
                isAddUsers = viewModel.isAddUsers,
                onAddUsersSelected = { selected ->
                    viewModel.handle(SupervisorEvent.AddUsersSelected(selected))
                },
                onStudentSelected = { student ->
                    viewModel.handle(SupervisorEvent.SelectStudent(student))
                }
            )
        },
        drawerShape = SupervisorDrawerShape(0.dp, 0.31f),
        drawerGesturesEnabled = true,
        content = { paddingValues ->
            if (viewModel.isAddUsers) {
                SupervisorAddUsers(
                    modifier = Modifier.padding(paddingValues)
                )
            } else {
                SupervisorProgressReport(
                    modifier = Modifier.padding(paddingValues),
                    studentModel = viewModel.selectedStudent
                )
            }
        }
    )

    LaunchedEffect(true) {
        viewModel.handle(SupervisorEvent.InitSelectedStudent)
    }
}

private fun getTitle(isAddUsers: Boolean): Int {
    return if (isAddUsers) {
        R.string.add_users
    } else {
        R.string.progress_report
    }
}

@DevicePreviews
@Composable
fun SupervisorScreenPreview() {
    KongFuJavaTheme {
        SupervisorScreen()
    }
}