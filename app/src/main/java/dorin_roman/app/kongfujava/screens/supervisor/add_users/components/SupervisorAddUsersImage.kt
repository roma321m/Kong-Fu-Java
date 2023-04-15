package dorin_roman.app.kongfujava.screens.supervisor.add_users.components

import androidx.compose.foundation.Image
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.elevation

@Composable
fun SupervisorAddUsersImage(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = MaterialTheme.elevation.default
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_panda_ninja),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
    }
}