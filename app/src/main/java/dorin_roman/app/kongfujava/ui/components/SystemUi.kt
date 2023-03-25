package dorin_roman.app.kongfujava.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dorin_roman.app.kongfujava.ui.theme.systemUi

@Composable
fun SystemUi() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.systemUi,
        darkIcons = isSystemInDarkTheme().not()
    )
}