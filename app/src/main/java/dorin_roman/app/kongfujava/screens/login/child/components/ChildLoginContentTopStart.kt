package dorin_roman.app.kongfujava.screens.login.child.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun ChildLoginTopLeft() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.large),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .size(150.dp, 150.dp),
            painter = painterResource(id = R.drawable.img_logo_white_round),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
                .fillMaxWidth(0.6f),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.child_login_description),
            style = MaterialTheme.typography.body1,
        )
    }
}