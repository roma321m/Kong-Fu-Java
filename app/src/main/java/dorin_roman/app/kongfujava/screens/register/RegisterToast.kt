package dorin_roman.app.kongfujava.screens.register

import android.widget.Toast
import androidx.annotation.StringRes
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.toast.IToast

enum class RegisterToast(
    @StringRes override val text: Int,
    override val duration: Int
) : IToast {
    VerificationEmailSent(
        text = R.string.register_verification_email,
        duration = Toast.LENGTH_LONG
    ),
    EmailVerified(
        text = R.string.register_email_verified,
        duration = Toast.LENGTH_LONG
    ),
    VerifyYourEmail(
        text = R.string.register_verify_email,
        duration = Toast.LENGTH_LONG
    ),
    SomethingWentWrong(
        text = R.string.register_something_went_wrong,
        duration = Toast.LENGTH_LONG
    )
}