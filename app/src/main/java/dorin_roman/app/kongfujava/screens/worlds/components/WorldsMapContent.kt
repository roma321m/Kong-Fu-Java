package dorin_roman.app.kongfujava.screens.worlds.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.worlds.WorldsMapViewModel
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.TopBar
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun WorldsMapContent(
    navigateToLevel: () -> Unit,
    worldsMapViewModel: WorldsMapViewModel
) {
    Scaffold(
        topBar = {
            TopBar(onBackPressed = {}, title = R.string.worlds_map)
        }
    ) { padding ->

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.secondary)
                .padding(MaterialTheme.spacing.large)
        ) {
            val (worlds) = createRefs()

            WorldsMapButtons(
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(worlds)
                    {
                        linkTo(start = parent.start, end = parent.end)
                        top.linkTo(parent.top, 20.dp)
                    },
                navigateToLevel = navigateToLevel
            )

        }
    }

}

@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        WorldsMapContent({}, viewModel())
    }
}
