package dorin_roman.app.kongfujava.ui.screens.child_login

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ChildLoginScreen(
    navigateToMainScreen: () -> Unit
) {
    TempUI(navigateToMainScreen = navigateToMainScreen)
}

@Composable
fun TempUI(
    navigateToMainScreen: () -> Unit
) {
    Text(text = "This is a temp UI for the child login screen")
}