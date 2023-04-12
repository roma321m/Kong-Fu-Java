package dorin_roman.app.kongfujava.screens.supervisor

sealed class SupervisorEvent {
    class AddUsersSelected(val selected: Boolean) : SupervisorEvent()
    class SelectStudent(val student: StudentModel) : SupervisorEvent()
    object InitSelectedStudent : SupervisorEvent()
}