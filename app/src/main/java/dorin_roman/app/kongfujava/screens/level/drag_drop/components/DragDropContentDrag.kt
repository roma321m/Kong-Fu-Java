package dorin_roman.app.kongfujava.screens.level.drag_drop.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun DragDropContentDrag(
    dragAnswers: List<String>,
    startDragging: () -> Unit,
    stopDragging: () -> Unit
) {
    if (dragAnswers.size == 4) {
        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.large)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.large)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DragItem(
                    dataToDrop = dragAnswers[0],
                    startDragging = startDragging,
                    stopDragging = stopDragging
                ) {
                    DragAnswerItemCard(answer = dragAnswers[0])
                }
                DragItem(
                    dataToDrop = dragAnswers[1],
                    startDragging = startDragging,
                    stopDragging = stopDragging
                ) {
                    DragAnswerItemCard(answer = dragAnswers[1])
                }
            }
            Row(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.large)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DragItem(
                    dataToDrop = dragAnswers[2],
                    startDragging = startDragging,
                    stopDragging = stopDragging
                ) {
                    DragAnswerItemCard(answer = dragAnswers[2])
                }
                DragItem(
                    dataToDrop = dragAnswers[3],
                    startDragging = startDragging,
                    stopDragging = stopDragging
                ) {
                    DragAnswerItemCard(answer = dragAnswers[3])
                }
            }
        }
    }
}

