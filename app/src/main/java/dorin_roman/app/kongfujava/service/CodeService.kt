package dorin_roman.app.kongfujava.service


import android.app.Service
import android.content.Intent
import android.os.Binder
import android.util.Log
import dorin_roman.app.kongfujava.provider.NotificationProvider.Companion.EXTRA_STATE
import dorin_roman.app.kongfujava.provider.NotificationProvider.Companion.NOTIFICATION_ID
import javax.inject.Inject

class CodeService : Service() {

    companion object {
        const val TAG = "CodeService"

        const val ACTION_SERVICE_DISMISS = "ACTION_SERVICE_DISMISS"
    }

    @Inject
    lateinit var codeViewModel: CodeViewModel

    private val binder = CodeBinder()

    override fun onBind(intent: Intent?) = binder

    inner class CodeBinder : Binder() {
        fun getService(): CodeService = this@CodeService
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")

        when (intent?.getStringExtra(EXTRA_STATE)) {
            CodeState.Started.name -> {
                startForegroundService()
                codeViewModel.handle(CodeServiceEvent.StartTimer)
            }

            CodeState.Dismiss.name -> {
                codeViewModel.handle(CodeServiceEvent.DismissTimer)
                stopForegroundService()
            }
        }
        intent?.action?.let {
            if (it == ACTION_SERVICE_DISMISS) {
                codeViewModel.handle(CodeServiceEvent.DismissTimer)
                stopForegroundService()
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForegroundService() {
        codeViewModel.handle(CodeServiceEvent.CreateChannel)
        startForeground(NOTIFICATION_ID, codeViewModel.notification)
    }

    private fun stopForegroundService() {
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

}