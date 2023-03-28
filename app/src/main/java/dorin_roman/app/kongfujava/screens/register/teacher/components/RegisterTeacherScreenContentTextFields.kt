package dorin_roman.app.kongfujava.screens.register.teacher.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.screens.register.RegisterEvent
import dorin_roman.app.kongfujava.screens.register.RegisterViewModel
import dorin_roman.app.kongfujava.screens.register.teacher.TeacherRegisterEvent
import dorin_roman.app.kongfujava.screens.register.teacher.TeacherRegisterViewModel
import dorin_roman.app.kongfujava.ui.components.EmailField
import dorin_roman.app.kongfujava.ui.components.PasswordField
import dorin_roman.app.kongfujava.ui.components.TextFieldWithIcons
import dorin_roman.app.kongfujava.ui.theme.spacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterTeacherScreenContentTextFields(
    registerViewModel: RegisterViewModel,
    teacherRegisterViewModel: TeacherRegisterViewModel,
    navigateToSupervisorLoginScreen: (userType: UserType) -> Unit
) {
    val keyboard = LocalSoftwareKeyboardController.current

    if (registerViewModel.verifyStep) {
        Text(text = stringResource(R.string.we_sent_verification_email))

        Button(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(MaterialTheme.spacing.medium),
            onClick = {
                keyboard?.hide()
                teacherRegisterViewModel.handle(TeacherRegisterEvent.ReloadUser)
            }
        ) {
            Text(text = stringResource(R.string.already_verified))
        }
    } else {
        EmailField(
            email = registerViewModel.email,
            onEmailValueChange = {
                registerViewModel.handle(RegisterEvent.UpdateEmailText(it))
            }
        )

        PasswordField(
            password = registerViewModel.password,
            onPasswordValueChange = {
                registerViewModel.handle(RegisterEvent.UpdatePasswordText(it))
            }
        )

        TextFieldWithIcons(
            label = R.string.register_class,
            placeholder = R.string.register_enter_class,
            icon = Icons.Default.LocalLibrary,
            text = teacherRegisterViewModel.className,
            onTextChange = {
                teacherRegisterViewModel.handle(TeacherRegisterEvent.UpdateClassText(it))
            }
        )

        TextFieldWithIcons(
            label = R.string.register_education,
            placeholder = R.string.register_enter_education,
            icon = Icons.Default.School,
            text = teacherRegisterViewModel.schoolName,
            onTextChange = {
                teacherRegisterViewModel.handle(TeacherRegisterEvent.UpdateSchoolText(it))
            }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(MaterialTheme.spacing.medium),
            onClick = {
                keyboard?.hide()
                registerViewModel.handle(RegisterEvent.Register)
            }
        ) {
            Text(text = stringResource(id = R.string.register).uppercase())
        }
    }

    if (registerViewModel.showLoading || teacherRegisterViewModel.showLoading) {
        CircularProgressIndicator()
    }

}