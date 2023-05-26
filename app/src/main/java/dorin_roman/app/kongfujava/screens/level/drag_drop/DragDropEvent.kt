package dorin_roman.app.kongfujava.screens.level.drag_drop


sealed class DragDropEvent {

    class InitAnswers(val levelId: Int) : DragDropEvent()

    class CheckAnswer(val answer: String) : DragDropEvent()
    object GetHint : DragDropEvent()

}