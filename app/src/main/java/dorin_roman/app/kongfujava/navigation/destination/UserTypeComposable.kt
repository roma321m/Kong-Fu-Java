package dorin_roman.app.kongfujava.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.Screens
import dorin_roman.app.kongfujava.ui.screens.login.main.UserTypeScreen
import dorin_roman.app.kongfujava.util.UserType


@ExperimentalAnimationApi
fun NavGraphBuilder.userTypeComposable(
    navigateToLoginScreen: (UserType) -> Unit
) {
    composable(
        route = Screens.USER_TYPE_SCREEN
    ) {
        UserTypeScreen(
            navigateToLoginScreen = navigateToLoginScreen
        )
    }
}