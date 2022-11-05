package dorin_roman.app.kongfujava.ui.screens.user_type

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import dorin_roman.app.kongfujava.util.UserType

@Composable
fun UserTypeScreen(
    navigateToLoginScreen: (UserType) -> Unit
) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    UserTypeScreenLayout(
        navigateToLoginScreen = navigateToLoginScreen,
        screenHeight = screenHeight,
        screenWidth = screenWidth
    )
}

@Preview(
    name = "NEXUS 9 Dark Mode",
    device = Devices.NEXUS_9,
    locale = "en",
    showSystemUi = true,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "NEXUS 9 Light Mode",
    device = Devices.NEXUS_9,
    locale = "en",
    showSystemUi = true,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "NEXUS 9 Dark Mode - rtl",
    device = Devices.NEXUS_9,
    locale = "he",
    showSystemUi = true,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "NEXUS 9 Light Mode - rtl",
    device = Devices.NEXUS_9,
    locale = "he",
    showSystemUi = true,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "NEXUS 10",
    device = Devices.NEXUS_10,
    locale = "en",
    showSystemUi = true,
    showBackground = true
)
@Preview(
    name = "PIXEL C",
    device = Devices.PIXEL_C,
    locale = "en",
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun UserTypeScreenPreview() {
    KongFuJavaTheme {
        UserTypeScreen { }
    }
}