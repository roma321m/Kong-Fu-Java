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
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    studentsModelList: List<StudentModel>,
    isAddUsers: Boolean,
    onAddUsersSelected: (Boolean) -> Unit,
    onStudentSelected: (StudentModel) -> Unit
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
            scaffoldState = scaffoldState,
            isAddUsers = isAddUsers,
            onAddUsersSelected = onAddUsersSelected
        )

        SupervisorDrawerUsers(
            modifier = Modifier.constrainAs(users) {
                top.linkTo(addUsers.bottom, margin = 2.dp)
                bottom.linkTo(settings.top, margin = 2.dp)
                height = Dimension.fillToConstraints
            },
            studentsModelList = studentsModelList,
            coroutineScope = coroutineScope,
            scaffoldState = scaffoldState,
            onStudentSelected = onStudentSelected
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
    val idList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    KongFuJavaTheme {
        SupervisorDrawer(
            scaffoldState = rememberScaffoldState(),
            coroutineScope = rememberCoroutineScope(),
            studentsModelList = idList.map {
                StudentModel("id$it", "test name $it", false)
            },
            onAddUsersSelected = {},
            onStudentSelected = {},
            isAddUsers = true
        )
    }
}