package dorin_roman.app.kongfujava.screens.level.multi_choice.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.LevelButtons
import dorin_roman.app.kongfujava.ui.components.popup.AlertLevelPopUp
import dorin_roman.app.kongfujava.ui.components.popup.FinishLevelPopUp
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun MultiChoiceContent(
    navigateToMapLevelsScreenFromLevel: (worldId: Int) -> Unit,
    levelNumber: Int,
    questionAnswers: List<String>,
    worldId: Int,
    levelState: PointState,
    isFinish: Boolean,
    isExit: Boolean,
    hintsCount: Int,
    handleHint: () -> Unit,
    checkAnswer: (String) -> Unit,
    handleExit: () -> Unit,
    buttonsColors: List<ColorState>
) {

    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (questionAnswers.isNotEmpty()) {
            MultiChoiceAnswers(
                questionAnswers = questionAnswers,
                checkAnswer = checkAnswer,
                buttonsColors = buttonsColors
            )
        }

        if (isFinish) {
            FinishLevelPopUp(
                onDismiss = {
                    navigateToMapLevelsScreenFromLevel(worldId)
                },
                levelNumber = levelNumber,
                levelState = levelState
            )
        }

        if (isExit) {
            AlertLevelPopUp(
                levelNumber = levelNumber,
                onDismiss = {
                    handleExit()
                },
                onClick = {
                    navigateToMapLevelsScreenFromLevel(worldId)
                }
            )
        }

    }

    Box(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        LevelButtons(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth(0.18f)
                .fillMaxHeight(0.08f),
            onClickHint = {
                handleHint()
            },
            OnClickNext = {
                handleExit()
            },
            hintsCount = hintsCount
        )
    }
}


@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        MultiChoiceContent(
            navigateToMapLevelsScreenFromLevel = { },
            levelNumber = 0,
            questionAnswers = emptyList(),
            worldId = 0,
            levelState = PointState.THREE,
            isFinish = false,
            isExit = false,
            hintsCount = 0,
            handleHint = {},
            checkAnswer = {},
            handleExit = {},
            buttonsColors = emptyList(),
        )
    }
}
