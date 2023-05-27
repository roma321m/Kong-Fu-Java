package dorin_roman.app.kongfujava.ui.components.topbar

import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.popup.DisplayAlertDialog
import dorin_roman.app.kongfujava.ui.components.topbar.components.BackButton
import dorin_roman.app.kongfujava.ui.components.topbar.components.DropDownMenuAppBar
import dorin_roman.app.kongfujava.ui.components.topbar.components.MenuButton
import dorin_roman.app.kongfujava.ui.components.topbar.components.Title
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

const val TAG = "TopBar"

@Composable
fun TopBar(
    title: Int,
    hasBackButton: Boolean = true,
    onBackPressed: () -> Unit = {},
    viewModel: TopBarViewModel = hiltViewModel()
) {
    var showMenu by remember { mutableStateOf(false) }
    var openLogOutDialog by remember { mutableStateOf(false) }
    var openMusicDialog by remember { mutableStateOf(false) }
    var openAboutDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(R.string.top_bar_log_out_title),
        message = stringResource(R.string.top_bar_log_out_body),
        openDialog = openLogOutDialog,
        closeDialog = { openLogOutDialog = false },
        onYesClicked = {
            viewModel.handle(TopBarEvent.LogOutClicked)
        }
    )

    DisplayAlertDialog(
        title = if (viewModel.isMusicPlaying) {
            stringResource(R.string.top_bar_stop_music_title)
        } else {
            stringResource(R.string.top_bar_play_music_title)
        },
        message = if (viewModel.isMusicPlaying) {
            stringResource(R.string.top_bar_stop_music_body)
        } else {
            stringResource(R.string.top_bar_play_music_body)
        },
        openDialog = openMusicDialog,
        closeDialog = { openMusicDialog = false },
        onYesClicked = {
            viewModel.handle(TopBarEvent.MusicClicked)
        }
    )

    DisplayAlertDialog(
        title = stringResource(R.string.top_bar_about_title),
        message = stringResource(R.string.top_bar_about_body),
        isConfirmOnly = true,
        openDialog = openAboutDialog,
        closeDialog = { openAboutDialog = false },
        onYesClicked = {
            viewModel.handle(TopBarEvent.AboutClicked)
        }
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        Log.d(TAG, "lifecycleOwner")
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    Log.d(TAG, "ON_RESUME")
                    if (viewModel.isMusicPlaying) {
                        viewModel.mediaPlayer.start()
                    }
                }

                Lifecycle.Event.ON_PAUSE -> {
                    Log.d(TAG, "ON_PAUSE")
                    if (viewModel.isMusicPlaying) {
                        viewModel.mediaPlayer.pause()
                    }
                }

                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            Log.d(TAG, "onDispose")
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    TopAppBar(
        title = {
            Title(id = title, modifier = Modifier)
        },
        navigationIcon = {
            if (hasBackButton) {
                BackButton(onBackPressed = onBackPressed)
            }
        },
        actions = {
            MenuButton(onClick = { showMenu = !showMenu })
            DropDownMenuAppBar(
                showMenu = showMenu,
                isPlaying = viewModel.isMusicPlaying,
                onDismissRequest = { showMenu = false },
                onLogOutClicked = {
                    openLogOutDialog = true
                },
                onHelpClicked = {
                    openAboutDialog = true
                },
                onMusicClicked = {
                    openMusicDialog = true
                },
            )
        },
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onSecondary,
    )
}

@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        TopBar(
            onBackPressed = {},
            title = 0
        )
    }
}