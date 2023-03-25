package dorin_roman.app.kongfujava.screens.worlds

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.screens.worlds.components.WorldsMapContent
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

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