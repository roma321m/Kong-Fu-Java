package dorin_roman.app.kongfujava.screens.supervisor.components.drawer

import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.screens.supervisor.StudentModel
import dorin_roman.app.kongfujava.ui.components.scrollbar.simpleVerticalScrollbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SupervisorDrawerUsers(
    modifier: Modifier = Modifier,
    studentsModelList: List<StudentModel>,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    refreshing: Boolean,
    onRefresh: () -> Unit,
    onStudentSelected: (StudentModel) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val state = rememberPullRefreshState(refreshing, onRefresh)

    Box(
        modifier = modifier
            .pullRefresh(state)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .simpleVerticalScrollbar(lazyListState, 2.dp),
            state = lazyListState,
            flingBehavior = ScrollableDefaults.flingBehavior(),
        ) {
            if (studentsModelList.isEmpty()) {
                item {
                    SupervisorDrawerMenuEmptyItem()
                }
            } else {
                items(studentsModelList) { student ->
                    SupervisorDrawerMenuItem(
                        modifier = Modifier
                            .then(if (refreshing) Modifier.alpha(0f) else Modifier),
                        imageVector = Icons.Filled.Person,
                        text = student.name,
                        onItemClick = {
                            onStudentSelected(student)
                            coroutineScope.launch {
                                scaffoldState.drawerState.close()
                            }
                        },
                        selected = student.selected
                    )
                }
            }
        }
        PullRefreshIndicator(
            modifier = Modifier
                .align(Alignment.TopCenter),
            refreshing = refreshing,
            state = state
        )
    }
}