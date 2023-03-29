package dorin_roman.app.kongfujava.screens.worlds.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WorldsMapButtons(modifier: Modifier, navigateToLevel: () -> Unit) {
//    LazyRow(modifier = modifier.fillMaxWidth()) {
//        //items() {}
//    }

    Button(
        modifier = modifier,
        onClick = navigateToLevel
    ) {
        Text(text = "Levels")
    }
}