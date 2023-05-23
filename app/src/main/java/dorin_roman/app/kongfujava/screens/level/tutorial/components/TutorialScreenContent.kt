package dorin_roman.app.kongfujava.screens.level.tutorial.components


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
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
fun TutorialScreenContent(
    navigateToMapLevelsScreenFromLevel: (worldId: Int) -> Unit,
    title: String,
    questionTitle: String,
    levelNumber: Int,
    levelId: Int,
    worldId: Int,
    handleScore: Unit,
    handleState: Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.extraLarge)
    ) {
        val (lesson, level, question, next) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(level) {
                    start.linkTo(parent.start, 10.dp)
                    top.linkTo(parent.top, 10.dp)

                },
            text = "${stringResource(id = R.string.level).uppercase()} $levelNumber",
            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onBackground)
        )

        Text(
            modifier = Modifier
                .constrainAs(lesson) {
                    start.linkTo(parent.start, 10.dp)
                    top.linkTo(level.bottom)
                },
            text = title,
            style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground)
        )

        Text(
            modifier = Modifier
                .constrainAs(question) {
                    linkTo(start = parent.start, startMargin = 10.dp, end = parent.end, endMargin = 10.dp)
                    top.linkTo(lesson.bottom, 20.dp)
                }
                .padding(10.dp),
            text = questionTitle,
            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onBackground)
        )

        Button(modifier = Modifier
            .constrainAs(next) {
                bottom.linkTo(parent.bottom, 20.dp)
                end.linkTo(parent.end, 20.dp)
            }
            .padding(10.dp),
            onClick = {
                handleScore
                navigateToMapLevelsScreenFromLevel(worldId)
            }) {
            Text(text = "Next")
        }


    }
}