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
        text = R.string.log_out_successfully,
        duration = Toast.LENGTH_LONG
    ),
    RevokeAccess(
        text = R.string.users_where_deleted_successfully,
        duration = Toast.LENGTH_LONG
    ),
    SomethingWentWrong(
        text = R.string.something_went_wrong,
        duration = Toast.LENGTH_LONG
    )
}