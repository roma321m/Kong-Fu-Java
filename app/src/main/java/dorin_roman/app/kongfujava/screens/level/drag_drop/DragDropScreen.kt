package dorin_roman.app.kongfujava.screens.level.drag_drop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import dorin_roman.app.kongfujava.screens.level.drag_drop.components.DragDropLeftScreenContent
import dorin_roman.app.kongfujava.screens.level.drag_drop.components.DragDropRightScreenContent
import dorin_roman.app.kongfujava.screens.level.drag_drop.components.DraggableScreen
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.topbar.TopBar
import dorin_roman.app.kongfujava.ui.components.VerticalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun DragDropScreen(
    navigateToMapLevelsScreenFromLevel: (worldId: Int) -> Unit,
    levelId: Int,
    levelNumber: Int,
    worldId: Int,
    levelViewModel: LevelViewModel = hiltViewModel(),
    dragDropViewModel: DragDropViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        levelViewModel.handle(LevelEvent.InitLevel(levelId, worldId))
        dragDropViewModel.handle(DragDropEvent.InitAnswers(levelId))
    }

    DraggableScreen(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        //fixme - temp
        Scaffold(
            topBar = {
                TopBar(
                    onBackPressed = {
                        levelViewModel.handle(LevelEvent.HandleExit)
                    },
                    title = R.string.drag_drop_questions
                )
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
                        DragDropLeftScreenContent(
                            levelNumber = levelNumber,
                            title = levelViewModel.title,
                            questionTitle = levelViewModel.questionTitle,
                            dragAnswers = dragDropViewModel.drag,
                            isRight = dragDropViewModel.isRight,
                            shownHints = dragDropViewModel.shownHints,
                            handleMistakes = { levelViewModel.handle(LevelEvent.UpdateLevelMistakes) },
                            //fixme make event
                            startDragging = { dragDropViewModel.startDragging() },
                            stopDragging = { dragDropViewModel.stopDragging() }
                        )
                    },
                    sixtyLayout = {
                        DragDropRightScreenContent(
                            dragDropViewModel,
                            navigateToMapLevelsScreenFromLevel = navigateToMapLevelsScreenFromLevel,
                            worldId = worldId,
                            levelNumber = levelNumber,
                            levelState = levelViewModel.state,
                            handleHint = {
                                dragDropViewModel.handle(DragDropEvent.GetHint)
                                levelViewModel.handle(LevelEvent.UpdateLevelHint)
                            },
                            hintsCount = levelViewModel.hint,
                            shownHints = dragDropViewModel.shownHints,
                            finishLevel = { levelViewModel.handle(LevelEvent.FinishLevel) },
                            isFinish = levelViewModel.isFinish,
                            handleExit = { levelViewModel.handle(LevelEvent.HandleExit) },
                            isExit = levelViewModel.isExit,
                            )
                    }
                )
            }
        }
    }
}


@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        DragDropScreen(
            dragDropViewModel = viewModel(),
            navigateToMapLevelsScreenFromLevel = { },
            levelId = 0,
            levelNumber = 0,
            worldId = 0
        )
    }
}