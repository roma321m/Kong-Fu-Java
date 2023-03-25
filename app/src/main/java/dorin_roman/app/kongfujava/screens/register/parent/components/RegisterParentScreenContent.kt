package dorin_roman.app.kongfujava.screens.register.parent.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.screens.register.parent.ParentRegisterViewModel
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun RegisterParentScreenContent(
    viewModel: ParentRegisterViewModel,
    navigateToSupervisorLoginScreen: (userType: UserType) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxHeight()
    ) {
        Spacer(Modifier.weight(0.05f))

        Box(modifier = Modifier.weight(0.2f)) {
            RegisterParentScreenContentImage()
        }

        Column(
            modifier = Modifier
                .weight(0.35f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RegisterParentScreenContentTexts()
        }

        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RegisterParentScreenContentTextFields(
                viewModel = viewModel
            )
        }
    }
}