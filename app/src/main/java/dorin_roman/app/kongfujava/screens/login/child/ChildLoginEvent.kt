package dorin_roman.app.kongfujava.screens.login.child

sealed class ChildLoginEvent {
    class OnCodeChange(val code: String) : ChildLoginEvent()
    class OnNameChange(val name: String) : ChildLoginEvent()
    class OnAgeChange(val age: String) : ChildLoginEvent()
    class OnNextClick(val step: ChildLoginStepState) : ChildLoginEvent()
    object CodeResponse : ChildLoginEvent()
    object SaveUserResponse : ChildLoginEvent()
}
