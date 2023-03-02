package dorin_roman.app.kongfujava.navigation

import androidx.navigation.NavController
import dorin_roman.app.kongfujava.util.UserType

class Screens(navController: NavController) {

    companion object {
        const val SPLASH_SCREEN = "splash"
        const val TEACHER_REGISTER_SCREEN = "teacher_register"
        const val PARENT_REGISTER_SCREEN = "parent_register"
        const val USER_TYPE_SCREEN = "user_type"
        const val CHILD_LOGIN_SCREEN = "child_login"
        const val TEACHER_LOGIN_SCREEN = "teacher_login"
        const val PARENT_LOGIN_SCREEN = "parent_login"
        const val MAIN_SCREEN = "main"
    }

    val navigateToUserTypeScreen: () -> Unit = {
        navController.navigate(route = USER_TYPE_SCREEN) {
            popUpTo(SPLASH_SCREEN) {
                inclusive = true
            }
        }
    }

    val navigateToTeacherLoginScreen: () -> Unit = {
        navController.navigate(route = TEACHER_LOGIN_SCREEN)
    }

    val navigateToTeacherRegisterScreen: () -> Unit = {
        navController.navigate(route = TEACHER_REGISTER_SCREEN)
    }

    val navigateToParentLoginScreen: () -> Unit = {
        navController.navigate(route = PARENT_LOGIN_SCREEN)
    }

    val navigateToParentRegisterScreen: () -> Unit = {
        navController.navigate(route = PARENT_REGISTER_SCREEN)
    }

    val navigateToLoginScreen: (UserType) -> Unit = { userType ->
        when (userType) {
            UserType.Teacher -> navController.navigate(route = TEACHER_LOGIN_SCREEN)
            UserType.Child -> navController.navigate(route = CHILD_LOGIN_SCREEN)
            UserType.Parent -> navController.navigate(route = PARENT_LOGIN_SCREEN)
        }
    }

    val navigateToMainScreen: () -> Unit = {
        navController.navigate(route = MAIN_SCREEN)
    }
}