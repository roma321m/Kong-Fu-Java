package dorin_roman.app.kongfujava.screens.level.drag_drop.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun DragDropLeftTopScreenContent() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.extraLarge)
    ) {
        val (lesson, level, question, answers, helpers) = createRefs()


        Text(
            modifier = Modifier
                .constrainAs(lesson) {
                    start.linkTo(parent.start, 10.dp)
                    top.linkTo(parent.top, 10.dp)
                },
            text = stringResource(id = R.string.lesson).uppercase(),
            style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground)
        )

        Text(
            modifier = Modifier
                .constrainAs(level) {
                    start.linkTo(parent.start, 10.dp)
                    top.linkTo(lesson.bottom)
                },
            text = stringResource(id = R.string.level).uppercase(),
            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onBackground)
        )

        Text(
            modifier = Modifier
                .constrainAs(question) {
                    linkTo(start = parent.start, startMargin = 10.dp, end = parent.end, endMargin = 10.dp)
                    top.linkTo(level.bottom, 20.dp)
                }
                .padding(10.dp),
            text = stringResource(id = R.string.question1).uppercase(),
            style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onBackground)
        )
    }

}