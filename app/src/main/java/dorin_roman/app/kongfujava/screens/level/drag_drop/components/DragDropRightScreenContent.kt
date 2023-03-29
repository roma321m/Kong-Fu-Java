package dorin_roman.app.kongfujava.screens.level.drag_drop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dorin_roman.app.kongfujava.ui.components.HelpersButtons

@Composable
fun DragDropRightScreenContent() {
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

            HelpersButtons(modifier = Modifier
                .constrainAs(helpers) {
                    end.linkTo(parent.end, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                })
        }
    }
}