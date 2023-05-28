package dorin_roman.app.kongfujava.ui.components.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection


@Composable
fun IwEnLayout(content: @Composable () -> Unit) {
    if(LocalLayoutDirection.current == LayoutDirection.Ltr){
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            content()
        }
    }else{
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            content()
        }
    }
}