package dorin_roman.app.kongfujava.ui.screens.login.child.content

sealed class ChildLoginContentEvent {
    class EnterStudentCode(val code: String) : ChildLoginContentEvent()
    class EnterStudentName(val name: String) : ChildLoginContentEvent()
    class EnterStudentAge(val age: Int) : ChildLoginContentEvent()
}
