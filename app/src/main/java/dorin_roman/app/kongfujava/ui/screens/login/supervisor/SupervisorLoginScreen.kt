package dorin_roman.app.kongfujava.ui.screens.login.supervisor


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.HorizontalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.screens.login.supervisor.components.SupervisorLoginScreenContent
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun SupervisorLoginScreen(
    viewModel: SupervisorLoginViewModel = hiltViewModel(),
    navigateToTeacherRegisterScreen: () -> Unit,
    navigateToParentRegisterScreen: () -> Unit,
    userType: UserType
) {
    HorizontalFortySixtyLayout(
        fortyLayout = {
            SupervisorLoginScreenContent(
                navigateToTeacherRegisterScreen = navigateToTeacherRegisterScreen,
                navigateToParentRegisterScreen = navigateToParentRegisterScreen,
                viewModel = viewModel
            )
        },
        sixtyLayout = {
            SideScreenImage(R.drawable.ic_panda_login)
        }
    )

    LaunchedEffect(key1 = true) {
        viewModel.handle(SupervisorLoginEvent.UpdateUserTypeLogin(userType))
    }

    LaunchedEffect(viewModel.loginRequest) {
        viewModel.handle(SupervisorLoginEvent.LoginResponse)
    }
}

@DevicePreviews
@Composable
fun TeacherLoginScreenPreview() {
    KongFuJavaTheme {
        SupervisorLoginScreen(
            navigateToTeacherRegisterScreen = {},
            navigateToParentRegisterScreen = {},
            userType = UserType.None
        )
    }
}