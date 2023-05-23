package dorin_roman.app.kongfujava.screens.level.drag_drop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.screens.level.drag_drop.DragDropViewModel

@Composable
fun DragDropLeftScreenContent(navigateToMapLevelsScreenFromLevel: (worldId: Int) -> Unit, dragDropViewModel: DragDropViewModel) {

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.secondary)
        ) {
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(top = 20.dp, bottom = 10.dp, start = 50.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
            ) {
                DragDropLeftTopScreenContent(dragDropViewModel)
            }
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(top = 10.dp, bottom = 20.dp, start = 50.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background),
                contentAlignment = Alignment.Center
            ) {
                DragDropLeftBottomScreenContent(dragDropViewModel)
            }
        }
    }
}