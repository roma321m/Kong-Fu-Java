package dorin_roman.app.kongfujava.screens.register.teacher

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.register.RegisterEvent
import dorin_roman.app.kongfujava.screens.register.RegisterViewModel
import dorin_roman.app.kongfujava.screens.register.teacher.components.TeacherRegisterDescription
import dorin_roman.app.kongfujava.screens.register.teacher.components.TeacherRegisterTextFields
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.layout.CustomLayout2
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@Composable
fun TeacherRegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    teacherRegisterViewModel: TeacherRegisterViewModel = hiltViewModel()
) {
    CustomLayout2(
        startTopContent = {
            TeacherRegisterDescription()
        },
        startBottomContent = {
            TeacherRegisterTextFields(
                verifyStep = registerViewModel.verifyStep,
                showLoading = registerViewModel.showLoading || teacherRegisterViewModel.showLoading,
                onReloadClicked = {
                    teacherRegisterViewModel.handle(TeacherRegisterEvent.ReloadUser)
                },
                email = registerViewModel.email,
                onEmailChange = {
                    registerViewModel.handle(RegisterEvent.UpdateEmailText(it))
                },
                password = registerViewModel.password,
                onPasswordChange = {
                    registerViewModel.handle(RegisterEvent.UpdatePasswordText(it))
                },
                className = teacherRegisterViewModel.className,
                onClassNameChange = {
                    teacherRegisterViewModel.handle(TeacherRegisterEvent.UpdateClassText(it))
                },
                schoolName = teacherRegisterViewModel.schoolName,
                onSchoolNameChange = {
                    teacherRegisterViewModel.handle(TeacherRegisterEvent.UpdateSchoolText(it))
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
fun TeacherRegisterScreenPreview() {
    KongFuJavaTheme {
        TeacherRegisterScreen()
    }
}
