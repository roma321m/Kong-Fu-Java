package dorin_roman.app.kongfujava.ui.screens.login.supervisor.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R

@Composable
fun TeacherLoginScreenContentImage() {
    Image(
        modifier = Modifier
            .size(150.dp, 150.dp),
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = stringResource(id = R.string.app_logo)
    )
}