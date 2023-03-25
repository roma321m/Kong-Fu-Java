package dorin_roman.app.kongfujava.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dorin_roman.app.kongfujava.navigation.main_destination.*
import dorin_roman.app.kongfujava.navigation.screens.GeneralScreens
import dorin_roman.app.kongfujava.navigation.screens.GeneralScreens.Companion.SPLASH_SCREEN


const val MAIN_EXIT_NAVIGATION_ANIMATION_TIME_MILLIS = 500
const val MAIN_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS = 300

@ExperimentalAnimationApi
@Composable
fun MainNavigation(
    navController: NavHostController
) {
    val screen = remember(navController) {
        GeneralScreens(navController = navController)
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
            navigateToSupervisorLoginScreen = screen.navigateToSupervisorLoginScreen
        )
        parentRegisterComposable(
            navigateToSupervisorLoginScreen = screen.navigateToSupervisorLoginScreen
        )
        supervisorLoginComposable(
            navigateToTeacherRegisterScreen = screen.navigateToTeacherRegisterScreen,
            navigateToParentRegisterScreen = screen.navigateToParentRegisterScreen
        )
        childLoginComposable()
    }
}