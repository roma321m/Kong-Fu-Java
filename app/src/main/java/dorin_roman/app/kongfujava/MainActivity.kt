package dorin_roman.app.kongfujava

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dorin_roman.app.kongfujava.service.CodeService
import dorin_roman.app.kongfujava.ui.components.SystemUi
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var codeService: CodeService
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            Log.d(TAG, "onServiceConnected")
            val binder = service as CodeService.CodeBinder
            codeService = binder.getService()
            mainViewModel.handle(MainEvent.IsBindCodeService(true))
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            Log.d(TAG, "onServiceDisconnected")
            mainViewModel.handle(MainEvent.IsBindCodeService(false))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)

        setContent {
            KongFuJavaTheme {
                SystemUi()

                if (mainViewModel.isBoundCodeService) {
                    MainScreen(
                        mainViewModel = mainViewModel,
                        codeService = codeService
                    )
                }
            }
        }
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
        Intent(this, CodeService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
        unbindService(connection)
        mainViewModel.handle(MainEvent.IsBindCodeService(false))
    }
}