package dorin_roman.app.kongfujava.ui.screens.login.teacher_parent


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.HorizontalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.screens.login.teacher_parent.content.TeacherLoginScreenContent
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import dorin_roman.app.kongfujava.view_models.MainEvent
import dorin_roman.app.kongfujava.view_models.MainViewModel

//FIXME need to check if the size are good
@Composable
fun TeacherParentLoginScreen(
    navigateToMainScreen: () -> Unit,
    navigateToTeacherRegisterScreen: () -> Unit,
    navigateToParentRegisterScreen: () -> Unit,
    mainViewModel: MainViewModel,
    userType: Int
) {
    HorizontalFortySixtyLayout(
        fortyLayout = {
            TeacherLoginScreenContent(
                navigateToMainScreen = navigateToMainScreen,
                navigateToTeacherRegisterScreen = navigateToTeacherRegisterScreen,
                navigateToParentRegisterScreen = navigateToParentRegisterScreen,
                onLoginClicked = {
                    mainViewModel.handle(MainEvent.Teacher) // FIXME - temp, handle login instead of this
                },
                userType = userType
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
        TeacherParentLoginScreen({}, {}, {}, viewModel(), 0)
    }
}