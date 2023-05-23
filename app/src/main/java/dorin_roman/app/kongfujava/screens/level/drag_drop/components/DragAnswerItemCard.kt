package dorin_roman.app.kongfujava.screens.level.drag_drop.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun DragAnswerItemCard(answer: String) {
        Card(
            elevation = 10.dp,
            backgroundColor = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(8.dp)
                .size(width = 300.dp, height = 150.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = answer,
                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onPrimary)
                )
            }
        }
}

