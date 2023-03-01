package dorin_roman.app.kongfujava.ui.screens.register

import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.VerticalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.screens.login.main.content.RegisterScreenContent

@Composable
fun RegisterScreen(
    navigateToUserTypeScreen: () -> Unit
) {
    VerticalFortySixtyLayout(
        fortyLayout = {
            RegisterScreenContent(
                navigateToUserTypeScreen = navigateToUserTypeScreen
            )
        },
        sixtyLayout = {
            SideScreenImage(R.drawable.ic_panda_register)
        }
    )
}

