package dorin_roman.app.kongfujava.ui.components.topbar

import android.widget.Toast
import androidx.annotation.StringRes
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.toast.IToast


enum class TopBarToast(
    @StringRes override val text: Int,
    override val duration: Int
) : IToast {
    Thanks(
        text = R.string.top_bar_thanks,
        duration = Toast.LENGTH_LONG
    )
}