package dorin_roman.app.kongfujava.screens.supervisor.components.drawer

import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.screens.supervisor.StudentModel
import dorin_roman.app.kongfujava.ui.scrollbar.simpleVerticalScrollbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun SupervisorDrawerUsers(
    modifier: Modifier = Modifier,
    studentsModelList: List<StudentModel>,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = modifier
            .simpleVerticalScrollbar(lazyListState, 2.dp),
        state = lazyListState,
        flingBehavior = ScrollableDefaults.flingBehavior(),
    ) {
        items(studentsModelList) { student ->
            SupervisorDrawerMenuItem(
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
}