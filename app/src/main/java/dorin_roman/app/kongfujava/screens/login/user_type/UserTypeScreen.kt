package dorin_roman.app.kongfujava.screens.login.user_type

import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.screens.login.user_type.components.UserTypeButtons
import dorin_roman.app.kongfujava.screens.login.user_type.components.UserTypeDescription
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.image.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.layout.CustomLayout2
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@Composable
fun UserTypeScreen(
    navigateToLoginScreen: (UserType) -> Unit
) {
    CustomLayout2(
        startTopContent = {
            UserTypeDescription()
        },
        startBottomContent = {
            UserTypeButtons(navigateToLoginScreen = navigateToLoginScreen)
        },
        endContent = {
            SideScreenImage(R.drawable.img_panda_usertype)
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