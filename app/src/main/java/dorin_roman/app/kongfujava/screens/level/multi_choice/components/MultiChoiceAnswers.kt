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
    buttonsColors: List<ColorState>
) {
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

    Button(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(MaterialTheme.spacing.large),
        colors = getColorMultiChoiceAnswers(buttonsColors[0]),
        onClick = {
            if (buttonsColors[0] == ColorState.REGULAR) {
                checkAnswer(questionAnswers[0])
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
            if (buttonsColors[1] == ColorState.REGULAR) {
                checkAnswer(questionAnswers[1])
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
            if (buttonsColors[2] == ColorState.REGULAR) {
                checkAnswer(questionAnswers[2])
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
            if (buttonsColors[3] == ColorState.REGULAR) {
                checkAnswer(questionAnswers[3])
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