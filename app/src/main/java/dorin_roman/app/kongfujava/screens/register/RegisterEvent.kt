package dorin_roman.app.kongfujava.screens.register


sealed class RegisterEvent {
    class UpdateEmailText(val text: String) : RegisterEvent()
    class UpdatePasswordText(val text: String) : RegisterEvent()
    object Register : RegisterEvent()
    object RegisterResponse : RegisterEvent()
    object SendEmailVerification : RegisterEvent()
    object SendEmailVerificationResponse : RegisterEvent()
}