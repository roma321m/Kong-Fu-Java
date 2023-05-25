package dorin_roman.app.kongfujava.screens.level.multi_choice.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.ui.theme.Error

@Composable
fun MultiChoiceAnswers(
    modifier: Modifier,
    questionAnswers: List<String>,
    shownHints: List<String>,
    checkAnswer: (String) -> Unit,
    finishLevel: () -> Unit,
    isRight: Boolean?
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        val hint = remember { mutableStateOf(false) }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = if (shownHints.contains(questionAnswers[0])) {
                ButtonDefaults.buttonColors(MaterialTheme.colors.Error)
            } else {
                ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            },
            onClick = {
                checkAnswer(questionAnswers[0])
                finishLevel()
            }
        ) {
            Text(text = questionAnswers[0])
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = if (shownHints.contains(questionAnswers[1])) {
                Log.d("dorin", shownHints.size.toString())
                ButtonDefaults.buttonColors(MaterialTheme.colors.Error)
            } else {
                ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            }
            ,
            onClick = {
                checkAnswer(questionAnswers[1])
                finishLevel()
            }) {
            Text(text = questionAnswers[1])
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = if (shownHints.contains(questionAnswers[2])) {
                ButtonDefaults.buttonColors(MaterialTheme.colors.Error)
            } else {
                ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            },
            onClick = {
                checkAnswer(questionAnswers[2])
                finishLevel()
            }) {
            Text(text = questionAnswers[2])
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = if (shownHints.contains(questionAnswers[3])) {
                ButtonDefaults.buttonColors(MaterialTheme.colors.Error)
            } else {
                ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            },
            onClick = {
                checkAnswer(questionAnswers[3])
                finishLevel()
            }) {
            Text(text = questionAnswers[3])
        }

    }
}