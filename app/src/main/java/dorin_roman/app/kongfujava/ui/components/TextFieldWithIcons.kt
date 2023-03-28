package dorin_roman.app.kongfujava.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

@Composable
fun TextFieldWithIcons(
    modifier: Modifier = Modifier,
    label: Int,
    placeholder: Int,
    icon: ImageVector,
    text: String,
    onTextChange: (newText: String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        trailingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        onValueChange = { newText ->
            onTextChange(newText)
        },
        label = {
            Text(stringResource(label))
        },
        placeholder = {
            Text(stringResource(placeholder))
        }
    )
}