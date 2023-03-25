package dorin_roman.app.kongfujava.ui.screens.login.supervisor.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.ui.screens.login.supervisor.SupervisorLoginViewModel
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun SupervisorLoginScreenContent(
    navigateToTeacherRegisterScreen: () -> Unit,
    navigateToParentRegisterScreen: () -> Unit,
    viewModel: SupervisorLoginViewModel
) {
    Row(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxWidth()
    ) {
        Spacer(
            modifier = Modifier
                .weight(0.05f)
                .fillMaxHeight()
        )

        Column(
            modifier = Modifier
                .weight(0.35f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TeacherLoginScreenContentImage()

            TeacherLoginScreenContentTexts()
        }

        Spacer(
            modifier = Modifier
                .weight(0.05f)
                .fillMaxHeight()
        )

        Column(
            modifier = Modifier
                .weight(0.35f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TeacherLoginScreenContentTextButton(
                navigateToTeacherRegisterScreen = navigateToTeacherRegisterScreen,
                navigateToParentRegisterScreen = navigateToParentRegisterScreen,
                viewModel = viewModel
            )
        }

        Spacer(
            modifier = Modifier
                .weight(0.05f)
                .fillMaxWidth()
        )
    }
}