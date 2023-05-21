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
        text = R.string.something_went_wrong,
        duration = Toast.LENGTH_LONG
    ),
    CodeNotValid(
        text = R.string.code_not_valid,
        duration = Toast.LENGTH_SHORT
    ),
    CodeExpired(
        text = R.string.code_expired,
        duration = Toast.LENGTH_SHORT
    ),
    FillYourName(
        text = R.string.you_need_to_fill_in_your_name,
        duration = Toast.LENGTH_SHORT
    ),
    FillYourAge(
        text = R.string.you_need_to_select_your_age,
        duration = Toast.LENGTH_SHORT
    ),
    AddAnImage(
        text = R.string.you_need_to_add_an_image,
        duration = Toast.LENGTH_SHORT
    )
}