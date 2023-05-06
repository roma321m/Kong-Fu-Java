package dorin_roman.app.kongfujava.ui.components.media

import androidx.media3.common.MediaItem

data class VideoItem(
    val contentUrl: String,
    val mediaItem: MediaItem,
    val name: String
)