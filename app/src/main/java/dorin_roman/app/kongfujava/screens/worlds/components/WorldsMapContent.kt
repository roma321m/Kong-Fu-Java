package dorin_roman.app.kongfujava.screens.worlds.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dorin_roman.app.kongfujava.domain.models.World
import dorin_roman.app.kongfujava.ui.components.DevicePreviews

@Composable
fun WorldsMapContent(
    modifier: Modifier = Modifier,
    worlds: List<World>,
    navigateToLevel: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (buttons) = createRefs()
        LazyRow(
            modifier = Modifier
                .constrainAs(buttons) {
                    linkTo(start = parent.start, end = parent.end)
                    linkTo(top = parent.top, bottom = parent.bottom)
                },
            contentPadding = PaddingValues(20.dp),
            userScrollEnabled = true,
        ) {
            items(worlds) {
                WorldsItemView(world = it, navigateToLevel = navigateToLevel)
            }
        }
    }
}

@DevicePreviews
@Composable
fun WorldsMapContentPreview() {
    WorldsMapContent(
        worlds = listOf(
            World(0, "test", 0, 0),
            World(0, "test", 0, 0),
            World(0, "test", 0, 0),
            World(0, "test", 0, 0),
            World(0, "test", 0, 0)
        ),
        navigateToLevel = {}
    )
}