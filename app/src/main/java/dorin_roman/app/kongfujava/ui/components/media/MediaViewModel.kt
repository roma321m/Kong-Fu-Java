package dorin_roman.app.kongfujava.ui.components.media

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val player: Player
) : ViewModel() {

    companion object {
        const val TAG = "MediaViewModel"
        const val KEY_VIDEO_URL = "videoUrl"
    }

    init {
        player.prepare()
    }

    private val videoUrls = savedStateHandle.getStateFlow(KEY_VIDEO_URL, emptyList<String>())

    val videoItems = videoUrls.map { urls ->
        urls.map { url ->
            VideoItem(
                contentUrl = url,
                mediaItem = MediaItem.fromUri(url),
                name = "No Name"
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    var isPlaying by mutableStateOf(false)
        private set

    fun handle(event: MediaEvent) {
        when (event) {
            is MediaEvent.AddVideoUrl -> addVideoUrl(event.url)
            is MediaEvent.PlayVideo -> {} // playVideo(event.url)
            is MediaEvent.UpdateIsPlaying -> isPlaying = event.isPlaying
        }
    }

    private fun addVideoUrl(url: String) {
        Log.d(TAG, "addVideoUrl: $url")
        savedStateHandle[KEY_VIDEO_URL] = videoUrls.value + url
        player.setMediaItem(MediaItem.fromUri(url))
    }

//    private fun playVideo(url: String) {
//        player.setMediaItem(
//            videoItems.value.find { it.contentUrl == url }?.mediaItem ?: return
//        )
//    }
//
//    private fun pauseVideo(url: String) {
//        player.setMediaItem(
//            videoItems.value.find { it.contentUrl == url }?.mediaItem ?: return
//        )
//    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}