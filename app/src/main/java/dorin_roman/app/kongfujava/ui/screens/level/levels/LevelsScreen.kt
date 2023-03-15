package dorin_roman.app.kongfujava.ui.screens.level.levels

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
fun LevelsScreen(
    navigateToTutorialLevel: () -> Unit,
    navigateToMultiChoiceLevel: () -> Unit,
    navigateToDragDropLevel: () -> Unit,
) {
    // fixme - temp
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "levels screen")
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = navigateToTutorialLevel) {
            Text(text = "Tutorial Level")
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = navigateToMultiChoiceLevel) {
            Text(text = "Multi Choice Level")
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = navigateToDragDropLevel) {
            Text(text = "Drag Drop Level")
        }
    }
}

@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        LevelsScreen({}, {}, {})
    }
}