package dorin_roman.app.kongfujava.ui.screens.user_type

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dorin_roman.app.kongfujava.R

@Composable
fun UserTypeScreenImage() {
    Image(
        modifier = Modifier
            .fillMaxSize(),
        painter = painterResource(id = R.drawable.ic_logo), // Fixme - change the image
        contentDescription = stringResource(R.string.panda_image_8)
    )
}