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
fun MultiChoiceAnswers(
    modifier: Modifier,
    questionAnswers: List<String>,
    shownHints: List<String>,
    checkAnswer: (String) -> Unit,
    finishLevel: () -> Unit,
    isRight: Boolean?,
    handleMistakes: () -> Unit,
    buttonsColors: List<ColorState>
) {


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = GetColorMultiChoiceAnswers(buttonsColors[0]),
            onClick = {
                checkAnswer(
                    questionAnswers[0]
                )
                if (isRight == false) {
                    handleMistakes()
                }
                finishLevel()
            }
        ) {
            Text(text = questionAnswers[0])
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = GetColorMultiChoiceAnswers(buttonsColors[1]),
            onClick = {
                checkAnswer(questionAnswers[1])
                if (isRight == false) {
                    handleMistakes()
                }
                finishLevel()
            }) {
            Text(text = questionAnswers[1])
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = GetColorMultiChoiceAnswers(buttonsColors[2]),
            onClick = {
                checkAnswer(questionAnswers[2])
                if (isRight == false) {
                    handleMistakes()
                }
                finishLevel()
            }) {
            Text(text = questionAnswers[2])
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = GetColorMultiChoiceAnswers(buttonsColors[3]),
            onClick = {
                checkAnswer(questionAnswers[3])
                if (isRight == false) {
                    handleMistakes()
                }
                finishLevel()
            }) {
            Text(text = questionAnswers[3])
        }

    }
}