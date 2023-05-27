package dorin_roman.app.kongfujava.ui.components.topbar

sealed class TopBarEvent {
    object LogOutClicked : TopBarEvent()
    object MusicClicked : TopBarEvent()
    object AboutClicked : TopBarEvent()
    object Pause : TopBarEvent()
    object Resume : TopBarEvent()
}