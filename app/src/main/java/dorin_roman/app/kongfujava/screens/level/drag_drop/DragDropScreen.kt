package dorin_roman.app.kongfujava.screens.level.drag_drop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import dorin_roman.app.kongfujava.screens.level.drag_drop.components.DragDropContentDrag
import dorin_roman.app.kongfujava.screens.level.drag_drop.components.DragDropContentDrop
import dorin_roman.app.kongfujava.screens.level.drag_drop.components.DragDropContentQuestion
import dorin_roman.app.kongfujava.screens.level.drag_drop.components.DragDropContentTitle
import dorin_roman.app.kongfujava.screens.level.drag_drop.components.DraggableScreen
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.layout.CustomLayout4
import dorin_roman.app.kongfujava.ui.components.popup.AlertLevelPopUp
import dorin_roman.app.kongfujava.ui.components.topbar.TopBar
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun DragDropScreen(
    navigateToMapLevelsScreenFromLevel: (worldId: Int) -> Unit,
    levelId: Int,
    levelNumber: Int,
    worldId: Int,
    levelViewModel: LevelViewModel = hiltViewModel(),
    dragDropViewModel: DragDropViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        levelViewModel.handle(LevelEvent.InitLevel(levelId, worldId))
        dragDropViewModel.handle(DragDropEvent.InitAnswers(levelId))
    }

    LaunchedEffect(levelViewModel.questionTitle) {
        dragDropViewModel.handle(DragDropEvent.UpdateQuestion(levelViewModel.questionTitle))
    }

    DraggableScreen(
        modifier = Modifier.fillMaxSize(),
    ) {

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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.secondary)
                    .padding(MaterialTheme.spacing.large)
            ) {
                CustomLayout4(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    endTopWeight = 0.6f,
                    endBottomWeight = 0.4f,
                    startTopContent = {
                        DragDropContentTitle(
                            levelNumber = levelNumber,
                            title = levelViewModel.title,
                            questionTitle = dragDropViewModel.questionLeft
                        )
                    },
                    startBottomContent = {
                        DragDropContentDrag(
                            dragAnswers = dragDropViewModel.drag,
                            startDragging = {
                                //fixme make event
                                dragDropViewModel.startDragging()
                            },
                            stopDragging = {
                                //fixme make event
                                dragDropViewModel.stopDragging()
                            }
                        )
                    },
                    endTopContent = {
                        DragDropContentQuestion(
                            questionText = dragDropViewModel.questionRight
                        )
                    },
                    endBottomContent = {
                        DragDropContentDrop(
                            drop = dragDropViewModel.drop,
                            checkAnswer = {
                                dragDropViewModel.checkAnswer(it) // fixme handle
                            },
                            handleHint = { /*TODO*/ },
                            hintsCount = 0,
                            handleExit = {

                            }
                        )
                    }
                )
            }

//            if (dragDropViewModel.isFinish) {
//                FinishLevelPopUp(
//                    onDismiss = {
//                        navigateToMapLevelsScreenFromLevel(worldId)
//                    },
//                    levelNumber = levelNumber,
//                    levelState = levelViewModel.state
//                )
//            }

            if (levelViewModel.isExit) {
                AlertLevelPopUp(
                    levelNumber = levelNumber,
                    onDismiss = {
                        levelViewModel.handle(LevelEvent.HandleExit)
                    },
                    onClick = {
                        navigateToMapLevelsScreenFromLevel(worldId)
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