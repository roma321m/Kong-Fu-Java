package dorin_roman.app.kongfujava.ui.screens.worlds

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@Composable
fun WorldsScreen(
    navigateToLevel: () -> Unit,
) {
    // fixme
    Text(
        text = "worlds screen",
        modifier = Modifier.padding(50.dp)
    )
}

@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        WorldsScreen({})
    }
}