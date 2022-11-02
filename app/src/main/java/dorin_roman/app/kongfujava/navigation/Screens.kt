package dorin_roman.app.kongfujava.navigation

import androidx.navigation.NavController
import dorin_roman.app.kongfujava.util.UserType

class Screens(navController: NavController) {

    companion object {
        const val SPLASH_SCREEN = "splash"
        const val USER_TYPE_SCREEN = "user_type"
        const val CHILD_LOGIN_SCREEN = "child_login"
        const val TEACHER_LOGIN_SCREEN = "teacher_login"
        const val MAIN_SCREEN = "main"
    }

    val splash: () -> Unit = {
        navController.navigate(route = USER_TYPE_SCREEN) {
            popUpTo(SPLASH_SCREEN) {
                inclusive = true
            }
        }
    }

    val userType: (UserType) -> Unit = { userType ->
        when (userType) {
            UserType.Teacher -> navController.navigate(route = TEACHER_LOGIN_SCREEN)
            UserType.Child -> navController.navigate(route = CHILD_LOGIN_SCREEN)
        }
    }

    val child: () -> Unit = {
        navController.navigate(route = MAIN_SCREEN)
    }
}