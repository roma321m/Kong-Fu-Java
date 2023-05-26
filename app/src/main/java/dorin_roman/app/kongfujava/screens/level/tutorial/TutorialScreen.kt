package dorin_roman.app.kongfujava.screens.level.tutorial

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.screens.level.LevelEvent
import dorin_roman.app.kongfujava.screens.level.LevelViewModel
import dorin_roman.app.kongfujava.screens.level.tutorial.components.TutorialBody
import dorin_roman.app.kongfujava.screens.level.tutorial.components.TutorialEmptyVideo
import dorin_roman.app.kongfujava.screens.level.tutorial.components.TutorialTitle
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.layout.CustomLayout2
import dorin_roman.app.kongfujava.ui.components.popup.AlertLevelPopUp
import dorin_roman.app.kongfujava.ui.components.popup.FinishLevelPopUp
import dorin_roman.app.kongfujava.ui.components.topbar.TopBar
import dorin_roman.app.kongfujava.ui.components.video.VideoView
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@Composable
fun TutorialScreen(
    navigateToMapLevelsScreenFromLevel: (worldId: Int) -> Unit,
    levelId: Int,
    levelNumber: Int,
    worldId: Int,
    levelViewModel: LevelViewModel = hiltViewModel(),
    tutorialViewModel: TutorialViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        levelViewModel.handle(LevelEvent.InitLevel(levelId, worldId))
        tutorialViewModel.handle(TutorialEvent.InitTutorial(levelId))
    }

    Scaffold(
        topBar = {
            TopBar(
                onBackPressed = {
                    tutorialViewModel.handle(TutorialEvent.UpdateNextClicked(true))
                },
                title = R.string.tutorial_questions
            )
        },
        content = { padding ->
            CustomLayout2(
                modifier = Modifier.padding(padding),
                startTopWeight = 0.2f,
                startBottomWeight = 0.8f,
                startTopContent = {
                    TutorialTitle(
                        levelNumber = levelNumber,
                        title = levelViewModel.title
                    )
                },
                startBottomContent = {
                    TutorialBody(
                        onNextClick = {
                            tutorialViewModel.handle(TutorialEvent.UpdateNextClicked(true))
                        },
                        text = levelViewModel.questionTitle
                    )
                },
                endContent = {
                    if (tutorialViewModel.videoUrl.isNotBlank()) {
                        VideoView(
                            modifier = Modifier.fillMaxSize(),
                            url = tutorialViewModel.videoUrl,
                            stopPlay = {tutorialViewModel.isNextClicked},
                            handleVideoWatched = { tutorialViewModel.handle(TutorialEvent.VideoWatched) }
                        )
                    } else {
                        TutorialEmptyVideo()
                    }
                }
            )

            if(tutorialViewModel.isNextClicked) {
                if (tutorialViewModel.isVideoWatched) {
                    FinishLevelPopUp(
                        onDismiss = {
                            levelViewModel.handle(LevelEvent.UpdateLevelScore())
                            tutorialViewModel.handle(TutorialEvent.FinishLevel)
                            navigateToMapLevelsScreenFromLevel(worldId)
                        },
                        levelNumber = levelNumber,
                        levelState = PointState.THREE
                    )
                }
                else{
                    AlertLevelPopUp(
                        levelNumber = levelNumber,
                        onDismiss = {
                            tutorialViewModel.handle(TutorialEvent.UpdateNextClicked(false))
                        },
                        onClick = {
                            navigateToMapLevelsScreenFromLevel(worldId)
                        }
                    )
                }
            }
        }
    )
}

@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        TutorialScreen(
            navigateToMapLevelsScreenFromLevel = {},
            levelId = 0,
            levelNumber = 0,
            worldId = 0,
        )
    }
}