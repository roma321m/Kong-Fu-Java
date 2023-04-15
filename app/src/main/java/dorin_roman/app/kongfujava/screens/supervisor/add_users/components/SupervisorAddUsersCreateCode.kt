package dorin_roman.app.kongfujava.screens.supervisor.add_users.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.ui.theme.elevation


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SupervisorAddUsersCreateCode(
    modifier: Modifier = Modifier,
    hasActiveCode: Boolean,
    minutes: String,
    seconds: String,
    code: String,
    onCreateCodeClicked: () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = MaterialTheme.elevation.default,
        shape = MaterialTheme.shapes.medium
    ) {
        if (hasActiveCode) {
            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = code,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.h3.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primaryVariant
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    AnimatedContent(targetState = minutes, transitionSpec = { addAnimation() }) {
                        Text(
                            text = minutes,
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.h3.fontSize,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.primary
                            )
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    AnimatedContent(targetState = seconds, transitionSpec = { addAnimation() }) {
                        Text(
                            text = seconds,
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.h3.fontSize,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.primary
                            )
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Code will be valid for 5 minutes only"
                )

                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "You can generate as many codes as you need"
                )

                Button(
                    modifier = Modifier
                        .padding(8.dp),
                    onClick = {
                        onCreateCodeClicked()
                    }
                ) {
                    Text(
                        text = "Generate Code"
                    )
                }
            }
        }
    }
}

@ExperimentalAnimationApi
fun addAnimation(duration: Int = 600): ContentTransform {
    return slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}