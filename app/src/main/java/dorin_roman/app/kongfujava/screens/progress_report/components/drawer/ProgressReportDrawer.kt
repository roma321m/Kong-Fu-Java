package dorin_roman.app.kongfujava.screens.progress_report.components.drawer


import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.screens.progress_report.StudentModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun ProgressReportDrawer(
    studentsModelList: List<StudentModel>,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentWidth()
            .padding(start = 8.dp, top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ProgressReportDrawerHeader()

        Spacer(modifier = Modifier.height(24.dp))

        ProgressReportDrawerBody(
            studentsModelList = studentsModelList,
            coroutineScope = coroutineScope,
            scaffoldState = scaffoldState
        )
    }
}