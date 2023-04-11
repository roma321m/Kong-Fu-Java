package dorin_roman.app.kongfujava.screens.supervisor

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.supervisor.components.content.SupervisorContent
import dorin_roman.app.kongfujava.screens.supervisor.components.drawer.SupervisorDrawer
import dorin_roman.app.kongfujava.screens.supervisor.components.drawer.SupervisorDrawerShape
import dorin_roman.app.kongfujava.screens.supervisor.components.top_bar.SupervisorTopBar
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import kotlinx.coroutines.launch

@Composable
fun SupervisorScreen() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SupervisorTopBar(
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
            SupervisorDrawer(
                // fixme - temp list - viewModel
                studentsModelList = listOf(
                    StudentModel("id1", "test 1"),
                    StudentModel("id1", "test 2"),
                    StudentModel("id1", "test 3"),
                    StudentModel("id1", "test 4"),
                    StudentModel("id1", "test 5"),
                    StudentModel("id1", "test 6"),
                    StudentModel("id1", "test 7"),
                    StudentModel("id1", "test 8"),
                    StudentModel("id1", "test 9"),
                    StudentModel("id1", "test 10"),
                    StudentModel("id1", "test 11"),
                    StudentModel("id1", "test 12"),
                    StudentModel("id1", "test 13"),
                    StudentModel("id1", "test 14"),
                    StudentModel("id1", "test 15"),
                    StudentModel("id1", "test 16"),
                    StudentModel("id1", "test 17"),
                    StudentModel("id1", "test 18"),
                    StudentModel("id1", "test 19"),
                    StudentModel("id1", "test 20")
                ),
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope
            )
        },
        drawerShape = SupervisorDrawerShape(0.dp, 0.31f),
        drawerGesturesEnabled = true,
        content = { paddingValues ->
            SupervisorContent(
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}

@DevicePreviews
@Composable
fun SupervisorScreenPreview() {
    KongFuJavaTheme {
        SupervisorScreen()
    }
}