package dorin_roman.app.kongfujava.navigation.main_destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.screens.GeneralScreens.Companion.USER_TYPE_SCREEN
import dorin_roman.app.kongfujava.ui.screens.login.user_type.UserTypeScreen
import dorin_roman.app.kongfujava.util.UserType


@ExperimentalAnimationApi
fun NavGraphBuilder.userTypeComposable(
    navigateToLoginScreen: (UserType) -> Unit
) {
    composable(
        route = USER_TYPE_SCREEN
    ) {
        UserTypeScreen(
            navigateToLoginScreen = navigateToLoginScreen
        )
    }
}