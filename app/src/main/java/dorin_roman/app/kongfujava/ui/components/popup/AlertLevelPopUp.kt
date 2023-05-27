package dorin_roman.app.kongfujava.ui.components.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.ui.theme.mediumGray

@Composable
fun AlertLevelPopUp(levelNumber: Int, onDismiss: () -> Unit, onClick: () -> Unit) {
    Dialog(onDismissRequest = {
            onDismiss()
        }
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(40.dp))
                .fillMaxWidth(),
            elevation = 4.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp)
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .clip(CircleShape),
                        painter = painterResource(id = popUpImage(PointState.LOCK)),
                        contentDescription = "",
                        alignment = Alignment.Center
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 12.dp, bottom = 16.dp),
                    text = stringResource(id = R.string.alert_dialog_level) + " $levelNumber " + stringResource(id = R.string.alert_dialog_not_complete),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground)
                )


                Text(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                    text = stringResource(id = R.string.alert_dialog_exit),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onBackground)
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp, start = 12.dp, end = 12.dp),
                    text = stringResource(id = R.string.alert_dialog_save),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground)
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp, start = 36.dp, end = 36.dp, bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    onClick = {
                        onClick()
                    }) {
                    Text(text = stringResource(id = R.string.alert_dialog_yes))
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 36.dp, end = 36.dp, bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.mediumGray),
                    onClick = {
                        onDismiss()
                    }) {
                    Text(
                        text = stringResource(id = R.string.alert_dialog_no),
                        style = TextStyle(color = MaterialTheme.colors.onPrimary)
                    )
                }

            }
        }
    }
}



@Preview
@Composable
fun AlertLevelPopUpPreview() {
    AlertLevelPopUp( levelNumber = 1, onDismiss = {}, onClick = {})
}