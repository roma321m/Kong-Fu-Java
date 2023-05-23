package dorin_roman.app.kongfujava.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dorin_roman.app.kongfujava.navigation.main_destination.childLoginComposable
import dorin_roman.app.kongfujava.navigation.main_destination.parentRegisterComposable
import dorin_roman.app.kongfujava.navigation.main_destination.supervisorLoginComposable
import dorin_roman.app.kongfujava.navigation.main_destination.teacherRegisterComposable
import dorin_roman.app.kongfujava.navigation.main_destination.userTypeComposable
import dorin_roman.app.kongfujava.navigation.screens.GeneralScreens
import dorin_roman.app.kongfujava.navigation.screens.GeneralScreens.Companion.USER_TYPE_SCREEN


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
        startDestination = USER_TYPE_SCREEN
    ) {
        userTypeComposable(
            navigateToLoginScreen = screen.navigateToLoginScreen
        )
        teacherRegisterComposable()
        parentRegisterComposable()
        supervisorLoginComposable(
            navigateToTeacherRegisterScreen = screen.navigateToTeacherRegisterScreen,
            navigateToParentRegisterScreen = screen.navigateToParentRegisterScreen
        )
        childLoginComposable()
    }
}