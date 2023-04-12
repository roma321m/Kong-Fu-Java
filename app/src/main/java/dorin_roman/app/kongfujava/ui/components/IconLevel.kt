package dorin_roman.app.kongfujava.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.ui.theme.kongFuYellow

@Composable
fun IconLevel(imageVector: ImageVector, tint: Color) {
    Icon(
        imageVector = imageVector,
        contentDescription = "icon",
        tint = tint,
        modifier = Modifier
            .size(70.dp)
            .padding(5.dp)
    )
}

//Fixme colors
@Composable
fun LockLevel() {
    IconLevel(Icons.Outlined.Lock, Color.White)
}

@Composable
fun ZeroLevelWhite() {
    IconLevel(Icons.Filled.StarOutline, Color.White)
    IconLevel(Icons.Filled.StarOutline, Color.White)
    IconLevel(Icons.Filled.StarOutline, Color.White)
}

@Composable
fun ZeroLevelYellow() {
    IconLevel(Icons.Filled.StarOutline, kongFuYellow)
    IconLevel(Icons.Filled.StarOutline, kongFuYellow)
    IconLevel(Icons.Filled.StarOutline, kongFuYellow)
}

@Composable
fun OneLevel() {
    IconLevel(Icons.Filled.Star, kongFuYellow)
    IconLevel(Icons.Filled.StarOutline, kongFuYellow)
    IconLevel(Icons.Filled.StarOutline, kongFuYellow)
}

@Composable
fun TwoLevel() {
    IconLevel(Icons.Filled.Star, kongFuYellow)
    IconLevel(Icons.Filled.Star, kongFuYellow)
    IconLevel(Icons.Filled.StarOutline, kongFuYellow)
}

@Composable
fun ThreeLevel() {
    IconLevel(Icons.Filled.Star, kongFuYellow)
    IconLevel(Icons.Filled.Star, kongFuYellow)
    IconLevel(Icons.Filled.Star, kongFuYellow)
}