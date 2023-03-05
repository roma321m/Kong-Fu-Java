package dorin_roman.app.kongfujava.view_models

sealed class MainEvent {
    object Teacher: MainEvent()
    object Parent: MainEvent()
    object Child: MainEvent()
}