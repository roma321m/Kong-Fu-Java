package dorin_roman.app.kongfujava.screens.login.supervisor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.screens.login.supervisor.components.SupervisorLoginContentTopEnd
import dorin_roman.app.kongfujava.screens.login.supervisor.components.SupervisorLoginContentTopStart
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.layout.CustomLayout1
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@Composable
fun SupervisorLoginScreen(
    viewModel: SupervisorLoginViewModel = hiltViewModel(),
    navigateToTeacherRegisterScreen: () -> Unit,
    navigateToParentRegisterScreen: () -> Unit,
    userType: UserType
) {
    CustomLayout1(
        topStartContent = {
            SupervisorLoginContentTopStart()
        },
        topEndContent = {
            SupervisorLoginContentTopEnd(
                showLoading = viewModel.showLoading,
                email = viewModel.email,
                password = viewModel.password,
                onEmailChange = { newEmail ->
                    viewModel.handle(SupervisorLoginEvent.UpdateEmailText(newEmail))
                },
                onPasswordChange = { newPassword ->
                    viewModel.handle(SupervisorLoginEvent.UpdatePasswordText(newPassword))
                },
                onLoginClicked = {
                    viewModel.handle(SupervisorLoginEvent.Login)
                },
                onRegisterClicked = {
                    if (viewModel.userType == UserType.Teacher) {
                        navigateToTeacherRegisterScreen()
                    } else if (viewModel.userType == UserType.Parent) {
                        navigateToParentRegisterScreen()
                    }
                },
            )
        },
        bottomContent = {
            SideScreenImage(R.drawable.ic_panda_login)
        },
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