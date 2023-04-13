package dorin_roman.app.kongfujava.screens.supervisor

import dorin_roman.app.kongfujava.data.models.UserType

sealed class SupervisorEvent {
    class AddUsersSelected(val selected: Boolean) : SupervisorEvent()
    class SelectStudent(val student: StudentModel) : SupervisorEvent()
    class InitData(val userType: UserType) : SupervisorEvent()
    object LogOut : SupervisorEvent()
    object RevokeAccess : SupervisorEvent()
    object RevokeAccessResponse : SupervisorEvent()
    object DeleteUserResponse : SupervisorEvent()
    object LoadUserDataResponse : SupervisorEvent()
}