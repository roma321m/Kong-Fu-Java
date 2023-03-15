package dorin_roman.app.kongfujava.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dorin_roman.app.kongfujava.navigation.screens.SupervisorScreens
import dorin_roman.app.kongfujava.navigation.screens.SupervisorScreens.Companion.PROGRESS_REPORT_SCREEN
import dorin_roman.app.kongfujava.navigation.supervisor_destination.addUsersComposable
import dorin_roman.app.kongfujava.navigation.supervisor_destination.progressReportComposable


const val SUPERVISOR_EXIT_NAVIGATION_ANIMATION_TIME_MILLIS = 500
const val SUPERVISOR_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS = 300

@ExperimentalAnimationApi
@Composable
fun SupervisorNavigation(
    navController: NavHostController
) {
    val screen = remember(navController) {
        SupervisorScreens(navController = navController)
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = PROGRESS_REPORT_SCREEN
    ) {
        progressReportComposable(
            navigateToAddUsers = screen.navigateToAddUsersScreen
        )
        addUsersComposable(
            navigateProgressReport = screen.navigateToProgressReportScreen
        )
    }
}