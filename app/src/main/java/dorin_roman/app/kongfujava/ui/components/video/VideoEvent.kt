package dorin_roman.app.kongfujava.ui.components.video

sealed class VideoEvent {
    class AddVideoUrl(val url: String) : VideoEvent()
    class UpdateShouldShowControls(val shouldShowControls: Boolean) : VideoEvent()
    class UpdateIsPlaying(val isPlaying: Boolean) : VideoEvent()
    class UpdatePlaybackState(val playbackState: Int) : VideoEvent()
}