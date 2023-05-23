package dorin_roman.app.kongfujava.screens.supervisor.progress_report.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import dorin_roman.app.kongfujava.ui.components.image.RoundedImage
import dorin_roman.app.kongfujava.ui.theme.elevation
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun SupervisorProgressReportUserInfo(
    studentModel: StudentModel,
    world: String,
    level: Int,
    isLoading: Boolean = false,
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
            if (isLoading) {
                SupervisorProgressReportUserInfoShimmer()
            } else {

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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.spacing.medium)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(start = MaterialTheme.spacing.large)
                                .fillMaxHeight()
                                .fillMaxWidth(0.5f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(MaterialTheme.spacing.medium),
                                textAlign = TextAlign.Start,
                                maxLines = 1,
                                style = MaterialTheme.typography.h6,
                                text = "Name: ${studentModel.name}"
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(MaterialTheme.spacing.medium),
                                textAlign = TextAlign.Start,
                                maxLines = 1,
                                style = MaterialTheme.typography.h6,
                                text = "Age: ${studentModel.age}"
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(MaterialTheme.spacing.medium),
                                textAlign = TextAlign.Start,
                                maxLines = 1,
                                style = MaterialTheme.typography.h6,
                                text = "Code: ${studentModel.privateCode}"
                            )
                            if (world.isNotBlank()) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.spacing.medium),
                                    textAlign = TextAlign.Start,
                                    maxLines = 1,
                                    style = MaterialTheme.typography.h6,
                                    text = "Current World: $world"
                                )
                            }
                            if (level != 0) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.spacing.medium),
                                    textAlign = TextAlign.Start,
                                    maxLines = 1,
                                    style = MaterialTheme.typography.h6,
                                    text = "Current Level: $level"
                                )
                            }
                        }
                        RoundedImage(
                            modifier = Modifier
                                .padding(MaterialTheme.spacing.extraLarge)
                                .padding(MaterialTheme.spacing.extraLarge)
                                .fillMaxSize(),
                            imageUrl = studentModel.imageUrl.ifBlank {
                                "https://firebasestorage.googleapis.com/v0/b/kong-fu-java.appspot.com/o/images%2Fno-profile-picture.jpg?alt=media&token=a8888095-d35f-45b5-ae1a-c560f1c64ec2"
                            },
                            onClick = { }
                        )
                    }
                }
            }
        }
    }
}