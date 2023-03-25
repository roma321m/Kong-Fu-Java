package dorin_roman.app.kongfujava.ui.screens.register.parent.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun RegisterParentScreenContentTexts() {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.register_description),
        style = MaterialTheme.typography.body1
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.register).uppercase(),
        style = MaterialTheme.typography.h4
    )

}