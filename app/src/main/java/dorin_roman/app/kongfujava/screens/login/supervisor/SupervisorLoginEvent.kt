package dorin_roman.app.kongfujava.screens.login.supervisor

import dorin_roman.app.kongfujava.data.models.UserType


sealed class SupervisorLoginEvent {
    class UpdateEmailText(val text: String) : SupervisorLoginEvent()
    class UpdatePasswordText(val text: String) : SupervisorLoginEvent()
    class UpdateUserTypeLogin(val userType: UserType) : SupervisorLoginEvent()
    object Login : SupervisorLoginEvent()
    object LoginResponse : SupervisorLoginEvent()
}