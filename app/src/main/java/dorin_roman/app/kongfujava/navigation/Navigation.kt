package dorin_roman.app.kongfujava.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dorin_roman.app.kongfujava.navigation.Screens.Companion.SPLASH_SCREEN
import dorin_roman.app.kongfujava.navigation.destination.*

@ExperimentalAnimationApi
@Composable
fun MainNavigation(
    navController: NavHostController
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable(
            navigateToUserTypeScreen = screen.navigateToUserTypeScreen
        )
        userTypeComposable(
            navigateToLoginScreen = screen.navigateToLoginScreen
        )
        teacherRegisterComposable(
            navigateToTeacherLoginScreen = screen.navigateToTeacherLoginScreen
        )
        parentRegisterComposable(
            navigateToParentLoginScreen = screen.navigateToParentLoginScreen
        )
        teacherLoginComposable(
            navigateToMainScreen = screen.navigateToMainScreen,
            navigateToTeacherRegisterScreen = screen.navigateToTeacherRegisterScreen
        )
        childLoginComposable(
            navigateToMainScreen = screen.navigateToMainScreen
        )
        parentLoginComposable(
            navigateToMainScreen = screen.navigateToMainScreen,
            navigateToParentRegisterScreen = screen.navigateToParentRegisterScreen
        )
        mainComposable()
    }
}