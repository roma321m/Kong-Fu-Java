package dorin_roman.app.kongfujava.screens.register.teacher.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.screens.register.RegisterViewModel
import dorin_roman.app.kongfujava.screens.register.teacher.TeacherRegisterViewModel
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun RegisterTeacherScreenContent(
    registerViewModel: RegisterViewModel,
    teacherRegisterViewModel: TeacherRegisterViewModel,
    navigateToSupervisorLoginScreen: (userType: UserType) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxHeight()
    ) {
        Spacer(
            modifier = Modifier
                .weight(0.05f)
                .fillMaxWidth()
        )

        Box(modifier = Modifier.weight(0.2f)) {
            RegisterTeacherScreenContentImage()
        }

        Column(
            modifier = Modifier
                .weight(0.35f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RegisterScreenContentTexts()
        }

        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RegisterTeacherScreenContentTextFields(
                registerViewModel = registerViewModel,
                teacherRegisterViewModel = teacherRegisterViewModel,
                navigateToSupervisorLoginScreen = navigateToSupervisorLoginScreen
            )
        }

        Spacer(
            modifier = Modifier
                .weight(0.05f)
                .fillMaxWidth()
        )
    }
}