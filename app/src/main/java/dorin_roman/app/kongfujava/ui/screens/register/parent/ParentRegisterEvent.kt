package dorin_roman.app.kongfujava.ui.screens.register.parent


sealed class ParentRegisterEvent {
    class UpdateEmailText(val text: String) : ParentRegisterEvent()
    class UpdatePasswordText(val text: String) : ParentRegisterEvent()
    object Register : ParentRegisterEvent()
    object RegisterResponse : ParentRegisterEvent()
    object SendEmailVerification : ParentRegisterEvent()
    object SendEmailVerificationResponse : ParentRegisterEvent()
    object ReloadUser : ParentRegisterEvent()
    object ReloadUserResponse : ParentRegisterEvent()
}