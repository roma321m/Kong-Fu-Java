package dorin_roman.app.kongfujava.ui.components.media

sealed class MediaEvent {
    class AddVideoUrl(val url: String) : MediaEvent()
    class PlayVideo(val url: String) : MediaEvent()
    class UpdateIsPlaying(val isPlaying: Boolean) : MediaEvent()
}