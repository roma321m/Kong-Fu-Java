package dorin_roman.app.kongfujava.screens.level.drag_drop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.screens.level.multi_choice.components.ColorState
import dorin_roman.app.kongfujava.screens.level.multi_choice.components.getColorMultiChoiceAnswers
import dorin_roman.app.kongfujava.ui.components.LevelButtons
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun DragDropContentDrop(
    drop: List<String>,
    checkAnswer: () -> Unit,
    deleteAnswer: (String, Int) -> Unit,
    setAnswer: (String, Int) -> Unit,
    handleHint: () -> Unit,
    hintsCount: Int,
    handleExit: () -> Unit,
    dropAnswersColors: List<ColorState>,
    isFinish: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
            .padding(start = MaterialTheme.spacing.large)
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            LevelButtons(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.extraLarge)
                    .fillMaxWidth(0.15f)
                    .fillMaxHeight(0.12f),
                onClickHint = {
                    handleHint()
                },
                OnClickNext = {
                   checkAnswer()
                    if(!isFinish){
                        handleExit()
                    }
                },
                hintsCount = hintsCount
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(MaterialTheme.spacing.large),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if (drop.size == 4) {
                repeat(4) { index ->
                    DropItem<String>(modifier = Modifier.wrapContentSize()) { isInBound, answerItem, isDragging ->
                        if (answerItem != null) {
                            LaunchedEffect(key1 = answerItem) {
                            }
                        }
                        if (isInBound) {
                            if (answerItem != null) {
                                // it = answerItem
                            }
                            val answer = answerItem ?: (index + 1).toString()
                            if (!isDragging) {
                                setAnswer(answerItem.toString(), index)
                            }
                            DropAnswerItemCard(getColorMultiChoiceAnswers(color = dropAnswersColors[index]), answer, index , deleteAnswer)
                        } else {
                            DropAnswerItemCard(getColorMultiChoiceAnswers(color = dropAnswersColors[index]), drop[index], index, deleteAnswer)
                        }
                    }
                }
            }
        }
    }

}