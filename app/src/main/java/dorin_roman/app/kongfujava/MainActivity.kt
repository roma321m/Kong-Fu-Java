package dorin_roman.app.kongfujava

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.navigation.ChildNavigation
import dorin_roman.app.kongfujava.navigation.MainNavigation
import dorin_roman.app.kongfujava.screens.supervisor.SupervisorScreen
import dorin_roman.app.kongfujava.ui.components.SystemUi
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KongFuJavaTheme {
                SystemUi()

                val userType = mainViewModel.userType.collectAsState().value
                if (userType is RequestState.Success) {
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
                        UserType.Parent, UserType.Teacher -> {
                            SupervisorScreen()
                        }
                    }
                }
            }
        }
    }
}