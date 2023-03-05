package dorin_roman.app.kongfujava.navigation.child_destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.CHILD_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
import dorin_roman.app.kongfujava.navigation.Screens
import dorin_roman.app.kongfujava.ui.screens.worlds.WorldsScreen


@ExperimentalAnimationApi
fun NavGraphBuilder.worldsComposable(
    navigateToLevel: () -> Unit
) {
    composable(
        route = Screens.WORLDS_SCREEN,
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = CHILD_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
                ),
                initialOffsetX = { fullWidth -> -fullWidth }
            )
        }
    ) {
        WorldsScreen(
            navigateToLevel = navigateToLevel
        )
    }
}