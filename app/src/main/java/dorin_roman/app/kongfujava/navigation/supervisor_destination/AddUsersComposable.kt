package dorin_roman.app.kongfujava.navigation.supervisor_destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.SUPERVISOR_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
import dorin_roman.app.kongfujava.navigation.screens.SupervisorScreens.Companion.ADD_USERS_SCREEN
import dorin_roman.app.kongfujava.ui.screens.add_users.AddUsersScreen


@ExperimentalAnimationApi
fun NavGraphBuilder.addUsersComposable(
    navigateProgressReport: () -> Unit
) {
    composable(
        route = ADD_USERS_SCREEN,
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = SUPERVISOR_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
                ),
                initialOffsetX = { fullWidth -> -fullWidth }
            )
        }
    ) {
        AddUsersScreen(
            navigateProgressReport = navigateProgressReport
        )
    }
}