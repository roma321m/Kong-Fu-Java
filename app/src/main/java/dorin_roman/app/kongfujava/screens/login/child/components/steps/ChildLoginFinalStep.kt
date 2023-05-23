package dorin_roman.app.kongfujava.screens.login.child.components.steps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing

// Fixme
@Composable
fun ChildLoginFinalStep(
    showLoading: Boolean,
) {
    if (showLoading) {
        Box {

            CircularProgressIndicator()

            // place older
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .alpha(0f)
                        .padding(MaterialTheme.spacing.medium)
                        .fillMaxWidth(0.5f),
                    value = "",
                    onValueChange = {},
                )

                Button(
                    modifier = Modifier
                        .alpha(0f)
                        .fillMaxWidth(0.5f)
                        .padding(MaterialTheme.spacing.medium),
                    onClick = {}
                ) {
                    Text(stringResource(R.string.child_login_next))
                }
            }
        }
    }
}