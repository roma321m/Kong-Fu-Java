package dorin_roman.app.kongfujava.ui.screens.register.parent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.VerticalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.screens.register.parent.components.RegisterParentScreenContent
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun ParentRegisterScreen(
    viewModel: ParentRegisterViewModel = hiltViewModel(),
    navigateToSupervisorLoginScreen: (userType: UserType) -> Unit
) {
    VerticalFortySixtyLayout(
        fortyLayout = {
            RegisterParentScreenContent(
                viewModel = viewModel,
                navigateToSupervisorLoginScreen = navigateToSupervisorLoginScreen
            )
        },
        sixtyLayout = {
            SideScreenImage(R.drawable.ic_panda_register)
        }
    )

    LaunchedEffect(viewModel.registerRequest) {
        viewModel.handle(ParentRegisterEvent.RegisterResponse)
    }

    LaunchedEffect(viewModel.sendEmailVerificationRequest) {
        viewModel.handle(ParentRegisterEvent.SendEmailVerificationResponse)
    }

    LaunchedEffect(viewModel.reloadUserRequest) {
        viewModel.handle(ParentRegisterEvent.ReloadUserResponse)
    }
}

@DevicePreviews
@Composable
fun ParentRegisterScreenPreview() {
    KongFuJavaTheme {
        ParentRegisterScreen {}
    }
}