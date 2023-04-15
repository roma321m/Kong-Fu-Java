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
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.screens.supervisor.add_users.SupervisorAddUsersScreen
import dorin_roman.app.kongfujava.screens.supervisor.progress_report.SupervisorProgressReportScreen
import dorin_roman.app.kongfujava.screens.supervisor.components.drawer.SupervisorDrawer
import dorin_roman.app.kongfujava.screens.supervisor.components.drawer.SupervisorDrawerShape
import dorin_roman.app.kongfujava.screens.supervisor.components.top_bar.SupervisorTopBar
import dorin_roman.app.kongfujava.service.CodeService
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import kotlinx.coroutines.launch

@Composable
fun SupervisorScreen(
    userType: UserType = UserType.None,
    codeService: CodeService,
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
                },
                onLogOutClicked = {
                    viewModel.handle(SupervisorEvent.LogOut)
                },
                onRevokeAccessClicked = {
                    viewModel.handle(SupervisorEvent.RevokeAccess)
                }
            )
        },
        drawerShape = SupervisorDrawerShape(0.dp, 0.31f),
        drawerGesturesEnabled = true,
        content = { paddingValues ->
            if (viewModel.isAddUsers) {
                SupervisorAddUsersScreen(
                    modifier = Modifier.padding(paddingValues),
                    codeViewModel = codeService.codeViewModel
                )
            } else {
                SupervisorProgressReportScreen(
                    modifier = Modifier.padding(paddingValues),
                    studentModel = viewModel.selectedStudent
                )
            }
        }
    )

    LaunchedEffect(true) {
        viewModel.handle(SupervisorEvent.InitData(userType))
    }

    LaunchedEffect(viewModel.revokeAccessRequest) {
        viewModel.handle(SupervisorEvent.RevokeAccessResponse)
    }

    LaunchedEffect(viewModel.deleteUserRequest) {
        viewModel.handle(SupervisorEvent.DeleteUserResponse)
    }

    LaunchedEffect(viewModel.userDataRequest) {
        viewModel.handle(SupervisorEvent.LoadUserDataResponse)
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
        SupervisorScreen(
            codeService = CodeService()
        )
    }
}