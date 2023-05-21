package dorin_roman.app.kongfujava.screens.login.child

import android.net.Uri

sealed class ChildLoginEvent {
    class OnCodeChange(val code: String) : ChildLoginEvent()
    class OnNameChange(val name: String) : ChildLoginEvent()
    class OnImageChange(val imageUri: Uri) : ChildLoginEvent()
    class OnAgeChange(val age: String) : ChildLoginEvent()
    class OnNextClick(val step: ChildLoginStepState) : ChildLoginEvent()
}
