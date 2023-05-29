package dorin_roman.app.kongfujava.screens.level.levels_map.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.screens.level.LevelType
import dorin_roman.app.kongfujava.screens.level.levels_map.LevelItemModel
import dorin_roman.app.kongfujava.ui.components.mirror
import kotlinx.coroutines.delay


@Composable
fun LevelsMapContent(
    levels: List<LevelItemModel>,
    navigateToTutorialLevel: (Int, Int, Int) -> Unit,
    navigateToMultiChoiceLevel: (Int, Int, Int) -> Unit,
    navigateToDragDropLevel: (Int, Int, Int) -> Unit,
    itemContent: @Composable (
        LevelItemModel,
        (Int, Int, Int) -> Unit
    ) -> Unit,
    modifier: Modifier = Modifier,
    numberOfRows: Int = 6,
    numberOfColumns: Int = 5,
) {
    val density = LocalDensity.current
    var itemHeightDp by remember {
        mutableStateOf(0.dp)
    }
    var itemWidthDp by remember {
        mutableStateOf(0.dp)
    }

    var showImage by remember {
        mutableStateOf(-1)
    }

    LaunchedEffect(levels.size) {
        if (levels.isNotEmpty()) {
            var passedCount = -1
            levels.forEach {
                if (it.levelState != PointState.LOCK) {
                    passedCount++
                }
            }
            while (passedCount != showImage) {
                showImage += 1
                if (passedCount != 0) {
                    if (passedCount > 10) {
                        delay(200)
                    } else {
                        delay(2_000 / passedCount.toLong())
                    }
                }
            }
        }
    }

    Row(
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                itemHeightDp = with(density) { (coordinates.size.height / numberOfColumns).toDp() }
                itemWidthDp = with(density) { (coordinates.size.width / numberOfRows).toDp() }
            }
    ) {
        if (itemHeightDp != 0.dp && itemWidthDp != 0.dp) {
            repeat(numberOfRows) { row ->
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(itemWidthDp)
                ) {
                    repeat(numberOfColumns) { column ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(itemHeightDp)
                        ) {
                            if (levels.size == numberOfRows * numberOfColumns) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.BottomStart
                                ) {
                                    itemContent(
                                        levels[row + column * numberOfRows],
                                        when (levels[row + column * numberOfRows].levelType) {
                                            LevelType.TUTORIAL -> navigateToTutorialLevel
                                            LevelType.MULTI -> navigateToMultiChoiceLevel
                                            LevelType.DRAG_DROP -> navigateToDragDropLevel
                                        }
                                    )
                                    if (showImage == row + column * numberOfRows) {
                                        Image(
                                            modifier = Modifier
                                                .fillMaxHeight(0.55f)
                                                .padding(bottom = 20.dp)
                                                .mirror(),
                                            painter = painterResource(R.drawable.img_moving),
                                            contentDescription = null,
                                            alignment = Alignment.CenterStart,
                                            contentScale = ContentScale.FillHeight
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}