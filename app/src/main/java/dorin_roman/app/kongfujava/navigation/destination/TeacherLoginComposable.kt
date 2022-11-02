package dorin_roman.app.kongfujava.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.navigation.Screens
import dorin_roman.app.kongfujava.navigation.Destinations.ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
import dorin_roman.app.kongfujava.ui.screens.teacher_login.TeacherLoginScreen

@ExperimentalAnimationApi
fun NavGraphBuilder.teacherLoginComposable(
    navigateToMainScreen: () -> Unit
) {
    composable(
        route = Screens.TEACHER_LOGIN_SCREEN,
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
                ),
                initialOffsetX = { fullWidth -> -fullWidth }
            )
        }
    ) {
        TeacherLoginScreen(
            navigateToMainScreen = navigateToMainScreen
        )
    }
}