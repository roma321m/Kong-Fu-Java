package dorin_roman.app.kongfujava.screens.login.child

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.login.child.components.ChildLoginContentTopEnd
import dorin_roman.app.kongfujava.screens.login.child.components.ChildLoginTopLeft
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.CustomLayoutOne
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun ChildLoginScreen(
    viewModel: ChildLoginViewModel = hiltViewModel(),
) {
    CustomLayoutOne(
        topStartLayout = {
            ChildLoginTopLeft()
        },
        topEndLayout = {
            ChildLoginContentTopEnd(
                showLoading = {
                    viewModel.showLoading
                },
                codeVisibility = {
                    viewModel.stepState == ChildLoginStepState.CODE
                },
                nameVisibility = {
                    viewModel.stepState == ChildLoginStepState.NAME
                },
                ageVisibility = {
                    viewModel.stepState == ChildLoginStepState.AGE
                },
                textCode = viewModel.studentCode,
                name = viewModel.studentName,
                age = viewModel.studentAge,
                onTextCodeChange = { newTextCode ->
                    viewModel.handle(ChildLoginEvent.OnCodeChange(newTextCode))
                },
                onNameChange = { newName ->
                    viewModel.handle(ChildLoginEvent.OnNameChange(newName))
                },
                onAgeChange = { newAge ->
                    viewModel.handle(ChildLoginEvent.OnAgeChange(newAge))
                },
                onNextClicked = { step ->
                    viewModel.handle(ChildLoginEvent.OnNextClick(step))
                }
            )
        },
        bottomLayout = {
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