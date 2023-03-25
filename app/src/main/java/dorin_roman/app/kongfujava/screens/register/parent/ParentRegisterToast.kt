package dorin_roman.app.kongfujava.screens.register.parent

import android.widget.Toast
import androidx.annotation.StringRes
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.toast.IToast

enum class ParentRegisterToast(
    @StringRes override val text: Int,
    override val duration: Int
) : IToast {
    VerificationEmailSent(
        text = R.string.verification_email_sent,
        duration = Toast.LENGTH_LONG
    ),
    EmailVerified(
        text = R.string.email_verified,
        duration = Toast.LENGTH_LONG
    ),
    VerifyYourEmail(
        text = R.string.verify_email,
        duration = Toast.LENGTH_LONG
    ),
    SomethingWentWrong(
        text = R.string.something_went_wrong,
        duration = Toast.LENGTH_LONG
    )
}