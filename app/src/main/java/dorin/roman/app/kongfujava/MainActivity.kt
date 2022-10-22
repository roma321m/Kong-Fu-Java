package dorin.roman.app.kongfujava

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dorin.roman.app.kongfujava.ui.theme.KongFuJavaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KongFuJavaTheme {
                // TODO - Start of the application
            }
        }
    }
}