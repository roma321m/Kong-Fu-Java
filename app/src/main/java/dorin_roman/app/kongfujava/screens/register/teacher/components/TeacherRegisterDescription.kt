package dorin_roman.app.kongfujava.screens.register.teacher.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun TeacherRegisterDescription() {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier.fillMaxWidth(0.3f),
            painter = painterResource(id = R.drawable.img_logo_white_round),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(MaterialTheme.spacing.small),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.register_title),
            style = MaterialTheme.typography.h4
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(MaterialTheme.spacing.small),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.register_description),
            style = MaterialTheme.typography.body1
        )

    }
}