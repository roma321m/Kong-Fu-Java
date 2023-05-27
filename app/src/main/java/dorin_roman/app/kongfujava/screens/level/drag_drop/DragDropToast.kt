package dorin_roman.app.kongfujava.screens.level.drag_drop

import android.widget.Toast
import androidx.annotation.StringRes
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.toast.IToast

enum class DragDropToast(
    @StringRes override val text: Int,
    override val duration: Int
) : IToast {
    AnswerExits(
        text = R.string.drag_drop_answer_exists,
        duration = Toast.LENGTH_LONG
    )
}