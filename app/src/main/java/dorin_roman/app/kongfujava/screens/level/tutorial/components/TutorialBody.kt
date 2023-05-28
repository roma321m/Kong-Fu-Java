package dorin_roman.app.kongfujava.screens.level.tutorial.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.TextJavaStyle
import dorin_roman.app.kongfujava.ui.components.mirror
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
        TextJavaStyle(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.large)
                .fillMaxSize(0.9f),
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
                .mirror()
                .clickable {
                    onNextClick()
                },
            imageVector = Icons.Default.SkipNext,
            contentDescription = stringResource(id = R.string.next),
            tint = MaterialTheme.colors.primary,
        )
    }
}