package dorin_roman.app.kongfujava.screens.level.multi_choice.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MultiChoiceAnswers(modifier: Modifier, questionAnswers: List<String>, shownHints: List<String>, checkAnswer: (String) -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = if (shownHints.contains(questionAnswers[0])) {
                ButtonDefaults.buttonColors(MaterialTheme.colors.error)
            } else {
                ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            },
            onClick = { checkAnswer(questionAnswers[0]) }
        ) {
            Text(text = questionAnswers[0])
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = if (shownHints.contains(questionAnswers[1])) {
                ButtonDefaults.buttonColors(MaterialTheme.colors.error)
            } else {
                ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            },
            onClick = {
                checkAnswer(questionAnswers[1])
            }) {
            Text(text = questionAnswers[1])
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = if (shownHints.contains(questionAnswers[2])) {
                ButtonDefaults.buttonColors(MaterialTheme.colors.error)
            } else {
                ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            },
            onClick = {
                checkAnswer(questionAnswers[2])
            }) {
            Text(text = questionAnswers[2])
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = if (shownHints.contains(questionAnswers[3])) {
                ButtonDefaults.buttonColors(MaterialTheme.colors.error)
            } else {
                ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            },
            onClick = {
                checkAnswer(questionAnswers[3])
            }) {
            Text(text = questionAnswers[3])
        }

    }
}