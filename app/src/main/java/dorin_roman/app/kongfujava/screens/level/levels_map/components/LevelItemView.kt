package dorin_roman.app.kongfujava.screens.level.levels_map.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.screens.level.LevelType
import dorin_roman.app.kongfujava.screens.level.levels_map.LevelItemModel
import dorin_roman.app.kongfujava.ui.components.Stars
import dorin_roman.app.kongfujava.ui.theme.levelItemText
import dorin_roman.app.kongfujava.ui.theme.spacing

const val LEVEL_TYPE = "Level"

@Composable
fun LevelItemView(
    modifier: Modifier = Modifier,
    levelItemModel: LevelItemModel,
    navigateToLevel: (levelId: Int, levelNumber: Int, worldId: Int) -> Unit,
    worldId: Int
) {
    Box(
        modifier = modifier
            .padding(MaterialTheme.spacing.medium)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.primary)
            .then(
                if (levelItemModel.levelState == PointState.LOCK) {
                    Modifier.alpha(0.6f)
                } else {
                    Modifier.clickable {
                        navigateToLevel(levelItemModel.levelId, levelItemModel.levelNumber, worldId)
                    }
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = levelItemModel.levelNumber.toString(),
                style = MaterialTheme.typography.h4.copy(
                    color = MaterialTheme.colors.levelItemText,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 12.sp,
                    letterSpacing = 0.25.sp,
                ),
                textAlign = TextAlign.End
            )

            Row(
                modifier = Modifier.padding(MaterialTheme.spacing.medium),
            ) {
                Stars(
                    state = levelItemModel.levelState,
                    type = LEVEL_TYPE
                )
            }
        }
    }
}

@Preview
@Composable
fun LevelItemViewLockPreview() {
    LevelItemView(
        levelItemModel = LevelItemModel(0, PointState.LOCK, 1, 100, LevelType.TUTORIAL),
        navigateToLevel = { _, _, _ -> },
        worldId = 0
    )
}

@Preview
@Composable
fun LevelItemViewZeroPreview() {
    LevelItemView(
        levelItemModel = LevelItemModel(1, PointState.ZERO, 1, 100, LevelType.TUTORIAL),
        navigateToLevel = { _, _, _ -> },
        worldId = 0
    )
}

@Preview
@Composable
fun LevelItemViewOnePreview() {
    LevelItemView(
        levelItemModel = LevelItemModel(0, PointState.ONE, 1, 100, LevelType.TUTORIAL),
        navigateToLevel = { _, _, _ -> },
        worldId = 0
    )
}

@Preview
@Composable
fun LevelItemViewTwoPreview() {
    LevelItemView(
        levelItemModel = LevelItemModel(0, PointState.TWO, 1, 100, LevelType.TUTORIAL),
        navigateToLevel = { _, _, _ -> },
        worldId = 0
    )
}

@Preview
@Composable
fun LevelItemViewThreePreview() {
    LevelItemView(
        levelItemModel = LevelItemModel(0, PointState.THREE, 1, 100, LevelType.TUTORIAL),
        navigateToLevel = { _, _, _ -> },
        worldId = 0
    )
}