package dorin_roman.app.kongfujava.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dorin_roman.app.kongfujava.navigation.child_destination.dragDropLevelComposable
import dorin_roman.app.kongfujava.navigation.child_destination.levelsComposable
import dorin_roman.app.kongfujava.navigation.child_destination.multiChoiceLevelComposable
import dorin_roman.app.kongfujava.navigation.child_destination.tutorialLevelComposable
import dorin_roman.app.kongfujava.navigation.child_destination.worldsComposable
import dorin_roman.app.kongfujava.navigation.screens.ChildScreens
import dorin_roman.app.kongfujava.navigation.screens.ChildScreens.Companion.WORLDS_SCREEN

const val CHILD_ENTER_NAVIGATION_ANIMATION_TIME_MILLIS = 300

@ExperimentalAnimationApi
@Composable
fun ChildNavigation(
    navController: NavHostController
) {
    val screen = remember(navController) {
        ChildScreens(navController = navController)
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = WORLDS_SCREEN
    ) {
        worldsComposable(
            navigateToMapLevels = screen.navigateToMapLevelsScreen
        )
        levelsComposable(
            navigateToTutorialLevel = screen.navigateToTutorialLevelScreen,
            navigateToMultiChoiceLevel = screen.navigateToMultiChoiceLevelScreen,
            navigateToDragDropLevel = screen.navigateToDragDropLevelScreen,
            navigateToWorldMapFromMapLevelsScreen = screen.navigateToWorldMapFromMapLevelsScreen
        )
        multiChoiceLevelComposable(navigateToMapLevelsScreenFromLevel = screen.navigateToMapLevelsScreenFromLevelScreen)
        tutorialLevelComposable(navigateToMapLevelsScreenFromLevel = screen.navigateToMapLevelsScreenFromLevelScreen)
        dragDropLevelComposable(navigateToMapLevelsScreenFromLevel = screen.navigateToMapLevelsScreenFromLevelScreen)
    }
}