package dorin_roman.app.kongfujava.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dorin_roman.app.kongfujava.navigation.main_destination.*
import dorin_roman.app.kongfujava.navigation.screens.GeneralScreens
import dorin_roman.app.kongfujava.navigation.screens.GeneralScreens.Companion.SPLASH_SCREEN
import dorin_roman.app.kongfujava.view_models.ChildLoginContentViewModel
import dorin_roman.app.kongfujava.view_models.MainViewModel

const val MAIN_EXIT_NAVIGATION_ANIMATION_TIME_MILLIS = 500
const val MAIN_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS = 300

@ExperimentalAnimationApi
@Composable
fun MainNavigation(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    childLoginContentViewModel: ChildLoginContentViewModel
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
            navigateToTeacherLoginScreen = screen.navigateToTeacherLoginScreen
        )
        parentRegisterComposable(
            navigateToParentLoginScreen = screen.navigateToParentLoginScreen
        )
        teacherLoginComposable(
            navigateToMainScreen = screen.navigateToMainScreen,
            navigateToTeacherRegisterScreen = screen.navigateToTeacherRegisterScreen,
            mainViewModel = mainViewModel
        )
        childLoginComposable(
            navigateToMainScreen = screen.navigateToMainScreen,
            mainViewModel = mainViewModel,
            childLoginContentViewModel = childLoginContentViewModel
        )
        parentLoginComposable(
            navigateToMainScreen = screen.navigateToMainScreen,
            navigateToParentRegisterScreen = screen.navigateToParentRegisterScreen,
            mainViewModel = mainViewModel
        )
        mainComposable(
            mainViewModel = mainViewModel
        )
    }
}