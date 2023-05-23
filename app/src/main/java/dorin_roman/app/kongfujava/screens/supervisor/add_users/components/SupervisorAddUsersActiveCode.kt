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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.ui.components.layout.LeftToRightLayout
import dorin_roman.app.kongfujava.ui.theme.spacing

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SupervisorAddUsersActiveCode(
    modifier: Modifier = Modifier,
    minutes: String,
    seconds: String,
    code: String
) {
    Column(
        modifier = modifier
            .padding(MaterialTheme.spacing.large),
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
        LeftToRightLayout {
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
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun addAnimation(duration: Int = 600): ContentTransform {
    return slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}