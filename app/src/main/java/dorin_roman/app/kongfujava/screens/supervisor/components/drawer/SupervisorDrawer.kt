package dorin_roman.app.kongfujava.screens.supervisor.components.drawer


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dorin_roman.app.kongfujava.screens.supervisor.StudentModel
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun SupervisorDrawer(
    studentsModelList: List<StudentModel>,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        val (header, addUsers, users, settings) = createRefs()

        SupervisorDrawerHeader(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        )

        SupervisorDrawerAddUsers(
            modifier = Modifier.constrainAs(addUsers) {
                top.linkTo(header.bottom, margin = 16.dp)
            },
            coroutineScope = coroutineScope,
            scaffoldState = scaffoldState
        )

        SupervisorDrawerUsers(
            modifier = Modifier.constrainAs(users) {
                top.linkTo(addUsers.bottom, margin = 2.dp)
                bottom.linkTo(settings.top, margin = 2.dp)
                height = Dimension.fillToConstraints
            },
            studentsModelList = studentsModelList,
            coroutineScope = coroutineScope,
            scaffoldState = scaffoldState
        )

        SupervisorDrawerSettings(
            modifier = Modifier.constrainAs(settings) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
            },
            coroutineScope = coroutineScope,
            scaffoldState = scaffoldState,
            onLogOutClicked = {},
            onRevokeAccessClicked = {}
        )
    }
}

@DevicePreviews
@Composable
fun SupervisorDrawerPreview() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    KongFuJavaTheme {
        SupervisorDrawer(
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
            coroutineScope = coroutineScope,
        )
    }
}