package dorin_roman.app.kongfujava.screens.level.levels_map.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.screens.level.LevelType
import dorin_roman.app.kongfujava.screens.level.levels_map.LevelItemModel
import dorin_roman.app.kongfujava.ui.components.*

const val LEVEL_TYPE = "Level"

@Composable
fun LevelItemView(
    modifier: Modifier = Modifier,
    levelItemModel: LevelItemModel,
    navigateToLevel: (levelId: Int, levelNumber: Int, worldId: Int) -> Unit,
    worldId: Int
) {
    ConstraintLayout(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colors.primary)
            .then(
                if (levelItemModel.levelState == PointState.LOCK) {
                    Modifier.alpha(0.6f)
                } else {
                    Modifier
                }
            )
            .clickable {
                if (levelItemModel.levelState != PointState.LOCK) {
                    navigateToLevel(levelItemModel.levelId, levelItemModel.levelNumber, worldId)
                }
            }
    ) {
        val (text, score) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(parent.top, 5.dp)
                    bottom.linkTo(parent.bottom, 5.dp)
                    start.linkTo(parent.start, 5.dp)
                    end.linkTo(parent.end, 5.dp)
                },
            text = levelItemModel.levelNumber.toString(),
            style = MaterialTheme.typography.h4.copy(
                color = Color.White, //Fixme i know we agree on MaterialTheme.colors.onbackground but black its ugly!
                fontWeight = FontWeight.SemiBold,
                lineHeight = 12.sp,
                letterSpacing = 0.25.sp,
            ),
            textAlign = TextAlign.End
        )

        Row(
            modifier = Modifier
                .constrainAs(score) {
                    top.linkTo(text.bottom, 5.dp)
                    start.linkTo(parent.start, 5.dp)
                    end.linkTo(parent.end, 5.dp)
                    bottom.linkTo(parent.bottom, 5.dp)
                },
        ) {
            Stars(state = PointState.values()[levelItemModel.levelState.ordinal], type = LEVEL_TYPE)
        }
    }
}

@Preview
@Composable
fun LevelItemViewLockPreview() {
    LevelItemView(
        levelItemModel = LevelItemModel(0, PointState.LOCK, 1, 100, LevelType.TUTORIAL),
        navigateToLevel = { levelId, levelNumber , worldId -> },
        worldId = 0
    )
}

@Preview
@Composable
fun LevelItemViewZeroPreview() {
    LevelItemView(
        levelItemModel = LevelItemModel(1, PointState.ZERO, 1, 100, LevelType.TUTORIAL),
        navigateToLevel = { levelId, levelNumber , worldId-> },
        worldId = 0
    )
}

@Preview
@Composable
fun LevelItemViewOnePreview() {
    LevelItemView(
        levelItemModel = LevelItemModel(0, PointState.ONE, 1, 100, LevelType.TUTORIAL),
        navigateToLevel = { levelId, levelNumber , worldId -> },
        worldId = 0
    )
}

@Preview
@Composable
fun LevelItemViewTwoPreview() {
    LevelItemView(
        levelItemModel = LevelItemModel(0, PointState.TWO, 1, 100, LevelType.TUTORIAL),
        navigateToLevel = { levelId, levelNumber , worldId -> },
        worldId = 0
    )
}

@Preview
@Composable
fun LevelItemViewThreePreview() {
    LevelItemView(
        levelItemModel = LevelItemModel(0, PointState.THREE, 1, 100, LevelType.TUTORIAL),
        navigateToLevel = { levelId, levelNumber , worldId -> },
        worldId = 0
    )
}