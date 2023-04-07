package dorin_roman.app.kongfujava.screens.register.parent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.screens.register.RegisterEvent
import dorin_roman.app.kongfujava.screens.register.RegisterViewModel
import dorin_roman.app.kongfujava.screens.register.parent.components.RegisterParentScreenContent
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.VerticalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun ParentRegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    parentRegisterViewModel: ParentRegisterViewModel = hiltViewModel(),
    navigateToSupervisorLoginScreen: (userType: UserType) -> Unit
) {
    VerticalFortySixtyLayout(
        fortyLayout = {
            RegisterParentScreenContent(
                registerViewModel = registerViewModel,
                parentRegisterViewModel = parentRegisterViewModel,
                navigateToSupervisorLoginScreen = navigateToSupervisorLoginScreen
            )
        },
        sixtyLayout = {
            SideScreenImage(R.drawable.ic_panda_register)
        }
    )

    LaunchedEffect(registerViewModel.registerRequest) {
        registerViewModel.handle(RegisterEvent.RegisterResponse)
    }

    LaunchedEffect(registerViewModel.sendEmailVerificationRequest) {
        registerViewModel.handle(RegisterEvent.SendEmailVerificationResponse)
    }

    LaunchedEffect(parentRegisterViewModel.reloadUserRequest) {
        parentRegisterViewModel.handle(ParentRegisterEvent.ReloadUserResponse)
    }

    LaunchedEffect(parentRegisterViewModel.saveUserRequest) {
        parentRegisterViewModel.handle(ParentRegisterEvent.SaveUserToDatabaseResponse)
    }
}

@DevicePreviews
@Composable
fun ParentRegisterScreenPreview() {
    KongFuJavaTheme {
        ParentRegisterScreen {}
    }
}