package dorin_roman.app.kongfujava.navigation.screens

import androidx.navigation.NavController

class ChildScreens(navController: NavController) {

    companion object {
        const val WORLDS_SCREEN = "worlds"
        const val LEVELS_SCREEN = "levels/{worlds}"
        const val TUTORIAL_LEVEL_SCREEN = "tutorial"
        const val MULTI_CHOICE_LEVEL_SCREEN = "multi_choice"
        const val DRAG_DROP_LEVEL_SCREEN = "drag_drop"
    }

    val navigateToMapLevelsScreen: (worldId: Int) -> Unit = {
        navController.navigate(route = "levels/$it")
    }

    val navigateToTutorialLevelScreen: () -> Unit = {
        navController.navigate(route = TUTORIAL_LEVEL_SCREEN)
    }

    val navigateToMultiChoiceLevelScreen: () -> Unit = {
        navController.navigate(route = MULTI_CHOICE_LEVEL_SCREEN)
    }

    val navigateToDragDropLevelScreen: () -> Unit = {
        navController.navigate(route = DRAG_DROP_LEVEL_SCREEN)
    }

}