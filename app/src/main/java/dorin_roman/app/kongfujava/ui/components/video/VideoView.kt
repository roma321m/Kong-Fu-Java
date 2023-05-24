package dorin_roman.app.kongfujava.ui.components.video

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.ui.PlayerView
import dorin_roman.app.kongfujava.R
import kotlinx.coroutines.delay

// FIXME - remove - example how to use, link for temp level 1 video
//VideoView(
//    modifier = Modifier.fillMaxSize(),
//    url = "https://github.com/roma321m/Kong-Fu-Java-Videos/raw/main/level1.mp4"
//)

@Composable
fun VideoView(
    url: String,
    modifier: Modifier = Modifier,
    viewModel: VideoViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(viewModel.shouldShowControls) {
        if (viewModel.shouldShowControls) {
            delay(3_000)
            viewModel.handle(VideoEvent.UpdateShouldShowControls(false))
        }
    }

    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(true) {
        viewModel.handle(VideoEvent.AddVideoUrl(url))
    }

    DisposableEffect(key1 = Unit) {
        val listener = object : Player.Listener {
            override fun onEvents(
                player: Player,
                events: Player.Events
            ) {
                super.onEvents(player, events)
                viewModel.handle(VideoEvent.UpdateIsPlaying(player.isPlaying))
                viewModel.handle(VideoEvent.UpdatePlaybackState(player.playbackState))
            }
        }

        viewModel.player.addListener(listener)

        onDispose {
            viewModel.player.removeListener(listener)
            viewModel.player.release()
        }
    }

    Box(modifier = modifier) {

        AndroidView(
            modifier = Modifier
                .background(MaterialTheme.colors.secondary)
                .clickable {
                    viewModel.handle(VideoEvent.UpdateShouldShowControls(viewModel.shouldShowControls.not()))
                },
            factory = {
                PlayerView(context).apply {
                    player = viewModel.player
                    useController = false
                    layoutParams =
                        FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                }
            },
            update = {
                when (lifecycle) {
                    Lifecycle.Event.ON_PAUSE -> {
                        it.onPause()
                        it.player?.pause()
                    }

                    Lifecycle.Event.ON_RESUME -> {
                        it.onResume()
                        it.player?.play()
                    }

                    else -> Unit
                }
            }
        )

        PlayerControls(
            modifier = Modifier.fillMaxSize(),
            isVisible = { viewModel.shouldShowControls },
            isPlaying = { viewModel.isPlaying },
            playbackState = { viewModel.playbackState },
            onPauseToggle = {
                when {
                    viewModel.player.isPlaying -> {
                        viewModel.player.pause()
                    }

                    viewModel.player.isPlaying.not() && viewModel.playbackState == STATE_ENDED -> {
                        viewModel.player.seekTo(0)
                        viewModel.player.playWhenReady = true
                    }

                    else -> {
                        viewModel.player.play()
                    }
                }
                viewModel.handle(VideoEvent.UpdateIsPlaying(viewModel.isPlaying.not()))
            },
        )
    }
}

@Composable
private fun PlayerControls(
    modifier: Modifier = Modifier,
    isVisible: () -> Boolean,
    isPlaying: () -> Boolean,
    onPauseToggle: () -> Unit,
    playbackState: () -> Int,
) {

    val visible = remember(isVisible()) { isVisible() }
    val isVideoPlaying = remember(isPlaying()) { isPlaying() }
    val playerState = remember(playbackState()) { playbackState() }

    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier.background(Color.Black.copy(alpha = 0.5f))
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    modifier = Modifier.fillMaxSize(0.1f),
                    onClick = onPauseToggle
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = when {
                            isVideoPlaying -> Icons.Default.Pause
                            playerState == STATE_ENDED -> Icons.Default.Replay
                            else -> Icons.Default.PlayArrow
                        },
                        contentDescription = when {
                            isVideoPlaying -> stringResource(R.string.video_player_pause)
                            playerState == STATE_ENDED -> stringResource(R.string.video_player_replay)
                            else -> stringResource(R.string.video_player_play)
                        }
                    )
                }
            }
        }
    }
}