package dorin_roman.app.kongfujava.screens.level.drag_drop.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing

@Composable
fun DragDropContentTitle(
    levelNumber: Int,
    title: String,
    questionTitle: String
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

        Text(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.large),
            text = "${stringResource(id = R.string.level).uppercase()} $levelNumber",
            style = MaterialTheme.typography.h5
        )

        Text(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.large),
            text = title,
            style = MaterialTheme.typography.h4
        )
    }

    Box(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.large)
                .padding(top = MaterialTheme.spacing.extraLarge)
                .fillMaxWidth(),
            text = questionTitle,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
    }
}