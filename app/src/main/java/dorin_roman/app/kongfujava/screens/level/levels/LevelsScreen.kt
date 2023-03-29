package dorin_roman.app.kongfujava.screens.level.levels

import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.screens.level.levels.components.LevelsScreenContent
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun LevelsScreen(
    navigateToTutorialLevel: () -> Unit,
    navigateToMultiChoiceLevel: () -> Unit,
    navigateToDragDropLevel: () -> Unit,
) {
    // fixme - temp
    LevelsScreenContent(
        navigateToTutorialLevel,
        navigateToMultiChoiceLevel,
        navigateToDragDropLevel
    )

}

@DevicePreviews
@Composable
fun LevelsScreenPreview() {
    KongFuJavaTheme {
        LevelsScreen({}, {}, {})
    }
}