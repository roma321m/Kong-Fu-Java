package dorin_roman.app.kongfujava.navigation.main_destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.navigation.screens.GeneralScreens.Companion.TEACHER_REGISTER_SCREEN
import dorin_roman.app.kongfujava.ui.screens.register.teacher.TeacherRegisterScreen


@ExperimentalAnimationApi
fun NavGraphBuilder.teacherRegisterComposable(
    navigateToSupervisorLoginScreen: (usertype: UserType) -> Unit
) {
    composable(
        route = TEACHER_REGISTER_SCREEN
    ) {
        TeacherRegisterScreen(
            navigateToSupervisorLoginScreen = navigateToSupervisorLoginScreen
        )
    }
}