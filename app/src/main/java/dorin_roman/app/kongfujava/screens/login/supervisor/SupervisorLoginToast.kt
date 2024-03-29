package dorin_roman.app.kongfujava.screens.login.supervisor

import android.widget.Toast
import androidx.annotation.StringRes
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.toast.IToast


enum class SupervisorLoginToast(
    @StringRes override val text: Int,
    override val duration: Int
) : IToast {
    LoginSuccessfully(
        text = R.string.supervisor_login_successfully,
        duration = Toast.LENGTH_LONG
    ),
    SomethingWentWrong(
        text = R.string.supervisor_login_something_went_wrong,
        duration = Toast.LENGTH_LONG
    )
}