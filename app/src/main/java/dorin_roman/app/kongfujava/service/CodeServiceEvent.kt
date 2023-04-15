package dorin_roman.app.kongfujava.service

sealed class CodeServiceEvent {
    object CreateChannel : CodeServiceEvent()
    object CancelNotification : CodeServiceEvent()
    object StartTimer : CodeServiceEvent()
    object DismissTimer : CodeServiceEvent()
}