package dorin_roman.app.kongfujava.screens.level.drag_drop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dorin_roman.app.kongfujava.screens.level.drag_drop.DragDropViewModel
import dorin_roman.app.kongfujava.ui.components.LevelButtons

@Composable
fun DragDropRightScreenContent(
    dragDropViewModel: DragDropViewModel
) {


    Surface(
        modifier = Modifier
            .background(MaterialTheme.colors.secondary)
            .padding(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 50.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            val (lesson, level, question, answers, helpers) = createRefs()
            val screenWidth = LocalConfiguration.current.screenHeightDp

            LazyColumn(
                modifier = Modifier
                    .constrainAs(answers) {
                        start.linkTo(parent.start, 10.dp)
                        end.linkTo(parent.end, 10.dp)
                        top.linkTo(parent.top, 10.dp)
                        bottom.linkTo(helpers.top, 10.dp)
                    }
                    .fillMaxSize()
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(dragDropViewModel.drop) { it ->
                    DropItem<String>(modifier = Modifier.wrapContentSize()) { isInBound, answerItem ->
                        if (answerItem != null) {
                            LaunchedEffect(key1 = answerItem) {
                                dragDropViewModel.checkAnswer(answerItem)
                            }
                        }
                        if (isInBound) {
                            if (answerItem != null) {
                                // it = answerItem
                            }
                            DropAnswerItemCard(MaterialTheme.colors.primary, it)
                        } else {
                            DropAnswerItemCard(MaterialTheme.colors.background, it)
                        }
                    }
                }
            }


            LevelButtons(modifier = Modifier
                .constrainAs(helpers) {
                    end.linkTo(parent.end, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                }, {}, {}, 0
            )
        }
    }
}