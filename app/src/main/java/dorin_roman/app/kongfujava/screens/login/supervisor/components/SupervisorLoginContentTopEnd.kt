package dorin_roman.app.kongfujava.screens.login.supervisor.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.text_fields.EmailTextField
import dorin_roman.app.kongfujava.ui.components.text_fields.PasswordTextField
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun SupervisorLoginContentTopEnd(
    showLoading: Boolean,
    email: String,
    onEmailChange: (newEmail: String) -> Unit,
    password: String,
    onPasswordChange: (newPassword: String) -> Unit,
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.supervisor_login_title),
            style = MaterialTheme.typography.h4
        )

        EmailTextField(
            modifier = Modifier
                .fillMaxWidth(0.5f),
            email = email,
            onEmailValueChange = {
                onEmailChange(it)
            }
        )

        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth(0.5f),
            password = password,
            onPasswordValueChange = {
                onPasswordChange(it)
            }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(top = MaterialTheme.spacing.medium),
            onClick = {
                onLoginClicked()
            }
        ) {
            Text(text = stringResource(id = R.string.supervisor_login_title))
        }

        ClickableText(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth(0.5f),
            text = AnnotatedString(stringResource(id = R.string.supervisor_login_create_account)),
            style = TextStyle(
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.End
            ),
            onClick = {
                onRegisterClicked()
            }
        )

        if (showLoading) {
            CircularProgressIndicator()
        }
    }

}