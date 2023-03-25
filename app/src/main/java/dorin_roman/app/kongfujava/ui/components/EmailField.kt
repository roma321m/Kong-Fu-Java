package dorin_roman.app.kongfujava.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import dorin_roman.app.kongfujava.R

@Composable
fun EmailField(
    modifier: Modifier = Modifier,
    email: String,
    onEmailValueChange: (newValue: String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = email,
        onValueChange = { newValue ->
            onEmailValueChange(newValue)
        },
        label = { Text(text = stringResource(id = R.string.register_email)) },
        placeholder = { Text(text = stringResource(id = R.string.register_enter_email)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        )
    )
}

@Preview
@Composable
fun PreviewEmailField() {
    EmailField(
        email = "test@gmail.com",
        onEmailValueChange = {}
    )
}