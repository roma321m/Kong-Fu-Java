package dorin_roman.app.kongfujava.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.Destinations
import dorin_roman.app.kongfujava.navigation.Screens
import dorin_roman.app.kongfujava.ui.screens.main.MainScreen

@ExperimentalAnimationApi
fun NavGraphBuilder.mainComposable(
    navigateToTemp1: () -> Unit,
    navigateToTemp2: () -> Unit
) {
    composable(
        route = Screens.MAIN_SCREEN,
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = Destinations.ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
                ),
                initialOffsetX = { fullWidth -> -fullWidth }
            )
        }
    ) {
        MainScreen(
            navigateToTemp1 = navigateToTemp1,
            navigateToTemp2 = navigateToTemp2
        )
    }
}