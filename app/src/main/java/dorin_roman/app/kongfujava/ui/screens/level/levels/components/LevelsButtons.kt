package dorin_roman.app.kongfujava.ui.screens.level.levels.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LevelsButtons(
    navigateToTutorialLevel: () -> Unit,
    navigateToMultiChoiceLevel: () -> Unit,
    navigateToDragDropLevel: () -> Unit,
) {
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(6),
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
        // .gridScrollbar(gridState, 6)
        ,
        state = gridState,
        contentPadding = PaddingValues(
            8.dp
//            top = 8.dp,
//            bottom = 85.dp,
//            start = horizontalContentPadding,
//            end = horizontalContentPadding
        )
    ) {
        //items() {}
    }
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