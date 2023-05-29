package dorin_roman.app.kongfujava.screens.level.drag_drop.components

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun DragAnswerItemCard(
    answer: String,
    color: Color,
) {

    Card(
        elevation = 10.dp,
        backgroundColor = color,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
            .size(width = 300.dp, height = 150.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = answer,
                textAlign = TextAlign.Center,
                maxLines = 1,
                style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onPrimary)
            )
        }
    }

}

