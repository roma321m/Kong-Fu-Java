package dorin_roman.app.kongfujava.screens.level.drag_drop.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("SuspiciousIndentation")
@Composable
fun DragDropLeftBottomScreenContent(dragAnswers: List<String>, startDragging: () -> Unit, stopDragging: () -> Unit) {

    val gridState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
        state = gridState,
        userScrollEnabled = false,
    ) {
        items(dragAnswers) { answerItem ->
            DragItem(
                dataToDrop = answerItem,
                startDragging = startDragging,
                stopDragging = stopDragging
            ) {
                DragAnswerItemCard(answer = answerItem)
            }
        }
    }

}

