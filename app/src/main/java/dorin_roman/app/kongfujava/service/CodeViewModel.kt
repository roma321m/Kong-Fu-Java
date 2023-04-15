package dorin_roman.app.kongfujava.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dorin_roman.app.kongfujava.provider.NotificationProvider.Companion.NOTIFICATION_ID
import dorin_roman.app.kongfujava.util.formatTime
import dorin_roman.app.kongfujava.util.pad
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class CodeViewModel @Inject constructor(
    private val notificationManager: NotificationManager,
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationChannel: NotificationChannel?
) : ViewModel() {

    companion object {
        const val TAG = "CodeViewModel"
    }

    private var duration: Duration = 5.minutes
    private lateinit var timer: Timer

    var seconds = mutableStateOf("00")
        private set

    var minutes = mutableStateOf("00")
        private set

    var currentState = mutableStateOf(CodeState.Idle)
        private set

    val notification = notificationBuilder.build()

    fun handle(event: CodeServiceEvent) {
        when (event) {
            CodeServiceEvent.CreateChannel -> createNotificationChannel()
            CodeServiceEvent.CancelNotification -> cancelNotification()
            CodeServiceEvent.DismissTimer -> startTimer { minutes, seconds ->
                updateNotification(minutes, seconds)
            }

            CodeServiceEvent.StartTimer -> dismissTimer()
        }
    }

    private fun createNotificationChannel() {
        Log.d(TAG, "createNotificationChannel")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel?.let { channel ->
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    private fun cancelNotification() {
        Log.d(TAG, "cancelNotification")
        notificationManager.cancel(NOTIFICATION_ID)
    }

    private fun updateNotification(minutes: String, seconds: String) {
        Log.d(TAG, "updateNotification")
        notificationManager.notify(
            NOTIFICATION_ID,
            notificationBuilder.setContentText(
                formatTime(
                    minutes = minutes,
                    seconds = seconds,
                )
            ).build()
        )
    }

    private fun startTimer(onTick: (m: String, s: String) -> Unit) {
        Log.d(TAG, "startTimer")
        currentState.value = CodeState.Started
        timer = fixedRateTimer(initialDelay = 1000L, period = 1000L) {
            duration = duration.minus(1.seconds)
            updateTimeUnits()
            onTick(minutes.value, seconds.value)
        }
    }

    private fun dismissTimer() {
        Log.d(TAG, "dismissTimer")
        if (this::timer.isInitialized) {
            timer.cancel()
        }
        duration = 5.minutes
        currentState.value = CodeState.Idle
        updateTimeUnits()
    }

    private fun updateTimeUnits() {
        Log.d(TAG, "updateTimeUnits")
        duration.toComponents { _, m, s, _ ->
            minutes.value = m.pad()
            seconds.value = s.pad()
        }
    }

}