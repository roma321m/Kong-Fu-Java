package dorin_roman.app.kongfujava.screens.supervisor

import android.net.Uri
import dorin_roman.app.kongfujava.data.models.UserType

sealed class SupervisorEvent {
    class AddUsersSelected(val selected: Boolean) : SupervisorEvent()
    class SelectStudent(val student: StudentModel) : SupervisorEvent()
    class ImageSelected(val image: Uri) : SupervisorEvent()
    class InitData(val userType: UserType) : SupervisorEvent()
    object LogOut : SupervisorEvent()
    object RevokeAccess : SupervisorEvent()
    object RefreshChildrenList : SupervisorEvent()
}