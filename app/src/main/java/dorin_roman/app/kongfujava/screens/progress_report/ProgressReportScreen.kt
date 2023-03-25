package dorin_roman.app.kongfujava.screens.progress_report

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
fun ProgressReportScreen(
    navigateToAddUsers: () -> Unit,
) {
    // fixme - temp
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Progress Report Screen")
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = navigateToAddUsers) {
            Text(text = "Add users")
        }
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@DevicePreviews
@Composable
fun ProgressReportScreenPreview() {
    KongFuJavaTheme {
        ProgressReportScreen({})
    }
}