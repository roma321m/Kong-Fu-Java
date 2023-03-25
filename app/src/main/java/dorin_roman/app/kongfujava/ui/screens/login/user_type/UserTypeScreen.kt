package dorin_roman.app.kongfujava.ui.screens.login.user_type

import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.VerticalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.screens.login.user_type.content.UserTypeScreenContent
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun UserTypeScreen(
    navigateToLoginScreen: (UserType) -> Unit
) {
    VerticalFortySixtyLayout(
        fortyLayout = {
            UserTypeScreenContent(
                navigateToLoginScreen = navigateToLoginScreen
            )
        },
        sixtyLayout = {
            SideScreenImage(R.drawable.ic_panda_usertype)
        }
    )
}

@DevicePreviews
@Composable
fun UserTypeScreenPreview() {
    KongFuJavaTheme {
        UserTypeScreen { }
    }
}