package dorin_roman.app.kongfujava.ui.screens.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreen(
    navigateToTemp1: () -> Unit,
    navigateToTemp2: () -> Unit
) {
    Text(text = "This is the main screen")
}