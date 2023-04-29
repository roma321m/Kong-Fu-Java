package dorin_roman.app.kongfujava.screens.level.levels_map.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.screens.level.LevelType
import dorin_roman.app.kongfujava.screens.level.levels_map.LevelItemModel

@Composable
fun LevelsMapContent(
    modifier: Modifier,
    levels: SnapshotStateList<LevelItemModel>,
    navigateToTutorialLevel: () -> Unit,
    navigateToMultiChoiceLevel: () -> Unit,
    navigateToDragDropLevel: () -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(6),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        userScrollEnabled = false,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalArrangement = Arrangement.SpaceBetween,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(levels) { level ->
            LevelItemView(
                modifier = Modifier.fillMaxHeight(fraction = 0.18f),
                levelItemModel = level,
                navigateToLevel = when (level.levelType) {
                    LevelType.TUTORIAL -> navigateToTutorialLevel
                    LevelType.MULTI -> navigateToMultiChoiceLevel
                    LevelType.DRAG_DROP -> navigateToDragDropLevel
                }
            )
        }
    }
}