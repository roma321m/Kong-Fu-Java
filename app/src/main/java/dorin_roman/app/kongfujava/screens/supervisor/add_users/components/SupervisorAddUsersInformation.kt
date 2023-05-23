package dorin_roman.app.kongfujava.screens.supervisor.add_users.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun SupervisorAddUsersInformation(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .padding(
                    top = MaterialTheme.spacing.extraLarge,
                    bottom = MaterialTheme.spacing.large,
                    start = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.large
                ),
            painter = painterResource(id = R.drawable.img_logo_white_round),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(MaterialTheme.spacing.large),
            text = stringResource(R.string.add_users_create_new_code),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(MaterialTheme.spacing.large),
            text = stringResource(R.string.add_users_info_body),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    }
}