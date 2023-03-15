package dorin_roman.app.kongfujava.ui.screens.worlds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@Composable
fun WorldsScreen(
    navigateToLevel: () -> Unit,
) {
    // fixme - temp
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Worlds Screen")
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = navigateToLevel) {
            Text(text = "Levels")
        }
    }
}

@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        WorldsScreen({})
    }
}