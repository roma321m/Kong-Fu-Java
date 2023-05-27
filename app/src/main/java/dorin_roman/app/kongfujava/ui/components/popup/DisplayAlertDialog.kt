package dorin_roman.app.kongfujava.ui.components.popup

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import dorin_roman.app.kongfujava.R

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    onYesClicked: () -> Unit,
    isConfirmOnly: Boolean = false,
) {
    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onYesClicked()
                        closeDialog()
                    }
                ) {
                    Text(
                        text = if (isConfirmOnly) {
                            stringResource(R.string.alert_dialog_ok)
                        } else {
                            stringResource(R.string.alert_dialog_yes)
                        }
                    )
                }
            },
            dismissButton = {
                if (isConfirmOnly.not()) {
                    OutlinedButton(
                        onClick = {
                            closeDialog()
                        }
                    ) {
                        Text(text = stringResource(R.string.alert_dialog_no))
                    }
                }
            },
            onDismissRequest = { closeDialog() }
        )
    }
}