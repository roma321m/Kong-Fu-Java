package dorin_roman.app.kongfujava.navigation.child_destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.CHILD_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
import dorin_roman.app.kongfujava.navigation.screens.ChildScreens.Companion.LEVELS_SCREEN
import dorin_roman.app.kongfujava.navigation.screens.ChildScreens.Companion.WORLDS_SCREEN
import dorin_roman.app.kongfujava.screens.level.levels_map.LevelsMapScreen


@ExperimentalAnimationApi
fun NavGraphBuilder.levelsComposable(
    navigateToTutorialLevel: () -> Unit,
    navigateToMultiChoiceLevel: () -> Unit,
    navigateToDragDropLevel: () -> Unit,
) {
    composable(
        route = LEVELS_SCREEN,
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = CHILD_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
                ),
                initialOffsetX = { fullWidth -> -fullWidth }
            )
        },
        arguments = listOf(
            navArgument(WORLDS_SCREEN) {
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->
        val worldId = navBackStackEntry.arguments?.getInt(WORLDS_SCREEN) ?: 0
        LevelsMapScreen(
            worldId = worldId,
            navigateToTutorialLevel = navigateToTutorialLevel,
            navigateToMultiChoiceLevel = navigateToMultiChoiceLevel,
            navigateToDragDropLevel = navigateToDragDropLevel
        )
    }
}