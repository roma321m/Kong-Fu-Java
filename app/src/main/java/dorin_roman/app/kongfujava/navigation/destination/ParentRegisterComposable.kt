package dorin_roman.app.kongfujava.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.Screens
import dorin_roman.app.kongfujava.ui.screens.register.parent.ParentRegisterScreen


@ExperimentalAnimationApi
fun NavGraphBuilder.parentRegisterComposable(
    navigateToParentLoginScreen: () -> Unit
) {
    composable(
        route = Screens.PARENT_REGISTER_SCREEN
    ) {
        ParentRegisterScreen(
            navigateToParentLoginScreen = navigateToParentLoginScreen
        )
    }
}