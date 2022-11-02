package dorin_roman.app.kongfujava.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.Screens.Companion.SPLASH_SCREEN
import dorin_roman.app.kongfujava.navigation.Destinations.EXIT_NAVIGATION_ANIMATION_TIME_MILLIS
import dorin_roman.app.kongfujava.ui.screens.splash.SplashScreen

@ExperimentalAnimationApi
fun NavGraphBuilder.splashComposable(
    navigateToUserTypeScreen: () -> Unit
) {
    composable(
        route = SPLASH_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(EXIT_NAVIGATION_ANIMATION_TIME_MILLIS)
            )
        }
    ) {
        SplashScreen(
            navigateToUserTypeScreen = navigateToUserTypeScreen
        )
    }
}