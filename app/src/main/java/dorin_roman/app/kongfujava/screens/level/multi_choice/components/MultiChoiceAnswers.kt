package dorin_roman.app.kongfujava.screens.level.multi_choice.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MultiChoiceAnswers(modifier: Modifier, questionAnswers: List<String>){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {

        Button(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            onClick = { }) {
            Text(text = questionAnswers.get(0))
        }

        Button(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            onClick = { }) {
            Text(text = questionAnswers.get(1))
        }

        Button(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            onClick = { }) {
            Text(text = questionAnswers.get(2))
        }

        Button(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            onClick = { }) {
            Text(text = questionAnswers.get(3))
        }

    }
}