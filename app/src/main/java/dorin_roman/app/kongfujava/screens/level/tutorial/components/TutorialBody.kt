package dorin_roman.app.kongfujava.screens.level.tutorial.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun TutorialBody(
    onNextClick: () -> Unit,
    text: String
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.large),
            text = text,
            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onBackground)
        )
    }
    Box(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Icon(
            modifier = Modifier
                .padding(MaterialTheme.spacing.large)
                .fillMaxSize(0.08f)
                .clickable {
                    onNextClick()
                },
            imageVector = Icons.Default.SkipNext,
            contentDescription = stringResource(id = R.string.next),
            tint = MaterialTheme.colors.primary,
        )
    }
}