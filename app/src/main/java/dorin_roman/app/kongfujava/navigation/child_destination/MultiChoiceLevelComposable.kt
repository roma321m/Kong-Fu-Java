package dorin_roman.app.kongfujava.navigation.child_destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.CHILD_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
import dorin_roman.app.kongfujava.navigation.screens.ChildScreens.Companion.MULTI_CHOICE_LEVEL_SCREEN
import dorin_roman.app.kongfujava.ui.screens.level.multi_choice.MultiChoiceScreen


@ExperimentalAnimationApi
fun NavGraphBuilder.multiChoiceLevelComposable() {
    composable(
        route = MULTI_CHOICE_LEVEL_SCREEN,
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = CHILD_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
                ),
                initialOffsetX = { fullWidth -> -fullWidth }
            )
        }
    ) {
        MultiChoiceScreen()
    }
}