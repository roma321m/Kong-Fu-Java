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
        registerComposable(
            navigateToUserTypeScreen = screen.register
        )
        teacherLoginComposable(
            navigateToMainScreen = screen.teacher
        )
        childLoginComposable(
            navigateToMainScreen = screen.child
        )
        parentLoginComposable(
            navigateToMainScreen = screen.parent
        )
        mainComposable(
            navigateToTemp1 = screen.splash, // fixme - change
            navigateToTemp2 = screen.splash // fixme - change
        )
    }
}