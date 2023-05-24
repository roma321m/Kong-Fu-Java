package dorin_roman.app.kongfujava.ui.components.video


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    val player: Player,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        const val TAG = "MediaViewModel"
        const val KEY_VIDEO_URL = "videoUrl"
    }

    init {
        player.prepare()
    }

    var isPlaying by mutableStateOf(player.isPlaying)
        private set

    var playbackState by mutableStateOf(player.playbackState)
        private set

    var shouldShowControls by mutableStateOf(false)
        private set

    fun handle(event: VideoEvent) {
        when (event) {
            is VideoEvent.AddVideoUrl -> addVideoUrl(event.url)
            is VideoEvent.UpdateIsPlaying -> isPlaying = event.isPlaying
            is VideoEvent.UpdatePlaybackState -> playbackState = event.playbackState
            is VideoEvent.UpdateShouldShowControls -> shouldShowControls = event.shouldShowControls
        }
    }

    private fun addVideoUrl(url: String) {
        Log.d(TAG, "addVideoUrl: $url")
        savedStateHandle[KEY_VIDEO_URL] = url
        player.setMediaItem(MediaItem.fromUri(url))
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}