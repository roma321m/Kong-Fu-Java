package dorin_roman.app.kongfujava.screens.login.child

import android.widget.Toast
import androidx.annotation.StringRes
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.toast.IToast

enum class ChildToast(
    @StringRes override val text: Int,
    override val duration: Int
) : IToast {
    SomethingWentWrong(
        text = R.string.child_login_something_went_wrong,
        duration = Toast.LENGTH_LONG
    ),
    CodeNotValid(
        text = R.string.child_login_code_not_valid,
        duration = Toast.LENGTH_SHORT
    ),
    CodeExpired(
        text = R.string.child_login_code_expired,
        duration = Toast.LENGTH_SHORT
    ),
    FillYourName(
        text = R.string.child_login_fill_name,
        duration = Toast.LENGTH_SHORT
    ),
    FillYourAge(
        text = R.string.child_login_select_age,
        duration = Toast.LENGTH_SHORT
    ),
    AddAnImage(
        text = R.string.child_login_add_image,
        duration = Toast.LENGTH_SHORT
    )
}