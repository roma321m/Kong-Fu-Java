package dorin_roman.app.kongfujava.screens.worlds.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.domain.models.World

@Composable
fun WorldsMapButtons(modifier: Modifier, navigateToLevel: () -> Unit, worlds: List<World>) {
    LazyRow(
        modifier = modifier,
        userScrollEnabled = true,
    ) {
        items(worlds) {
            WorldsItemView(world = it, navigateToLevel = navigateToLevel)
        }
    }
}