package dorin_roman.app.kongfujava.screens.level.levels_map

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.level.levels_map.components.LevelItemView
import dorin_roman.app.kongfujava.screens.level.levels_map.components.LevelsEvent
import dorin_roman.app.kongfujava.screens.level.levels_map.components.LevelsMapContent
import dorin_roman.app.kongfujava.screens.top_bar.TopBarEvent
import dorin_roman.app.kongfujava.screens.top_bar.TopBarViewModel
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.topbar.TopBar
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun LevelsMapScreen(
    navigateToTutorialLevel: (levelId: Int, levelNumber: Int, worldId: Int) -> Unit,
    navigateToMultiChoiceLevel: (levelId: Int, levelNumber: Int, worldId: Int) -> Unit,
    navigateToDragDropLevel: (levelId: Int, levelNumber: Int, worldId: Int) -> Unit,
    worldId: Int,
    navigateToWorldMapFromMapLevelsScreen: () -> Unit,
    levelsMapViewModel: LevelsMapViewModel = hiltViewModel(),
    topBarViewModel: TopBarViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        levelsMapViewModel.handle(LevelsEvent.InitLevels(worldId))
    }

    Scaffold(
        topBar = {
            TopBar(
                onBackPressed = {
                    navigateToWorldMapFromMapLevelsScreen()
                },
                title = R.string.levels_map_title,
                onLogOutClicked = {
                    topBarViewModel.handle(TopBarEvent.LogOut)
                }
            )
        },
        content = { paddingValues ->
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.3f),
                painter = painterResource(R.drawable.img_levels_background),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds
            )
            LevelsMapContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 150.dp, vertical = MaterialTheme.spacing.large)
                    .fillMaxSize(),
                levels = levelsMapViewModel.levelsModels,
                navigateToTutorialLevel = navigateToTutorialLevel,
                navigateToMultiChoiceLevel = navigateToMultiChoiceLevel,
                navigateToDragDropLevel = navigateToDragDropLevel,
                itemContent = { item, nav ->
                    LevelItemView(
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.large)
                            .fillMaxSize(),
                        levelItemModel = item,
                        navigateToLevel = nav,
                        worldId = worldId,
                    )
                }
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    modifier = Modifier
                        .padding(end = 100.dp, bottom = 30.dp)
                        .fillMaxHeight(0.1f)
                        .clip(CircleShape),
                    painter = painterResource(R.drawable.img_panda_done),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillHeight
                )
            }
        }
    )

}

@DevicePreviews
@Composable
fun LevelsScreenPreview() {
    KongFuJavaTheme {
        LevelsMapScreen(
            navigateToTutorialLevel = { _, _, _ -> },
            navigateToMultiChoiceLevel = { _, _, _ -> },
            navigateToDragDropLevel = { _, _, _ -> },
            worldId = 0,
            navigateToWorldMapFromMapLevelsScreen = {}
        )
    }
}