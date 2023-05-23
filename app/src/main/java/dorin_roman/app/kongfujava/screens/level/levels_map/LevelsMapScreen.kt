package dorin_roman.app.kongfujava.screens.level.levels_map

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.level.levels_map.components.LevelsEvent
import dorin_roman.app.kongfujava.screens.level.levels_map.components.LevelsMapContent
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.topbar.TopBar
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@Composable
fun LevelsMapScreen(
    levelsMapViewModel: LevelsMapViewModel = hiltViewModel(),
    navigateToTutorialLevel: (levelId: Int, levelNumber: Int, worldId: Int) -> Unit,
    navigateToMultiChoiceLevel: (levelId: Int, levelNumber: Int, worldId: Int) -> Unit,
    navigateToDragDropLevel: (levelId: Int, levelNumber: Int, worldId: Int) -> Unit,
    worldId: Int,
) {

    LaunchedEffect(key1 = levelsMapViewModel.levelsModels) {
        levelsMapViewModel.handle(LevelsEvent.InitLevels(worldId))
    }

    Scaffold(
        topBar = {
            TopBar(
                onBackPressed = {},
                title = R.string.levels_map,
                hasBackButton = false
            )
        },
        content = { paddingValues ->
            LevelsMapContent(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.secondary)
                    .padding(paddingValues),
                levels = levelsMapViewModel.levelsModels,
                navigateToTutorialLevel,
                navigateToMultiChoiceLevel,
                navigateToDragDropLevel,
                worldId
            )
        }
    )

}

@DevicePreviews
@Composable
fun LevelsScreenPreview() {
    KongFuJavaTheme {
        LevelsMapScreen(
            hiltViewModel(),
            { levelId, levelNumber, worldId -> },
            { levelId, levelNumber, worldId -> },
            { levelId, levelNumber, worldId -> },
            0
        )
    }
}