package dorin_roman.app.kongfujava.screens.level.multi_choice

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
import dorin_roman.app.kongfujava.screens.level.multi_choice.components.MultiChoiceScreenContent
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.VerticalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.components.image.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.topbar.TopBar
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun MultiChoiceScreen(
    levelViewModel: LevelViewModel = hiltViewModel(),
    multiChoiceViewModel: MultiChoiceViewModel = hiltViewModel(),
    navigateToMapLevelsScreenFromLevel: (worldId: Int) -> Unit,
    levelId: Int,
    levelNumber: Int,
    worldId: Int
) {

    LaunchedEffect(key1 = true) {
        levelViewModel.handle(LevelEvent.InitLevel(levelId, worldId))
        multiChoiceViewModel.handle(MultiEvent.InitAnswers(levelId))
    }

    Scaffold(
        topBar = {
            TopBar(onBackPressed = {}, title = R.string.multi_choice_questions)
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
                    MultiChoiceScreenContent(
                        navigateToMapLevelsScreenFromLevel,
                        levelNumber,
                        levelViewModel.title,
                        levelViewModel.questionTitle,
                        multiChoiceViewModel.answers,
                        worldId,
                        multiChoiceViewModel.shownHints,
                        { levelViewModel.handle(LevelEvent.FinishLevel) },
                        {
                            levelViewModel.handle(LevelEvent.UpdateLevelHint)
                            multiChoiceViewModel.handle(MultiEvent.GetHint)
                        },
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
        MultiChoiceScreen(
            multiChoiceViewModel = viewModel(),
            navigateToMapLevelsScreenFromLevel = { },
            levelId = 0,
            levelNumber = 0,
            worldId = 0,
        )
    }
}