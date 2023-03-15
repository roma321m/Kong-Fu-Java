package dorin_roman.app.kongfujava.ui.screens.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dorin_roman.app.kongfujava.navigation.ChildNavigation
import dorin_roman.app.kongfujava.navigation.SupervisorNavigation
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import dorin_roman.app.kongfujava.util.UserType
import dorin_roman.app.kongfujava.view_models.MainViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel
) {
    val navController: NavHostController = rememberAnimatedNavController()

    Column {
        TempUi(mainViewModel = mainViewModel) // Fixme - temp
        Spacer(modifier = Modifier.padding(10.dp))
        when (mainViewModel.type) {
            UserType.Parent, UserType.Teacher -> {
                SupervisorNavigation(
                    navController = navController
                )
            }
            UserType.Child -> {
                ChildNavigation(
                    navController = navController
                )
            }
        }
    }

}

@Composable
fun TempUi(mainViewModel: MainViewModel) {
    val text = when (mainViewModel.type) {
        UserType.Parent -> "  (Parent)"
        UserType.Teacher -> "  (Teacher)"
        UserType.Child -> "  (Child)"
    }
    Text(text = "This is the main screen $text")
}

@DevicePreviews
@Composable
fun MainScreenPreview() {
    KongFuJavaTheme {
        MainScreen(viewModel())
    }
}