package dorin_roman.app.kongfujava.ui.screens.login.teacher

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
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.HorizontalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.screens.login.teacher.content.TeacherLoginScreenContent
import dorin_roman.app.kongfujava.ui.theme.spacing

//FIXME need to check if the size are good
@Composable
fun TeacherLoginScreen(
    navigateToMainScreen: () -> Unit
) {
    HorizontalFortySixtyLayout(
        fortyLayout = {
            TeacherLoginScreenContent(
                navigateToMainScreen = navigateToMainScreen
            )
        },
        sixtyLayout = {
            SideScreenImage(R.drawable.ic_panda_login)
        }
    )
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
        Text(text = "This is a temp UI for the teacher login screen")
        Button(onClick = navigateToMainScreen) {
            Text(text = "go to main")
        }
    }
}