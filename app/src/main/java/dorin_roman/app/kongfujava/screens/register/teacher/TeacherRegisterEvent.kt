package dorin_roman.app.kongfujava.screens.register.teacher


sealed class TeacherRegisterEvent {
    object ReloadUser : TeacherRegisterEvent()
    object ReloadUserResponse : TeacherRegisterEvent()
    object SaveUserToDatabaseResponse : TeacherRegisterEvent()
    class UpdateClassText(val text: String) : TeacherRegisterEvent()
    class UpdateSchoolText(val text: String) : TeacherRegisterEvent()
}