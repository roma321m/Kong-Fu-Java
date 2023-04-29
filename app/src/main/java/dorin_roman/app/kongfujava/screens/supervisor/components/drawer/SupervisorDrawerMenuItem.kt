package dorin_roman.app.kongfujava.screens.supervisor.components.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SupervisorDrawerMenuItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    text: String,
    onItemClick: () -> Unit,
    selected: Boolean = false
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.3f)
            .padding(4.dp)
            .clickable { onItemClick() },
        backgroundColor = if (selected) {
            MaterialTheme.colors.primary
        } else {
            MaterialTheme.colors.background
        },
        elevation = 0.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(start = 24.dp),
                text = text,
            )
        }
    }
}