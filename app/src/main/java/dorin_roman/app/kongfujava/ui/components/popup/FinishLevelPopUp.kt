package dorin_roman.app.kongfujava.ui.components.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.screens.worlds.components.WORLD_TYPE
import dorin_roman.app.kongfujava.ui.components.Stars
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun FinishLevelPopUp(onDismiss: () -> Unit, levelNumber: Int, levelState: PointState) {

    Dialog(
        onDismissRequest = {
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
                        .padding(10.dp)
                        .fillMaxWidth()
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .clip(CircleShape),
                        painter = painterResource(id = popUpImage(levelState)),
                        contentDescription = "",
                        alignment = Alignment.Center
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 12.dp, bottom = 16.dp),
                    text = stringResource(id = R.string.alert_dialog_level) + " $levelNumber " + stringResource(id = R.string.alert_dialog_complete),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground)
                )


                Text(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                    text = stringResource(id = R.string.alert_dialog_score),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onBackground)
                )

                Row(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium),
                ) {
                    Stars(
                        state = levelState,
                        type = WORLD_TYPE
                    )
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp, start = 36.dp, end = 36.dp, bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    onClick = {
                        onDismiss()
                    }) {
                    Text(text = stringResource(id = R.string.alert_dialog_next))
                }

            }
        }
    }

}



@Preview
@Composable
fun FinishLevelPopUpPreview() {
    FinishLevelPopUp(onDismiss = {}, levelNumber = 1, levelState = PointState.ZERO)
}