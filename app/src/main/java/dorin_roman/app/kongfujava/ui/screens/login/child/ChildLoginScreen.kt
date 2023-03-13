package dorin_roman.app.kongfujava.ui.screens.login.child


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.HorizontalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.screens.login.child.content.ChildLoginScreenContent
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import dorin_roman.app.kongfujava.view_models.ChildLoginContentViewModel
import dorin_roman.app.kongfujava.view_models.MainEvent
import dorin_roman.app.kongfujava.view_models.MainViewModel

@Composable
fun ChildLoginScreen(
    navigateToMainScreen: () -> Unit,
    mainViewModel: MainViewModel,
    childLoginContentViewModel: ChildLoginContentViewModel
) {
    HorizontalFortySixtyLayout(
        fortyLayout = {
            ChildLoginScreenContent(
                navigateToMainScreen = navigateToMainScreen,
                onLoginClicked = {
                    mainViewModel.handle(MainEvent.Child) // FIXME - temp, handle login instead of this
                },
                childLoginContentViewModel =  childLoginContentViewModel
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
        ChildLoginScreen({}, viewModel(), viewModel())
    }
}