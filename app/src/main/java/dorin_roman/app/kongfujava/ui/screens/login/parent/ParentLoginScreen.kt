package dorin_roman.app.kongfujava.ui.screens.login.parent

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
import androidx.lifecycle.viewmodel.compose.viewModel
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import dorin_roman.app.kongfujava.ui.theme.spacing
import dorin_roman.app.kongfujava.view_models.MainEvent
import dorin_roman.app.kongfujava.view_models.MainViewModel

@Composable
fun ParentLoginScreen(
    navigateToMainScreen: () -> Unit,
    navigateToParentRegisterScreen: () -> Unit,
    mainViewModel: MainViewModel
) {
    TempUI(
        navigateToMainScreen = navigateToMainScreen,
        navigateToParentRegisterScreen = navigateToParentRegisterScreen,
        onLoginClicked = {
            mainViewModel.handle(MainEvent.Parent) // FIXME - temp, handle login instead of this
        }
    )
}

@Composable
fun TempUI(
    navigateToMainScreen: () -> Unit,
    navigateToParentRegisterScreen: () -> Unit,
    onLoginClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.large),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is a temp UI for the parent login screen")
        Button(
            onClick = {
                onLoginClicked()
                navigateToMainScreen()
            }
        ) {
            Text(text = "go to main")
        }
        Button(onClick = navigateToParentRegisterScreen) {
            Text(text = "go to parent register")
        }
    }
}

@DevicePreviews
@Composable
fun ParentLoginScreenPreview() {
    KongFuJavaTheme {
        ParentLoginScreen({}, {}, viewModel())
    }
}