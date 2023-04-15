package dorin_roman.app.kongfujava

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.navigation.ChildNavigation
import dorin_roman.app.kongfujava.navigation.MainNavigation
import dorin_roman.app.kongfujava.screens.supervisor.SupervisorScreen
import dorin_roman.app.kongfujava.service.CodeService

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    codeService: CodeService
) {
    lateinit var navController: NavHostController
    val userType = mainViewModel.userType.collectAsState().value

    if (userType is RequestState.Success) {
        when (userType.data) {
            UserType.None -> {
                navController = rememberAnimatedNavController()
                // FIXME - remove splash screen from main nav
                MainNavigation(
                    navController = navController
                )
            }
            UserType.Child -> {
                navController = rememberAnimatedNavController()
                ChildNavigation(
                    navController = navController
                )
            }
            UserType.Parent -> {
                SupervisorScreen(
                    userType = UserType.Parent,
                    codeService = codeService
                )
            }
            UserType.Teacher -> {
                SupervisorScreen(
                    userType = UserType.Teacher,
                    codeService = codeService
                )
            }
        }
    }
}