package dorin_roman.app.kongfujava.ui.screens.login.main

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.components.SideScreenImage
import dorin_roman.app.kongfujava.ui.components.VerticalFortySixtyLayout
import dorin_roman.app.kongfujava.ui.screens.login.main.content.UserTypeScreenContent
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme
import dorin_roman.app.kongfujava.util.UserType

@Composable
fun UserTypeScreen(
    navigateToLoginScreen: (UserType) -> Unit
) {
    VerticalFortySixtyLayout(
        fortyLayout = {
            UserTypeScreenContent(
                navigateToLoginScreen = navigateToLoginScreen
            )
        },
        sixtyLayout = {
            SideScreenImage(R.drawable.ic_panda_usertype)
        }
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