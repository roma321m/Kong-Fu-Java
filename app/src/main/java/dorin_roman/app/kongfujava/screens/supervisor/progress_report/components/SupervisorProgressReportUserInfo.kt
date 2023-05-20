package dorin_roman.app.kongfujava.screens.supervisor.progress_report.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.supervisor.StudentModel
import dorin_roman.app.kongfujava.ui.theme.elevation
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun SupervisorProgressReportUserInfo(
    studentModel: StudentModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
    ) {
        Card(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large,
                    top = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.small
                )
                .fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.small,
            elevation = MaterialTheme.elevation.small
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.large),
                    style = MaterialTheme.typography.h5,
                    text = stringResource(R.string.user_info),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.medium),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    style = MaterialTheme.typography.h6,
                    text = "name: \t${studentModel.name}"
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.medium),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    style = MaterialTheme.typography.h6,
                    text = "age: \t${studentModel.age}"
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.medium),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    style = MaterialTheme.typography.h6,
                    text = "Code: \t${studentModel.privateCode}"
                )
            }
        }
    }
}