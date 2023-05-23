package dorin_roman.app.kongfujava.navigation.child_destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.CHILD_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
import dorin_roman.app.kongfujava.navigation.screens.ChildScreens
import dorin_roman.app.kongfujava.navigation.screens.ChildScreens.Companion.MULTI_CHOICE_LEVEL_SCREEN
import dorin_roman.app.kongfujava.screens.level.multi_choice.MultiChoiceScreen


@ExperimentalAnimationApi
fun NavGraphBuilder.multiChoiceLevelComposable(navigateToMapLevelsScreenFromLevel: (worldId: Int) -> Unit) {
    composable(
        route = MULTI_CHOICE_LEVEL_SCREEN,
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = CHILD_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
                ),
                initialOffsetX = { fullWidth -> -fullWidth }
            )
        },
        arguments = listOf(
            navArgument(ChildScreens.LEVELS_KEY1_SCREEN) {
                type = NavType.IntType
            },
            navArgument(ChildScreens.LEVELS_KEY2_SCREEN) {
                type = NavType.IntType
            },
            navArgument(ChildScreens.WORLDS_SCREEN) {
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->
        val levelId = navBackStackEntry.arguments?.getInt(ChildScreens.LEVELS_KEY1_SCREEN) ?: -1
        val levelNumber = navBackStackEntry.arguments?.getInt(ChildScreens.LEVELS_KEY2_SCREEN) ?: -1
        val worldId = navBackStackEntry.arguments?.getInt(ChildScreens.WORLDS_SCREEN) ?: -1
        MultiChoiceScreen(navigateToMapLevelsScreenFromLevel = navigateToMapLevelsScreenFromLevel, levelId = levelId, levelNumber = levelNumber, worldId = worldId)
    }
}