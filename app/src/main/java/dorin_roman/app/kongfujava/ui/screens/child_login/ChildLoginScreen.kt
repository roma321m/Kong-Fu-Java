package dorin_roman.app.kongfujava.ui.screens.child_login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.ui.theme.spacing

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.large),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is a temp UI for the child login screen")
        Button(onClick = navigateToMainScreen) {
            Text(text = "go to main")
        }
    }
}