package dorin_roman.app.kongfujava.screens.level.multi_choice.components

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.ui.theme.Error
import dorin_roman.app.kongfujava.ui.theme.Right

@Composable
fun GetColorMultiChoiceAnswers(color: ColorState): ButtonColors {
    return when (color) {
        ColorState.RIGHT -> {
            ButtonDefaults.buttonColors(MaterialTheme.colors.Right)
        }

        ColorState.HINT, ColorState.MISTAKE -> {
            ButtonDefaults.buttonColors(MaterialTheme.colors.Error)
        }

        ColorState.REGULAR -> {
            ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
        }
    }
}