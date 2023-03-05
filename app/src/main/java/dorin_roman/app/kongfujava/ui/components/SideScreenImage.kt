package dorin_roman.app.kongfujava.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dorin_roman.app.kongfujava.R

@Composable
fun SideScreenImage(id: Int) {
    Image(
        modifier = Modifier
            .background(MaterialTheme.colors.secondary)
            .fillMaxSize(),
        painter = painterResource(id = id),
        contentDescription = stringResource(R.string.panda_image_8)
    )
}