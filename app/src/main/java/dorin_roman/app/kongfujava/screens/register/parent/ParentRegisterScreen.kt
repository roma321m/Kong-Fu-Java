package dorin_roman.app.kongfujava.screens.register.parent

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.register.RegisterEvent
import dorin_roman.app.kongfujava.screens.register.RegisterViewModel
import dorin_roman.app.kongfujava.screens.register.parent.components.ParentRegisterDescription
import dorin_roman.app.kongfujava.screens.register.parent.components.ParentRegisterTextFields
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.layout.CustomLayout2
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@Composable
fun ParentRegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    parentRegisterViewModel: ParentRegisterViewModel = hiltViewModel(),
) {
    CustomLayout2(
        startTopContent = {
            ParentRegisterDescription()
        },
        startBottomContent = {
            ParentRegisterTextFields(
                verifyStep = registerViewModel.verifyStep,
                showLoading = registerViewModel.showLoading || parentRegisterViewModel.showLoading,
                onReloadClicked = {
                    parentRegisterViewModel.handle(ParentRegisterEvent.ReloadUser)
                },
                email = registerViewModel.email,
                onEmailChange = {
                    registerViewModel.handle(RegisterEvent.UpdateEmailText(it))
                },
                password = registerViewModel.password,
                onPasswordChange = {
                    registerViewModel.handle(RegisterEvent.UpdatePasswordText(it))
                },
                onRegisterClicked = {
                    registerViewModel.handle(RegisterEvent.Register)
                }
            )
        },
        endContent = {
            SideScreenImage(R.drawable.ic_panda_register)
        }
    )
}

@DevicePreviews
@Composable
fun ParentRegisterScreenPreview() {
    KongFuJavaTheme {
        ParentRegisterScreen()
    }
}