package dorin_roman.app.kongfujava.screens.supervisor.add_users.components

import android.content.Context
import android.content.Intent
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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.service.CodeService
import dorin_roman.app.kongfujava.service.CodeState
import dorin_roman.app.kongfujava.service.CodeViewModel
import dorin_roman.app.kongfujava.ui.theme.elevation

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SupervisorAddUsersCreateCode(
    modifier: Modifier = Modifier,
    codeViewModel: CodeViewModel,
) {
    val context = LocalContext.current
    val minutes by codeViewModel.minutes
    val seconds by codeViewModel.seconds
    val currentState by codeViewModel.currentState

    Card(
        modifier = modifier,
        elevation = MaterialTheme.elevation.default
    ) {
        if (currentState != CodeState.Idle) {
            AnimatedContent(targetState = minutes, transitionSpec = { addAnimation() }) {
                Text(
                    text = minutes, style = TextStyle(
                        fontSize = MaterialTheme.typography.h1.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = if (minutes == "00") Color.White else Color.Blue
                    )
                )
            }
            AnimatedContent(targetState = seconds, transitionSpec = { addAnimation() }) {
                Text(
                    text = seconds, style = TextStyle(
                        fontSize = MaterialTheme.typography.h1.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = if (seconds == "00") Color.White else Color.Blue
                    )
                )
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
                        triggerForegroundService(context)
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

fun triggerForegroundService(context: Context) {
    Intent(context, CodeService::class.java).apply {
        context.startService(this)
    }
}