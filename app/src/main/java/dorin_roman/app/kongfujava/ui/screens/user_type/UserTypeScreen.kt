package dorin_roman.app.kongfujava.ui.screens.user_type

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
import androidx.compose.ui.tooling.preview.Preview
import dorin_roman.app.kongfujava.ui.theme.spacing
import dorin_roman.app.kongfujava.util.UserType

@Composable
fun UserTypeScreen(
    navigateToLoginScreen: (UserType) -> Unit
) {
    TempUI(navigateToLoginScreen = navigateToLoginScreen)
}

@Composable
fun TempUI(
    navigateToLoginScreen: (UserType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.large),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is a temp ui for the user type screen", style = MaterialTheme.typography.h5)

        Button(
            onClick = {
                navigateToLoginScreen(UserType.teacher)
            }
        ) {
            Text(text = "Go to Teacher login screen")
        }

        Button(
            onClick = {
                navigateToLoginScreen(UserType.child)
            }
        ) {
            Text(text = "Go to Child login screen")
        }
    }
}


@Preview
@Composable
private fun UserTypeScreenPreview() {
    UserTypeScreen { }
}