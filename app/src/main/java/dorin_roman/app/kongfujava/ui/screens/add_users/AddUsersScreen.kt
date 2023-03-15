package dorin_roman.app.kongfujava.ui.screens.add_users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun AddUsersScreen(
    navigateProgressReport: () -> Unit,
) {
    // fixme - temp
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Add Users Screen")
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = navigateProgressReport) {
            Text(text = "Progress Report")
        }
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@DevicePreviews
@Composable
fun ProgressReportScreenPreview() {
    KongFuJavaTheme {
        AddUsersScreen({})
    }
}