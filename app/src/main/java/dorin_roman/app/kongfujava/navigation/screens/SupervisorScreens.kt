package dorin_roman.app.kongfujava.navigation.screens

import androidx.navigation.NavController

class SupervisorScreens(navController: NavController) {

    companion object {
        const val ADD_USERS_SCREEN = "add_users"
        const val PROGRESS_REPORT_SCREEN = "progress_report"
    }

    val navigateToAddUsersScreen: () -> Unit = {
        navController.navigate(route = ADD_USERS_SCREEN)
    }

    val navigateToProgressReportScreen: () -> Unit = {
        navController.navigate(route = PROGRESS_REPORT_SCREEN)
    }
}