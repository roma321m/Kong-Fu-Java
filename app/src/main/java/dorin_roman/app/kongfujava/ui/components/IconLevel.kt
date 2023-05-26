package dorin_roman.app.kongfujava.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.ui.theme.star

@Composable
fun IconLevel(imageVector: ImageVector, tint: Color, modifier: Modifier) {
    Icon(
        imageVector = imageVector,
        contentDescription = "icon",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun LockLevel(type: String) {
    val size = if (type == "World") 70.dp else 30.dp
    IconLevel(
        imageVector = Icons.Outlined.Lock,
        tint = Color.White,
        modifier = Modifier
            .alpha(0f)
            .size(size)
            .padding(5.dp)
    )
    IconLevel(
        imageVector = Icons.Outlined.Lock,
        tint = Color.White,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
    IconLevel(
        imageVector = Icons.Outlined.Lock,
        tint = Color.White,
        modifier = Modifier
            .size(size)
            .alpha(0f)
            .padding(5.dp)
    )
}

@Composable
fun ZeroLevelWhite(type: String) {
    val size = if (type == "World") 70.dp else 30.dp
    IconLevel(
        imageVector = Icons.Filled.StarOutline,
        tint = Color.White,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
    IconLevel(
        imageVector = Icons.Filled.StarOutline,
        tint = Color.White,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
    IconLevel(
        imageVector = Icons.Filled.StarOutline,
        tint = Color.White,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
}

@Composable
fun ZeroLevelYellow(type: String) {
    val size = if (type == "World") 70.dp else 30.dp

    IconLevel(
        imageVector = Icons.Filled.StarOutline,
        tint = MaterialTheme.colors.star,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
    IconLevel(
        imageVector = Icons.Filled.StarOutline,
        tint = MaterialTheme.colors.star,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
    IconLevel(
        imageVector = Icons.Filled.StarOutline,
        tint = MaterialTheme.colors.star,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
}

@Composable
fun OneLevel(type: String) {
    val size = if (type == "World") 70.dp else 30.dp

    IconLevel(
        imageVector = Icons.Filled.Star,
        tint = MaterialTheme.colors.star,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
    IconLevel(
        imageVector = Icons.Filled.StarOutline,
        tint = MaterialTheme.colors.star,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
    IconLevel(
        imageVector = Icons.Filled.StarOutline,
        tint = MaterialTheme.colors.star,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
}

@Composable
fun TwoLevel(type: String) {
    val size = if (type == "World") 70.dp else 30.dp

    IconLevel(
        imageVector = Icons.Filled.Star,
        tint = MaterialTheme.colors.star,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
    IconLevel(
        imageVector = Icons.Filled.Star,
        tint = MaterialTheme.colors.star,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
    IconLevel(
        imageVector = Icons.Filled.StarOutline,
        tint = MaterialTheme.colors.star,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
}

@Composable
fun ThreeLevel(type: String) {
    val size = if (type == "World") 70.dp else 30.dp

    IconLevel(
        imageVector = Icons.Filled.Star,
        tint = MaterialTheme.colors.star,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
    IconLevel(
        imageVector = Icons.Filled.Star,
        tint = MaterialTheme.colors.star,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
    IconLevel(
        imageVector = Icons.Filled.Star,
        tint = MaterialTheme.colors.star,
        modifier = Modifier
            .size(size)
            .padding(5.dp)
    )
}