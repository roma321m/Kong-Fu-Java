package dorin_roman.app.kongfujava.screens.supervisor.add_users.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.ui.theme.elevation


@Composable
fun SupervisorAddUsersInformation(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = MaterialTheme.elevation.default
    ) {
        Column(
            modifier = Modifier
                .width(400.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Create New Code",
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "By creating a code you can link new users to your account.\nYour user will control those accounts login info and you will get the progress reports for those users",
                style = MaterialTheme.typography.subtitle1
            )

            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}