package dorin_roman.app.kongfujava.navigation.screens

import androidx.navigation.NavController
import dorin_roman.app.kongfujava.util.UserType

class GeneralScreens(navController: NavController) {

    companion object {
        const val SPLASH_SCREEN = "splash"
        const val TEACHER_REGISTER_SCREEN = "teacher_register"
        const val PARENT_REGISTER_SCREEN = "parent_register"
        const val USER_TYPE_SCREEN = "user_type"
        const val CHILD_LOGIN_SCREEN = "child_login"
        const val TEACHER_PARENT_LOGIN_SCREEN = "teacher_parent_login/{user_type}"
        const val MAIN_SCREEN = "main"
    }

    val navigateToUserTypeScreen: () -> Unit = {
        navController.navigate(route = USER_TYPE_SCREEN) {
            popUpTo(SPLASH_SCREEN) {
                inclusive = true
            }
        }
    }

    val navigateToTeacherParentLoginScreen: () -> Unit = {
        navController.navigate(route = TEACHER_PARENT_LOGIN_SCREEN)
    }

    val navigateToTeacherRegisterScreen: () -> Unit = {
        navController.navigate(route = TEACHER_REGISTER_SCREEN)
    }

    val navigateToParentRegisterScreen: () -> Unit = {
        navController.navigate(route = PARENT_REGISTER_SCREEN)
    }

    val navigateToLoginScreen: (UserType) -> Unit = { userType ->
        when (userType) {
            UserType.Teacher -> navController.navigate(route = "teacher_parent_login/${userType.ordinal}")
            UserType.Child -> navController.navigate(route = CHILD_LOGIN_SCREEN)
            UserType.Parent -> navController.navigate(route = "teacher_parent_login/${userType.ordinal}")
        }
    }

    val navigateToMainScreen: () -> Unit = {
        navController.navigate(route = MAIN_SCREEN) {
            popUpTo(USER_TYPE_SCREEN) {
                inclusive = true
            }
        }
    }

}