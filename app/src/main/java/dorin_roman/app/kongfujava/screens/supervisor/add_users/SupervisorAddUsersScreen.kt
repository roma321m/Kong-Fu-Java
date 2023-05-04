package dorin_roman.app.kongfujava.screens.supervisor.add_users

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.screens.supervisor.add_users.components.SupervisorAddUsersCreateCode
import dorin_roman.app.kongfujava.screens.supervisor.add_users.components.SupervisorAddUsersImage
import dorin_roman.app.kongfujava.screens.supervisor.add_users.components.SupervisorAddUsersInformation
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.layout.CustomLayout2
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@Composable
fun SupervisorAddUsersScreen(
    modifier: Modifier = Modifier,
    viewModel: SupervisorAddUsersViewModel = hiltViewModel()
) {
    CustomLayout2(
        modifier = modifier,
        startTopContent = {
            SupervisorAddUsersInformation(
                modifier = Modifier.fillMaxSize(),
            )
        },
        startBottomContent = {
            SupervisorAddUsersCreateCode(
                modifier = Modifier.fillMaxSize(),
                hasActiveCode = viewModel.hasActiveCode,
                minutes = viewModel.minutes,
                seconds = viewModel.seconds,
                code = viewModel.code,
                onCreateCodeClicked = {
                    viewModel.handle(SupervisorAddUsersEvent.CreateCodeClicked)
                }
            )
        },
        endContent = {
            SupervisorAddUsersImage(
                modifier = Modifier.fillMaxSize()
            )
        }
    )
}

@DevicePreviews
@Composable
fun SupervisorAddUsersScreenPreview() {
    KongFuJavaTheme {
        SupervisorAddUsersScreen()
    }
}