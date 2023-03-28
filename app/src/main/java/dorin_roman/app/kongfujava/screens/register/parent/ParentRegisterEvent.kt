package dorin_roman.app.kongfujava.screens.register.parent


sealed class ParentRegisterEvent {
    object ReloadUser : ParentRegisterEvent()
    object ReloadUserResponse : ParentRegisterEvent()
}