package dorin_roman.app.kongfujava.screens.register.teacher

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.screens.register.RegisterEvent
import dorin_roman.app.kongfujava.screens.register.RegisterViewModel
import dorin_roman.app.kongfujava.screens.register.teacher.components.RegisterTeacherScreenContent
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.VerticalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun TeacherRegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    teacherRegisterViewModel: TeacherRegisterViewModel = hiltViewModel(),
    navigateToSupervisorLoginScreen: (userType: UserType) -> Unit
) {
    VerticalFortySixtyLayout(
        fortyLayout = {
            RegisterTeacherScreenContent(
                registerViewModel = registerViewModel,
                teacherRegisterViewModel = teacherRegisterViewModel,
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

    LaunchedEffect(teacherRegisterViewModel.reloadUserRequest) {
        teacherRegisterViewModel.handle(TeacherRegisterEvent.ReloadUserResponse)
    }

    LaunchedEffect(teacherRegisterViewModel.saveUserRequest) {
        teacherRegisterViewModel.handle(TeacherRegisterEvent.SaveUserToDatabaseResponse)
    }
}

@DevicePreviews
@Composable
fun TeacherRegisterScreenPreview() {
    KongFuJavaTheme {
        TeacherRegisterScreen {}
    }
}
