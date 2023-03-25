package dorin_roman.app.kongfujava.ui.screens.login.supervisor.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.ui.components.EmailField
import dorin_roman.app.kongfujava.ui.components.PasswordField
import dorin_roman.app.kongfujava.ui.screens.login.supervisor.SupervisorLoginEvent
import dorin_roman.app.kongfujava.ui.screens.login.supervisor.SupervisorLoginViewModel
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun TeacherLoginScreenContentTextButton(
    navigateToTeacherRegisterScreen: () -> Unit,
    navigateToParentRegisterScreen: () -> Unit,
    viewModel: SupervisorLoginViewModel
) {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.login).uppercase(),
        style = MaterialTheme.typography.h4
    )

    EmailField(
        email = viewModel.email,
        onEmailValueChange = {
            viewModel.handle(SupervisorLoginEvent.UpdateEmailText(it))
        }
    )

    PasswordField(
        password = viewModel.password,
        onPasswordValueChange = {
            viewModel.handle(SupervisorLoginEvent.UpdatePasswordText(it))
        }
    )

    Button(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(top = MaterialTheme.spacing.medium),
        onClick = {
            viewModel.handle(SupervisorLoginEvent.Login)
        }
    ) {
        Text(text = stringResource(id = R.string.login).uppercase())
    }

    ClickableText(
        text = AnnotatedString(stringResource(id = R.string.login_create_account)),
        style = TextStyle(
            color = Color.Blue,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .fillMaxWidth(0.6f),
        onClick = {
            if (viewModel.userType == UserType.Teacher) {
                navigateToTeacherRegisterScreen()
            } else if (viewModel.userType == UserType.Parent) {
                navigateToParentRegisterScreen()
            }
        }
    )

    if (viewModel.showLoading) {
        CircularProgressIndicator()
    }

}