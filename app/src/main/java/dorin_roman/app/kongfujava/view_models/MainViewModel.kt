package dorin_roman.app.kongfujava.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.util.UserType
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    var type by mutableStateOf(UserType.Teacher)
        private set

    fun handle(event: MainEvent) {
        when (event) {
            MainEvent.Child -> type = UserType.Child // FIXME - handle login and set the type
            MainEvent.Teacher -> type = UserType.Teacher // FIXME - handle login and set the type
            MainEvent.Parent -> type = UserType.Parent // FIXME - handle login and set the type
        }
    }

}