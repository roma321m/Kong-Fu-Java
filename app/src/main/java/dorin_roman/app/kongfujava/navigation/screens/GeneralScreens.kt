package dorin_roman.app.kongfujava.navigation.screens

import androidx.navigation.NavController
import dorin_roman.app.kongfujava.data.models.UserType

class GeneralScreens(navController: NavController) {

    companion object {
        const val TEACHER_REGISTER_SCREEN = "teacher_register"
        const val PARENT_REGISTER_SCREEN = "parent_register"
        const val USER_TYPE_SCREEN = "user_type"
        const val CHILD_LOGIN_SCREEN = "child_login"
        const val SUPERVISOR_LOGIN_SCREEN = "supervisor_login/{user_type}"
    }

    val navigateToSupervisorLoginScreen: (UserType) -> Unit = { userType ->
        navController.navigate(route = "supervisor_login/${userType.ordinal}")
    }

    val navigateToTeacherRegisterScreen: () -> Unit = {
        navController.navigate(route = TEACHER_REGISTER_SCREEN)
    }

    val navigateToParentRegisterScreen: () -> Unit = {
        navController.navigate(route = PARENT_REGISTER_SCREEN)
    }

    val navigateToLoginScreen: (UserType) -> Unit = { userType ->
        when (userType) {
            UserType.Teacher -> navController.navigate(route = "supervisor_login/${userType.ordinal}")
            UserType.Child -> navController.navigate(route = CHILD_LOGIN_SCREEN)
            UserType.Parent -> navController.navigate(route = "supervisor_login/${userType.ordinal}")
            UserType.None -> {}
        }
    }

}