package dorin_roman.app.kongfujava.screens.level.tutorial.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun TutorialTitle(
    levelNumber: Int,
    title: String
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.large),
            text = "${stringResource(id = R.string.level).uppercase()} $levelNumber",
            style = MaterialTheme.typography.h5.copy(
                color = MaterialTheme.colors.onBackground
            )
        )

        Text(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.large),
            text = title,
            style = MaterialTheme.typography.h4.copy(
                color = MaterialTheme.colors.onBackground
            )
        )
    }
}