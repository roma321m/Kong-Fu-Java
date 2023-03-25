package dorin_roman.app.kongfujava.ui.components

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
import dorin_roman.app.kongfujava.ui.theme.yellowColor

@Composable
fun IconLevel(imageVector: ImageVector, tint: Color) {
    Icon(
        imageVector = imageVector,
        contentDescription = "Lock",
        tint = tint,
        modifier = Modifier
            .size(50.dp)
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
    IconLevel(Icons.Filled.StarOutline, yellowColor)
    IconLevel(Icons.Filled.StarOutline, yellowColor)
    IconLevel(Icons.Filled.StarOutline, yellowColor)
}

@Composable
fun OneLevel() {
    IconLevel(Icons.Filled.Star, yellowColor)
    IconLevel(Icons.Filled.StarOutline, yellowColor)
    IconLevel(Icons.Filled.StarOutline, yellowColor)
}

@Composable
fun TwoLevel() {
    IconLevel(Icons.Filled.Star, yellowColor)
    IconLevel(Icons.Filled.Star, yellowColor)
    IconLevel(Icons.Filled.StarOutline, yellowColor)
}

@Composable
fun ThreeLevel() {
    IconLevel(Icons.Filled.Star, yellowColor)
    IconLevel(Icons.Filled.Star, yellowColor)
    IconLevel(Icons.Filled.Star, yellowColor)
}