package dorin_roman.app.kongfujava.screens.supervisor.components.content.progress_report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.screens.supervisor.StudentModel

@Composable
fun SupervisorProgressReport(
    modifier: Modifier = Modifier,
    studentModel: StudentModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Progress Report screen - temp UI",
            style = MaterialTheme.typography.h3
        )
        Text(
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.h5,
            text = "id: ${studentModel.id}"
        )
        Text(
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.h5,
            text = "name: ${studentModel.name}"
        )
    }
}