package dorin_roman.app.kongfujava.screens.progress_report.components.drawer


import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.progress_report.StudentModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProgressReportDrawerBody(
    studentsModelList: List<StudentModel>,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    ProgressReportDrawerMenuItem(
        imageVector = Icons.Filled.Create,
        text = stringResource(R.string.add_users),
        onItemClick = {
            coroutineScope.launch {
                scaffoldState.drawerState.close()
            }
            // TODO - navigate to add user screen
        },
        selected = true // Fixme - temp - set selection base on clicks
    )

//    Divider(
//        modifier = Modifier
//            .fillMaxWidth(0.4f)
//            .padding(vertical = 6.dp),
//        color = MaterialTheme.colors.onBackground
//    )

    studentsModelList.forEach { student ->
        ProgressReportDrawerMenuItem(
            imageVector = Icons.Filled.Person,
            text = student.name,
            onItemClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
                // TODO - show progress report for this student
            }
        )
    }
}