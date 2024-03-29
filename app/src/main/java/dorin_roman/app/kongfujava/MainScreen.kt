package dorin_roman.app.kongfujava

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.navigation.ChildNavigation
import dorin_roman.app.kongfujava.navigation.MainNavigation
import dorin_roman.app.kongfujava.screens.supervisor.SupervisorScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    onDataLoaded: () -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    lateinit var navController: NavHostController
    val userType = mainViewModel.userType.collectAsState().value

    if (userType is RequestState.Success) {
        onDataLoaded() // Can use this to while loading user data as well.
        when (userType.data) {
            UserType.None -> {
                navController = rememberAnimatedNavController()
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
                    userType = UserType.Parent
                )
            }
            UserType.Teacher -> {
                SupervisorScreen(
                    userType = UserType.Teacher
                )
            }
        }
    }
}