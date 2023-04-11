package dorin_roman.app.kongfujava.screens.worlds.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dorin_roman.app.kongfujava.domain.models.World

@Composable
fun WorldsMapContent(modifier: Modifier, worlds: List<World>, navigateToLevel: () -> Unit) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (buttons) = createRefs()
        WorldsMapButtons(
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(buttons)
                {
                    linkTo(start = parent.start, end = parent.end)
                    linkTo(top = parent.top, topMargin = 20.dp, bottom = parent.bottom, bottomMargin = 20.dp)
                },
            navigateToLevel = navigateToLevel,
            worlds = worlds
        )
    }
}