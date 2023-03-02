package dorin_roman.app.kongfujava.ui.screens.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun MainScreen() {
    // TODO - build 3 different navigations, on for each user
    Text(text = "This is the main screen")
}

@DevicePreviews
@Composable
fun MainScreenPreview() {
    KongFuJavaTheme {
        MainScreen()
    }
}