package dorin_roman.app.kongfujava.ui.screens.login.teacher


import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.HorizontalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.screens.login.teacher.content.TeacherLoginScreenContent
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

//FIXME need to check if the size are good
@Composable
fun TeacherLoginScreen(
    navigateToMainScreen: () -> Unit,
    navigateToTeacherRegisterScreen: () -> Unit,
) {
    HorizontalFortySixtyLayout(
        fortyLayout = {
            TeacherLoginScreenContent(
                navigateToMainScreen = navigateToMainScreen,
                navigateToTeacherRegisterScreen = navigateToTeacherRegisterScreen
            )
        },
        sixtyLayout = {
            SideScreenImage(R.drawable.ic_panda_login)
        }
    )
}

@DevicePreviews
@Composable
fun TeacherLoginScreenPreview() {
    KongFuJavaTheme {
        TeacherLoginScreen({}, {})
    }
}