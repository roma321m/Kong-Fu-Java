package dorin_roman.app.kongfujava.screens.level.multi_choice

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.level.LevelEvent
import dorin_roman.app.kongfujava.screens.level.LevelViewModel
import dorin_roman.app.kongfujava.screens.level.multi_choice.components.MultiChoiceContent
import dorin_roman.app.kongfujava.screens.level.multi_choice.components.MultiChoiceTitle
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.image.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.layout.CustomLayout2
import dorin_roman.app.kongfujava.ui.components.topbar.TopBar
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@Composable
fun MultiChoiceScreen(
    navigateToMapLevelsScreenFromLevel: (worldId: Int) -> Unit,
    levelId: Int,
    levelNumber: Int,
    worldId: Int,
    levelViewModel: LevelViewModel = hiltViewModel(),
    multiChoiceViewModel: MultiChoiceViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        levelViewModel.handle(LevelEvent.InitLevel(levelId, worldId))
        multiChoiceViewModel.handle(MultiEvent.InitAnswers(levelId))
    }


    LaunchedEffect(key1 = multiChoiceViewModel.isRight) {
      if(multiChoiceViewModel.isRight){
          levelViewModel.handle(LevelEvent.UpdateLevelScore(multiChoiceViewModel.hintCount, multiChoiceViewModel.mistakesCount))
      }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = R.string.multi_choice_level_title,
                onBackPressed = {
                    levelViewModel.handle(LevelEvent.HandleExit)
                },
            )
        }
    ) { padding ->
        CustomLayout2(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            startTopWeight = 0.3f,
            startBottomWeight = 0.7f,
            startWeight = 0.5f,
            endWeight = 0.5f,
            startTopContent = {
                MultiChoiceTitle(
                    levelNumber = levelNumber,
                    title = levelViewModel.title,
                    questionTitle = levelViewModel.questionTitle,
                )
            },
            startBottomContent = {
                MultiChoiceContent(
                    navigateToMapLevelsScreenFromLevel = navigateToMapLevelsScreenFromLevel,
                    levelNumber = levelNumber,
                    questionAnswers = multiChoiceViewModel.answers,
                    worldId = worldId,
                    levelState = levelViewModel.state,
                    isFinish = multiChoiceViewModel.isFinish,
                    isExit = levelViewModel.isExit,
                    hintsCount = multiChoiceViewModel.hintCount,
                    handleHint = {
                        multiChoiceViewModel.handle(MultiEvent.GetHint)
                    },
                    buttonsColors = multiChoiceViewModel.buttonColors,
                    checkAnswer = { answer ->
                        multiChoiceViewModel.handle(MultiEvent.CheckAnswer(answer))
                    },
                    handleExit = {
                        levelViewModel.handle(LevelEvent.HandleExit)
                    }
                )
            },
            endContent = {
                SideScreenImage(R.drawable.ic_panda_question)
            }
        )
    }
}


@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        MultiChoiceScreen(
            multiChoiceViewModel = viewModel(),
            navigateToMapLevelsScreenFromLevel = { },
            levelId = 0,
            levelNumber = 0,
            worldId = 0,
        )
    }
}