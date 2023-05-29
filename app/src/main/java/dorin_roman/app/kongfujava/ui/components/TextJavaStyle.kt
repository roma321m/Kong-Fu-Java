package dorin_roman.app.kongfujava.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.ui.theme.spacing
import dorin_roman.app.kongfujava.ui.theme.textBlue
import dorin_roman.app.kongfujava.ui.theme.textGray
import dorin_roman.app.kongfujava.ui.theme.textGreen
import dorin_roman.app.kongfujava.ui.theme.textOrange
import dorin_roman.app.kongfujava.ui.theme.textPurple
import dorin_roman.app.kongfujava.ui.theme.textYellow


@Composable
fun TextJavaStyle(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val scrollState = rememberScrollState()
        val viewMaxHeight = maxHeight.value
        val columnMaxScroll = scrollState.maxValue
        val scrollStateValue = scrollState.value
        val paddingSize = (scrollStateValue * viewMaxHeight) / columnMaxScroll
        val animation = animateDpAsState(paddingSize.dp)
        val scrollBarHeight = viewMaxHeight / 10

        if (scrollStateValue < columnMaxScroll) {
            Box(
                modifier = Modifier
                    .paddingFromBaseline(animation.value)
                    .padding(MaterialTheme.spacing.extraSmall)
                    .height(scrollBarHeight.dp)
                    .width(4.dp)
                    .background(
                        color = MaterialTheme.colors.onBackground,
                        shape = MaterialTheme.shapes.medium
                    )
                    .align(Alignment.TopEnd),
            )
        }

        Column(
            modifier = Modifier
                .verticalScroll(state = scrollState)
                .fillMaxWidth(),
        ) {
            val rtl = "`"
            val ltr = "|"
            text.split(Regex("(?<=[|`])")).forEach { string ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = style.copy(
                        textDirection = if (string.contains(rtl)) {
                            TextDirection.ContentOrRtl
                        } else {
                            TextDirection.ContentOrLtr
                        }
                    ),
                    text = buildAnnotatedString {
                        val newLine = "*"
                        val tab = "@"
                        val orange = "!"
                        val yellow = "#"
                        val purple = "^"
                        val blue = "&"
                        val comment = "%"
                        val white = "~"
                        val question = ">"
                        string.split(Regex("(?<=[*@!#%^&~>|`])")).forEach { string ->
                            if (string.contains(newLine)) {
                                appendLine(string.dropLast(1))
                            } else if (string.contains(tab)) {
                                append("   ")
                            } else if (string.contains(rtl)) {
                                append(string.dropLast(1))
                            } else if (string.contains(ltr)) {
                                append(string.dropLast(1))
                            } else if (string.contains(orange)) {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colors.textOrange
                                    )
                                ) {
                                    append(string.dropLast(1))
                                }
                            } else if (string.contains(yellow)) {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colors.textYellow
                                    )
                                ) {
                                    append(string.dropLast(1))
                                }
                            } else if (string.contains(purple)) {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colors.textPurple
                                    )
                                ) {
                                    append(string.dropLast(1))
                                }
                            } else if (string.contains(blue)) {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colors.textBlue
                                    )
                                ) {
                                    append(string.dropLast(1))
                                }
                            } else if (string.contains(comment)) {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colors.textGray
                                    )
                                ) {
                                    append(string.dropLast(1))
                                }
                            } else if (string.contains(question)) {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colors.textGreen
                                    )
                                ) {
                                    append(string.dropLast(1))
                                }
                            } else if (string.contains(white)) {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colors.onBackground
                                    )
                                ) {
                                    append(string.dropLast(1))
                                }
                            } else {
                                append(string)
                            }
                        }
                    }
                )
            }
        }
    }
}