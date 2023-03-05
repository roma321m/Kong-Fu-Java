package dorin_roman.app.kongfujava

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dorin_roman.app.kongfujava.navigation.MainNavigation
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import dorin_roman.app.kongfujava.ui.theme.systemUi
import dorin_roman.app.kongfujava.view_models.MainViewModel

@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KongFuJavaTheme {

                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(
                    color = MaterialTheme.colors.systemUi,
                    darkIcons = isSystemInDarkTheme().not()
                )

                navController = rememberAnimatedNavController()
                MainNavigation(
                    navController = navController,
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}