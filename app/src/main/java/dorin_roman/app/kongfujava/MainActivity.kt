package dorin_roman.app.kongfujava

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import dorin_roman.app.kongfujava.navigation.MainNavigation
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KongFuJavaTheme {
                navController = rememberAnimatedNavController()
                MainNavigation(
                    navController = navController
                )
            }
        }
    }
}