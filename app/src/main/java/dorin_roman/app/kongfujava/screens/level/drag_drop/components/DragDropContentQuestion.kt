package dorin_roman.app.kongfujava.screens.level.drag_drop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.ui.components.TextJavaStyle
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun DragDropContentQuestion(
    questionText: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
            .padding(start = MaterialTheme.spacing.large)
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(start = MaterialTheme.spacing.large)
                .fillMaxSize(),
        ) {
            TextJavaStyle(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.large)
                    .padding(top = MaterialTheme.spacing.extraLarge)
                    .fillMaxSize(0.95f),
                text = questionText,
                style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onBackground)
            )
        }
    }

}