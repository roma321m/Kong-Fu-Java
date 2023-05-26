package dorin_roman.app.kongfujava.screens.level.multi_choice.components

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.ui.theme.kongFuError
import dorin_roman.app.kongfujava.ui.theme.right

@Composable
fun getColorMultiChoiceAnswers(color: ColorState): ButtonColors {
    return when (color) {
        ColorState.RIGHT -> {
            ButtonDefaults.buttonColors(MaterialTheme.colors.right)
        }

        ColorState.HINT, ColorState.MISTAKE -> {
            ButtonDefaults.buttonColors(MaterialTheme.colors.kongFuError)
        }

        ColorState.REGULAR -> {
            ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
        }
    }
}