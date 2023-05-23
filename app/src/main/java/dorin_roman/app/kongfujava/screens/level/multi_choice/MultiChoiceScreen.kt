package dorin_roman.app.kongfujava.screens.level.multi_choice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.level.LevelEvent
import dorin_roman.app.kongfujava.screens.level.multi_choice.components.MultiChoiceScreenContent
import dorin_roman.app.kongfujava.ui.components.*
import dorin_roman.app.kongfujava.ui.components.topbar.TopBar
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun MultiChoiceScreen(
    multiChoiceViewModel: MultiChoiceViewModel = hiltViewModel(),
    navigateToMapLevelsScreenFromLevel: (worldId: Int) -> Unit,
    levelId: Int,
    levelNumber: Int,
    worldId: Int
) {

    LaunchedEffect(key1 = multiChoiceViewModel.question, key2 = multiChoiceViewModel.answer) {
        multiChoiceViewModel.handle(LevelEvent.InitLevel(levelId))
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
                    MultiChoiceScreenContent(navigateToMapLevelsScreenFromLevel,levelNumber,multiChoiceViewModel.title.value, multiChoiceViewModel.questionTitle.value, multiChoiceViewModel.answers)
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