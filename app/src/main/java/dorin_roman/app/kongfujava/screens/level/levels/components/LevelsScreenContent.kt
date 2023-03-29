package dorin_roman.app.kongfujava.screens.level.levels.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.TopBar
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun LevelsScreenContent(
    navigateToTutorialLevel: () -> Unit,
    navigateToMultiChoiceLevel: () -> Unit,
    navigateToDragDropLevel: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(onBackPressed = {}, title = R.string.app_name)
        }
    ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.secondary)
                .padding(MaterialTheme.spacing.large)
        ) {
            val (levels) = createRefs()

            LevelsButtons(
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(levels)
                    {
                        linkTo(start = parent.start, end = parent.end)
                        top.linkTo(parent.top, 20.dp)
                    },
                navigateToTutorialLevel,
                navigateToMultiChoiceLevel,
                navigateToDragDropLevel
            )
        }
    }
}


@DevicePreviews
@Composable
fun LevelsScreenPreview() {
    KongFuJavaTheme {
        LevelsScreenContent({}, {}, {})
    }
}