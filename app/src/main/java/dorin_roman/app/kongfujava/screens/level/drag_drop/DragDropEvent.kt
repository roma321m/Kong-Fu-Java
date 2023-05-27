package dorin_roman.app.kongfujava.screens.level.drag_drop


sealed class DragDropEvent {
    class InitAnswers(val levelId: Int) : DragDropEvent()
    object CheckAnswer : DragDropEvent()
    class SetAnswer(val answer: String, val index: Int) : DragDropEvent()
    class DeleteAnswer(val answer: String, val index: Int) : DragDropEvent()

    class UpdateQuestion(val question: String) : DragDropEvent()
    object GetHint : DragDropEvent()
    class UpdateDragging(val draggingStatus: Boolean) : DragDropEvent()
}