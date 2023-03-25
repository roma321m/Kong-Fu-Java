package dorin_roman.app.kongfujava.screens.register.teacher

import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.screens.register.teacher.content.RegisterTeacherScreenContent
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.VerticalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun TeacherRegisterScreen(
    navigateToSupervisorLoginScreen: (userType: UserType) -> Unit
) {
    VerticalFortySixtyLayout(
        fortyLayout = {
            RegisterTeacherScreenContent(
                navigateToSupervisorLoginScreen = navigateToSupervisorLoginScreen
            )
        },
        sixtyLayout = {
            SideScreenImage(R.drawable.ic_panda_register)
        }
    )
}

@DevicePreviews
@Composable
fun TeacherRegisterScreenPreview() {
    KongFuJavaTheme {
        TeacherRegisterScreen {}
    }
}