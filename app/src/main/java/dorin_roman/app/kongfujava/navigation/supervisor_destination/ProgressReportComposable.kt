package dorin_roman.app.kongfujava.navigation.supervisor_destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.SUPERVISOR_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
import dorin_roman.app.kongfujava.navigation.screens.SupervisorScreens.Companion.PROGRESS_REPORT_SCREEN
import dorin_roman.app.kongfujava.screens.progress_report.ProgressReportScreen


@ExperimentalAnimationApi
fun NavGraphBuilder.progressReportComposable(
    navigateToAddUsers: () -> Unit
) {
    composable(
        route = PROGRESS_REPORT_SCREEN,
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = SUPERVISOR_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
                ),
                initialOffsetX = { fullWidth -> -fullWidth }
            )
        }
    ) {
        ProgressReportScreen(
            navigateToAddUsers = navigateToAddUsers
        )
    }
}