package dorin_roman.app.kongfujava.screens.register.parent.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.EmailField
import dorin_roman.app.kongfujava.ui.components.PasswordField
import dorin_roman.app.kongfujava.ui.theme.spacing


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ParentRegisterTextFields(
    verifyStep: Boolean,
    showLoading: Boolean,
    onReloadClicked: () -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onRegisterClicked: () -> Unit,
) {
    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (verifyStep) {
            Text(text = stringResource(R.string.register_verification_email))

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(MaterialTheme.spacing.medium),
                onClick = {
                    keyboard?.hide()
                    onReloadClicked()
                }
            ) {
                Text(text = stringResource(R.string.register_already_verified))
            }
        } else {
            EmailField(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(MaterialTheme.spacing.medium),
                email = email,
                onEmailValueChange = {
                    onEmailChange(it)
                }
            )

            PasswordField(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(MaterialTheme.spacing.medium),
                password = password,
                onPasswordValueChange = {
                    onPasswordChange(it)
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(MaterialTheme.spacing.medium),
                onClick = {
                    keyboard?.hide()
                    onRegisterClicked()
                }
            ) {
                Text(text = stringResource(id = R.string.register_title))
            }
        }

        if (showLoading) {
            CircularProgressIndicator()
        }
    }
}