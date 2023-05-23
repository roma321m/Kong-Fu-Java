package dorin_roman.app.kongfujava.screens.supervisor

import android.widget.Toast
import androidx.annotation.StringRes
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.toast.IToast


enum class SupervisorToast(
    @StringRes override val text: Int,
    override val duration: Int
) : IToast {
    LogOut(
        text = R.string.supervisor_log_out_successfully,
        duration = Toast.LENGTH_LONG
    ),
    RevokeAccess(
        text = R.string.supervisor_users_deleted_successfully,
        duration = Toast.LENGTH_LONG
    ),
    SomethingWentWrong(
        text = R.string.supervisor_something_went_wrong,
        duration = Toast.LENGTH_LONG
    )
}