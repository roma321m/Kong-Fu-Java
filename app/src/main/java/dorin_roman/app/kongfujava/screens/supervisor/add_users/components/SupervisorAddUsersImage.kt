package dorin_roman.app.kongfujava.screens.supervisor.add_users.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import dorin_roman.app.kongfujava.R

@Composable
fun SupervisorAddUsersImage(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_panda_ninja),
        contentScale = ContentScale.FillBounds,
        contentDescription = null
    )
}