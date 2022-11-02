package dorin_roman.app.kongfujava.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dorin_roman.app.kongfujava.navigation.Screens.Companion.SPLASH_SCREEN
import dorin_roman.app.kongfujava.navigation.destination.childLoginComposable
import dorin_roman.app.kongfujava.navigation.destination.splashComposable
import dorin_roman.app.kongfujava.navigation.destination.teacherLoginComposable
import dorin_roman.app.kongfujava.navigation.destination.userTypeComposable

@ExperimentalAnimationApi
@Composable
fun SetupNavigation(
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
            navigateToUserTypeScreen = screen.splash
        )
        userTypeComposable(
            navigateToLoginScreen = screen.userType
        )
        teacherLoginComposable(
            navigateToMainScreen = screen.splash // fixme - change to main screen
        )
        childLoginComposable(
            navigateToMainScreen = screen.splash // fixme - change to main screen
        )
    }
}