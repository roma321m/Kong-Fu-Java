package dorin_roman.app.kongfujava.provider

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import dorin_roman.app.kongfujava.MainActivity
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.provider.NotificationChannelProvider.NOTIFICATION_CHANNEL_ID
import dorin_roman.app.kongfujava.service.CodeService
import dorin_roman.app.kongfujava.service.CodeState

class NotificationProvider(
    private val context: Context
) {

    companion object {
        const val TAG = "NotificationProvider"

        const val NOTIFICATION_ID = 10

        const val EXTRA_STATE = "kong.fu.java.extra.state"
        const val CLICK_REQUEST_CODE = 100
        const val DISMISS_REQUEST_CODE = 101
    }

    private val flag = PendingIntent.FLAG_IMMUTABLE

    fun provide() : NotificationCompat.Builder {
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Code: AABBCC")
            .setContentText("05:00")
            .setSmallIcon(R.drawable.ic_logo)
            .addAction(0, context.getString(R.string.dismiss), dismissPendingIntent())
            .setContentIntent(clickPendingIntent())
    }

    private fun clickPendingIntent(): PendingIntent {
        val clickIntent = Intent(context, MainActivity::class.java).apply {
            putExtra(EXTRA_STATE, CodeState.Started.name)
        }
        return PendingIntent.getActivity(
            context, CLICK_REQUEST_CODE, clickIntent, flag
        )
    }

    private fun dismissPendingIntent(): PendingIntent {
        val cancelIntent = Intent(context, CodeService::class.java).apply {
            putExtra(EXTRA_STATE, CodeState.Dismiss.name)
        }
        return PendingIntent.getService(
            context, DISMISS_REQUEST_CODE, cancelIntent, flag
        )
    }

}