package dorin_roman.app.kongfujava.screens.level.multi_choice.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun ColumnScope.MultiChoiceAnswers(
    questionAnswers: List<String>,
    checkAnswer: (String) -> Unit,
    finishLevel: () -> Unit,
    isRight: Boolean,
    mistakesCount: Int,
    handleMistakes: () -> Unit,
    buttonsColors: List<ColorState>
) {
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

    Button(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(MaterialTheme.spacing.large),
        colors = getColorMultiChoiceAnswers(buttonsColors[0]),
        onClick = {
            checkAnswer(
                questionAnswers[0]
            )
            if (!isRight) {
                handleMistakes()
            }
            if (mistakesCount == 3 || isRight) {
                finishLevel()
            }
        }
    ) {
        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium),
            text = questionAnswers[0]
        )
    }

    Button(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(MaterialTheme.spacing.large),
        colors = getColorMultiChoiceAnswers(buttonsColors[1]),
        onClick = {
            checkAnswer(questionAnswers[1])
            if (!isRight) {
                handleMistakes()
            }
            if (mistakesCount == 3 || isRight) {
                finishLevel()
            }
        }
    ) {
        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium),
            text = questionAnswers[1]
        )
    }

    Button(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(MaterialTheme.spacing.large),
        colors = getColorMultiChoiceAnswers(buttonsColors[2]),
        onClick = {
            checkAnswer(questionAnswers[2])
            if (!isRight) {
                handleMistakes()
            }
            if (mistakesCount == 3 || isRight) {
                finishLevel()
            }
        }
    ) {
        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium),
            text = questionAnswers[2]
        )
    }

    Button(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(MaterialTheme.spacing.large),
        colors = getColorMultiChoiceAnswers(buttonsColors[3]),
        onClick = {
            checkAnswer(questionAnswers[3])
            if (!isRight) {
                handleMistakes()
            }
            if (mistakesCount == 3 || isRight) {
                finishLevel()
            }
        }
    ) {
        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium),
            text = questionAnswers[3]
        )
    }
}