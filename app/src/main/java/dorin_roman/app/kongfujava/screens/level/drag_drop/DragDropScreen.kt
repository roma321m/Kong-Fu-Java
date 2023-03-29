package dorin_roman.app.kongfujava.screens.level.drag_drop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.level.drag_drop.components.DragDropLeftScreenContent
import dorin_roman.app.kongfujava.screens.level.drag_drop.components.DragDropRightScreenContent
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.TopBar
import dorin_roman.app.kongfujava.ui.components.VerticalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun DragDropScreen() {
    //fixme - temp
    Scaffold(
        topBar = {
            TopBar(onBackPressed = {}, title = R.string.drag_drop_questions)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(MaterialTheme.colors.secondary)
                .padding(padding)
        ) {
            VerticalFortySixtyLayout(
                fortyLayout = {
                    DragDropLeftScreenContent()
                },
                sixtyLayout = {
                    DragDropRightScreenContent()
                }
            )
        }
    }
}


@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        DragDropScreen()
    }
}