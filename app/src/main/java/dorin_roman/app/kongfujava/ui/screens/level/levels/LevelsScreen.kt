package dorin_roman.app.kongfujava.ui.screens.level.levels

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.screens.level.levels.content.LevelsScreenContent
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun LevelsScreen(
    navigateToTutorialLevel: () -> Unit,
    navigateToMultiChoiceLevel: () -> Unit,
    navigateToDragDropLevel: () -> Unit,
) {
    // fixme - temp
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LevelsScreenContent(
            navigateToTutorialLevel,
            navigateToMultiChoiceLevel,
            navigateToDragDropLevel
        )
    }
}

@DevicePreviews
@Composable
fun LevelsScreenPreview() {
    KongFuJavaTheme {
        LevelsScreen({}, {}, {})
    }
}