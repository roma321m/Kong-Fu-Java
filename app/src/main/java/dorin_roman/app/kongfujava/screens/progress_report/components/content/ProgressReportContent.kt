package dorin_roman.app.kongfujava.screens.progress_report.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgressReportContent(
    navigateToAddUsers: () -> Unit,
    paddingValues: PaddingValues
) {
    // fixme - temp ui
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = navigateToAddUsers) {
            Text(text = "Add users")
        }
        Spacer(modifier = Modifier.padding(10.dp))
    }
}