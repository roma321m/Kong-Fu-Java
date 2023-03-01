package dorin_roman.app.kongfujava.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.Screens
import dorin_roman.app.kongfujava.ui.screens.register.RegisterScreen


@ExperimentalAnimationApi
fun NavGraphBuilder.registerComposable(
    navigateToUserTypeScreen: () -> Unit
) {
    composable(
        route = Screens.REGISTER_SCREEN
    ) {
        RegisterScreen(
            navigateToUserTypeScreen = navigateToUserTypeScreen
        )
    }
}