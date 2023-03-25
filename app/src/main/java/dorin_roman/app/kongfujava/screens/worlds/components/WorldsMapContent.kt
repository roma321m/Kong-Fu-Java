package dorin_roman.app.kongfujava.screens.worlds.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dorin_roman.app.kongfujava.screens.worlds.WorldsMapViewModel
import dorin_roman.app.kongfujava.ui.components.BackButton
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun WorldsMapContent(navigateToLevel: () -> Unit, worldsMapViewModel: WorldsMapViewModel) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.secondary)
            .padding(12.dp)
            .fillMaxSize(),
    ) {
        BackButton(
            modifier = Modifier.wrapContentSize(),
            onBackPressed = {} //fixme - onclick
        )
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WorldsMapTitle()
            Spacer(modifier = Modifier.height(10.dp))
            WorldsMapButtons()
            Button(onClick = navigateToLevel) {
                Text(text = "Levels")
            }
        }
    }
}

@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        WorldsMapContent({}, viewModel())
    }
}
