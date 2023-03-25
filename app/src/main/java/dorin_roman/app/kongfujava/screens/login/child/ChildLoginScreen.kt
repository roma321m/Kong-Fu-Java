package dorin_roman.app.kongfujava.screens.login.child


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.MainEvent
import dorin_roman.app.kongfujava.MainViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.login.child.content.ChildLoginScreenContent
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.HorizontalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun ChildLoginScreen(
    childLoginViewModel: ChildLoginViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    HorizontalFortySixtyLayout(
        fortyLayout = {
            ChildLoginScreenContent(
                onLoginClicked = {
                    mainViewModel.handle(MainEvent.Child) // Fixme -do login then change this value
                },
                childLoginViewModel = childLoginViewModel
            )
        },
        sixtyLayout = {
            SideScreenImage(R.drawable.ic_panda_login)
        }
    )
}

@DevicePreviews
@Composable
fun ChildLoginScreenPreview() {
    KongFuJavaTheme {
        ChildLoginScreen()
    }
}