package dorin_roman.app.kongfujava.ui.screens.login.teacher


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.HorizontalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.screens.login.teacher.content.TeacherLoginScreenContent
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import dorin_roman.app.kongfujava.view_models.MainEvent
import dorin_roman.app.kongfujava.view_models.MainViewModel

//FIXME need to check if the size are good
@Composable
fun TeacherLoginScreen(
    navigateToMainScreen: () -> Unit,
    navigateToTeacherRegisterScreen: () -> Unit,
    mainViewModel: MainViewModel
) {
    HorizontalFortySixtyLayout(
        fortyLayout = {
            TeacherLoginScreenContent(
                navigateToMainScreen = navigateToMainScreen,
                navigateToTeacherRegisterScreen = navigateToTeacherRegisterScreen,
                onLoginClicked = {
                    mainViewModel.handle(MainEvent.Teacher) // FIXME - temp, handle login instead of this
                }
            )
        },
        sixtyLayout = {
            SideScreenImage(R.drawable.ic_panda_login)
        }
    )
}

@DevicePreviews
@Composable
fun TeacherLoginScreenPreview() {
    KongFuJavaTheme {
        TeacherLoginScreen({}, {}, viewModel())
    }
}