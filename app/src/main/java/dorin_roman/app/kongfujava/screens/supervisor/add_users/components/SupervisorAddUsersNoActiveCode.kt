package dorin_roman.app.kongfujava.screens.supervisor.add_users.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun SupervisorAddUsersNoActiveCode(
    modifier: Modifier = Modifier,
    onCreateCodeClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium),
            text = stringResource(R.string.code_will_be_valid_for_5_minutes_only),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium),
            text = stringResource(R.string.you_can_generate_as_many_codes_as_you_need),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )

        Button(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium),
            onClick = {
                onCreateCodeClicked()
            }
        ) {
            Text(
                text = stringResource(R.string.generate_code),
                style = MaterialTheme.typography.h6
            )
        }
    }
}