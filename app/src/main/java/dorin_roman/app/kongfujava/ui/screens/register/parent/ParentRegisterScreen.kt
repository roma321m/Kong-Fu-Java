package dorin_roman.app.kongfujava.ui.screens.register.parent

import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.VerticalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.screens.register.parent.content.RegisterParentScreenContent
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun ParentRegisterScreen(
    navigateToTeacherParentLoginScreen: () -> Unit
) {
    VerticalFortySixtyLayout(
        fortyLayout = {
            RegisterParentScreenContent(
                navigateToTeacherParentLoginScreen = navigateToTeacherParentLoginScreen
            )
        },
        sixtyLayout = {
            SideScreenImage(R.drawable.ic_panda_register)
        }
    )
}

@DevicePreviews
@Composable
fun ParentRegisterScreenPreview() {
    KongFuJavaTheme {
        ParentRegisterScreen {}
    }
}