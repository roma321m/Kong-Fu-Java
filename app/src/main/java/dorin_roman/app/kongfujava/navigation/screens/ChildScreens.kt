package dorin_roman.app.kongfujava.navigation.screens

import android.util.Log
import androidx.navigation.NavController

class ChildScreens(navController: NavController) {

    companion object {
        const val WORLDS_SCREEN = "worlds"
        const val LEVELS_SCREEN = "levels/{worlds}"
        const val LEVELS_KEY1_SCREEN = "level"
        const val LEVELS_KEY2_SCREEN = "number"
        const val TUTORIAL_LEVEL_SCREEN = "tutorial/{level}/{number}/{worlds}"
        const val MULTI_CHOICE_LEVEL_SCREEN = "multi_choice/{level}/{number}/{worlds}"
        const val DRAG_DROP_LEVEL_SCREEN = "drag_drop/{level}/{number}/{worlds}"
    }

    val navigateToMapLevelsScreen: (worldId: Int) -> Unit = {
        navController.navigate(route = "levels/$it")
    }

    val navigateToMapLevelsScreenFromLevel: (worldId: Int) -> Unit = {
        navController.navigate(route = "levels/$it") {
            popUpTo(WORLDS_SCREEN) {
                inclusive = true
            }
        }
    }

    val navigateToTutorialLevelScreen: (levelId: Int, levelNumber: Int, worldId: Int) -> Unit = { levelId, levelNumber, worldId ->
        navController.navigate(route = "tutorial/$levelId/$levelNumber/$worldId")
    }

    val navigateToMultiChoiceLevelScreen: (levelId: Int, levelNumber: Int, worldId: Int) -> Unit = { levelId, levelNumber, worldId ->
        navController.navigate(route = "multi_choice/$levelId/$levelNumber/$worldId")
    }

    val navigateToDragDropLevelScreen: (levelId: Int, levelNumber: Int, worldId: Int) -> Unit = { levelId, levelNumber, worldId ->
        navController.navigate(route = "drag_drop/$levelId/$levelNumber/$worldId")
    }

}