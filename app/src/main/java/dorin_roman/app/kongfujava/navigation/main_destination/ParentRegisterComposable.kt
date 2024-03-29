package dorin_roman.app.kongfujava.navigation.main_destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.screens.GeneralScreens.Companion.PARENT_REGISTER_SCREEN
import dorin_roman.app.kongfujava.screens.register.parent.ParentRegisterScreen


@ExperimentalAnimationApi
fun NavGraphBuilder.parentRegisterComposable() {
    composable(
        route = PARENT_REGISTER_SCREEN
    ) {
        ParentRegisterScreen()
    }
}