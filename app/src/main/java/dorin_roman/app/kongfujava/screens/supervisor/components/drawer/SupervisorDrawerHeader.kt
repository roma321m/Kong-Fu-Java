package dorin_roman.app.kongfujava.screens.supervisor.components.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SupervisorDrawerHeader(
    modifier: Modifier = Modifier
) {
    // todo -- add data about the user (class name, etc)
    Column(
        modifier = modifier
            .size(200.dp)
            .background(MaterialTheme.colors.secondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "temp")
        Text(text = "temp 2")
    }
}