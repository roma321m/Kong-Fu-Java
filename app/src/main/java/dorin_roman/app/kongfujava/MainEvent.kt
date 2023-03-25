package dorin_roman.app.kongfujava

sealed class MainEvent {
    object Child : MainEvent()
    object Parent : MainEvent()
    object Teacher : MainEvent()
}