package dorin_roman.app.kongfujava.screens.level.drag_drop.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DropAnswerItemCard(
    backgroundColor: Color,
    answerItem: String
) {

    Card(
        elevation = 10.dp,
        backgroundColor = backgroundColor,
        border = BorderStroke(3.dp, MaterialTheme.colors.primary),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .size(width = 150.dp, height = 100.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = answerItem,
                style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onPrimary)
            )
        }
    }

}