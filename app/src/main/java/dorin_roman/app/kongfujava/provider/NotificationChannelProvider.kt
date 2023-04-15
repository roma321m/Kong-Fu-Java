package dorin_roman.app.kongfujava.provider

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

object NotificationChannelProvider {

    const val TAG = "NotificationChannelProvider"

    const val NOTIFICATION_CHANNEL_ID = "KONG_FU_JAVA_CHANNEL_ID"
    const val NOTIFICATION_CHANNEL_NAME = "CODE_NOTIFICATION"

    fun provide(): NotificationChannel? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            )
        }
        return null
    }
}