package dorin_roman.app.kongfujava.screens.level.tutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.level.LevelEvent
import dorin_roman.app.kongfujava.screens.level.LevelViewModel
import dorin_roman.app.kongfujava.screens.level.tutorial.components.TutorialScreenContent
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.VerticalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.components.image.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.topbar.TopBar
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun TutorialScreen(
    levelViewModel: LevelViewModel = hiltViewModel(),
    tutorialViewModel: TutorialViewModel = hiltViewModel(),
    navigateToMapLevelsScreenFromLevel: (worldId: Int) -> Unit,
    levelId: Int,
    levelNumber: Int,
    worldId: Int
) {

    LaunchedEffect(key1 = levelViewModel.question) {
        levelViewModel.handle(LevelEvent.InitLevel(levelId))
    }

    // fixme - temp
    Scaffold(
        topBar = {
            TopBar(onBackPressed = {}, title = R.string.tutorial_questions)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(MaterialTheme.colors.secondary)
                .padding(padding)
        ) {
            VerticalFortySixtyLayout(
                fortyLayout = {
                    TutorialScreenContent(
                        navigateToMapLevelsScreenFromLevel,
                        levelViewModel.title.value,
                        levelViewModel.questionTitle.value,
                        levelNumber,
                        levelId,
                        worldId,
                        { levelViewModel.handle(LevelEvent.UpdateLevelScore(levelId, worldId, levelViewModel.score)) },
                        { levelViewModel.handle(LevelEvent.UpdateLevelState(levelId, worldId, levelViewModel.state)) },
                        { levelViewModel.handle(LevelEvent.UpdateLevelHint(levelId, worldId, levelViewModel.hint)) }
                    )
                },
                sixtyLayout = {
                    SideScreenImage(R.drawable.ic_panda_question)
                }
            )
        }
    }
}

@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        TutorialScreen(
            tutorialViewModel = viewModel(),
            navigateToMapLevelsScreenFromLevel = {},
            levelId = 0,
            levelNumber = 0,
            worldId = 0,
        )
    }
}