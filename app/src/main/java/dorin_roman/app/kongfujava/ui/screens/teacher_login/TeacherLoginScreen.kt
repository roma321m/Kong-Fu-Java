package dorin_roman.app.kongfujava.ui.screens.teacher_login

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun TeacherLoginScreen(
    navigateToMainScreen: () -> Unit
) {
   TempUI(navigateToMainScreen = navigateToMainScreen)
}

@Composable
fun TempUI(
    navigateToMainScreen: () -> Unit
) {
    Text(text = "This is a temp UI for the teacher login screen")
}