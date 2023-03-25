package dorin_roman.app.kongfujava.ui.toast

import android.content.Context
import android.util.Log
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class ToastLauncher @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        const val TAG = "ToastLauncher"
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun launch(toast: IToast) {
        Log.i(TAG, "launch toast")
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(context, toast.text, toast.duration).show()
        }
    }
}