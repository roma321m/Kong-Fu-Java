package dorin_roman.app.kongfujava.screens.level.levels.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.screens.level.levels.components.LevelsButtons
import dorin_roman.app.kongfujava.screens.level.levels.components.LevelsTitle
import dorin_roman.app.kongfujava.ui.components.BackButton
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun LevelsScreenContent(
    navigateToTutorialLevel: () -> Unit,
    navigateToMultiChoiceLevel: () -> Unit,
    navigateToDragDropLevel: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.secondary)
            .padding(12.dp)
            .fillMaxSize(),
    ) {
        BackButton(
            modifier = Modifier.wrapContentSize(),
            onBackPressed = {} //fixme - onclick
        )
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LevelsTitle()
            Spacer(modifier = Modifier.padding(10.dp))
            LevelsButtons(
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