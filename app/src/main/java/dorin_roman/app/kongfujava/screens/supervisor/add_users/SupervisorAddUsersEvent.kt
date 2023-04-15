package dorin_roman.app.kongfujava.screens.supervisor.add_users

sealed class SupervisorAddUsersEvent {
    class CanShowNotification(val state: Boolean) : SupervisorAddUsersEvent()
}