package dorin_roman.app.kongfujava.screens.level.multi_choice.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dorin_roman.app.kongfujava.ui.theme.kongFuError
import dorin_roman.app.kongfujava.ui.theme.mediumGray
import dorin_roman.app.kongfujava.ui.theme.right

@Composable
fun getColorMultiChoiceAnswers(color: ColorState): Color {
    return when (color) {
        ColorState.RIGHT -> {
            MaterialTheme.colors.right
        }

        ColorState.HINT, ColorState.MISTAKE -> {
            MaterialTheme.colors.kongFuError
        }

        ColorState.REGULAR -> {
            MaterialTheme.colors.primary
        }

        ColorState.TAKEN -> {
            MaterialTheme.colors.mediumGray
        }

        ColorState.DROP -> {
            MaterialTheme.colors.background
        }
    }
}