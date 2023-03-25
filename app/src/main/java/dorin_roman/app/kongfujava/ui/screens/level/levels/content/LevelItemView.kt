package dorin_roman.app.kongfujava.ui.screens.level.levels.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import dorin_roman.app.kongfujava.models.LevelItemModel
import dorin_roman.app.kongfujava.ui.components.*
import dorin_roman.app.kongfujava.util.PointState

@Composable
fun LevelItemView(
    levelItemModel: LevelItemModel
) {
    ConstraintLayout(
        modifier = Modifier.size(400.dp)
    ) {
        val (levelBox, text, score) = createRefs()
        Box(
            modifier = Modifier
                .alpha(0.8f)
                .constrainAs(levelBox) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        top = parent.top,
                        bottom = parent.bottom
                    )
                }
                .background(color = MaterialTheme.colors.primary)
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    color = Color.Black.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxSize()
                .aspectRatio(1f)
        )

        Text(
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(levelBox.top, 5.dp)
                    bottom.linkTo(levelBox.bottom, 5.dp)
                    start.linkTo(levelBox.start, 5.dp)
                    end.linkTo(levelBox.end, 5.dp)
                },
            text = levelItemModel.levelNumber.toString(),
            style = MaterialTheme.typography.h2.copy(
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
                    start.linkTo(levelBox.start, 5.dp)
                    end.linkTo(levelBox.end, 5.dp)
                },
        ) {
            when (levelItemModel.levelState) {
                PointState.LOCK -> {
                    LockLevel()
                }
                PointState.ZERO -> {
                    ZeroLevelYellow()
                }
                PointState.ONE -> {
                    OneLevel()
                }
                PointState.TWO -> {
                    TwoLevel()
                }
                PointState.THREE -> {
                    ThreeLevel()
                }
            }
        }


//            Box(modifier = Modifier
//                .constrainAs(gradient) {
//                    linkTo(
//                        start = parent.start,
//                        end = parent.end,
//                        top = parent.top,
//                        bottom = parent.bottom
//                    )
//                }
//                .clip(RoundedCornerShape(8.dp))
//                .background(
//                    brush = Brush.verticalGradient(
//                        0F to Color.Transparent,
//                        0.5F to Color.Black.copy(alpha = 0f),
//                        1F to Color.Black.copy(alpha = 0.6f)
//                    )
//                )
//                .fillMaxSize()
//                .aspectRatio(1f)
//            )
//        if (extension != null) {
//            Text(
//                modifier = Modifier.constrainAs(text) {
//                    end.linkTo(thumbnail.end, 5.dp)
//                    bottom.linkTo(thumbnail.bottom, 5.dp)
//                },
//                text = extension.uppercase(),
//                style = MaterialTheme.typography.subtitle2.copy(
//                    color = Color.White,
//                    fontWeight = FontWeight.SemiBold,
//                    lineHeight = 12.sp,
//                    letterSpacing = 0.25.sp,
//                ),
//                textAlign = TextAlign.End
//            )
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun LevelItemViewLockPreview() {
    LevelItemView(levelItemModel = LevelItemModel(PointState.LOCK, 1, 100))
}

@Preview(showBackground = true)
@Composable
fun LevelItemViewZeroPreview() {
    LevelItemView(levelItemModel = LevelItemModel(PointState.ZERO, 1, 100))
}

@Preview(showBackground = true)
@Composable
fun LevelItemViewOnePreview() {
    LevelItemView(levelItemModel = LevelItemModel(PointState.ONE, 1, 100))
}

@Preview(showBackground = true)
@Composable
fun LevelItemViewTwoPreview() {
    LevelItemView(levelItemModel = LevelItemModel(PointState.TWO, 1, 100))
}

@Preview(showBackground = true)
@Composable
fun LevelItemViewThreePreview() {
    LevelItemView(levelItemModel = LevelItemModel(PointState.THREE, 1, 100))
}