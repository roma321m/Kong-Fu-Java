package dorin_roman.app.kongfujava.navigation.main_destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.MAIN_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
import dorin_roman.app.kongfujava.navigation.screens.GeneralScreens.Companion.PARENT_LOGIN_SCREEN
import dorin_roman.app.kongfujava.ui.screens.login.parent.ParentLoginScreen
import dorin_roman.app.kongfujava.view_models.MainViewModel

@ExperimentalAnimationApi
fun NavGraphBuilder.parentLoginComposable(
    navigateToMainScreen: () -> Unit,
    navigateToParentRegisterScreen: () -> Unit,
    mainViewModel: MainViewModel
) {
    composable(
        route = PARENT_LOGIN_SCREEN,
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = MAIN_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
                ),
                initialOffsetX = { fullWidth -> -fullWidth }
            )
        }
    ) {
        ParentLoginScreen(
            navigateToMainScreen = navigateToMainScreen,
            navigateToParentRegisterScreen = navigateToParentRegisterScreen,
            mainViewModel = mainViewModel
        )
    }
}