package dorin_roman.app.kongfujava.ui.screens.worlds

import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.ui.screens.worlds.content.WorldsMapContent
import dorin_roman.app.kongfujava.view_models.WorldsMapViewModel

@Composable
fun WorldsMapScreen(
    navigateToLevel: () -> Unit,
    worldsMapViewModel: WorldsMapViewModel = hiltViewModel()
) {
    WorldsMapContent(navigateToLevel, worldsMapViewModel)
}

@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        WorldsMapScreen({})
    }
}