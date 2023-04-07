package dorin_roman.app.kongfujava.screens.progress_report.components.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R

@Composable
fun ProgressReportDrawerHeader() {
    // todo -- add data about the user (class name, etc)
    Box(
        modifier = Modifier
            .size(200.dp)
            .padding(20.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier
                .sizeIn(150.dp),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
        )
        Image(
            modifier = Modifier
                .scale(1.4f),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
        )
    }
}