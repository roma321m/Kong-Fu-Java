package dorin_roman.app.kongfujava.ui.components

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "NEXUS 9 Dark Mode",
    device = Devices.NEXUS_9,
    locale = "en",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "NEXUS 9 Light Mode",
    device = Devices.NEXUS_9,
    locale = "en",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "NEXUS 9 Dark Mode - rtl",
    device = Devices.NEXUS_9,
    locale = "he",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "NEXUS 9 Light Mode - rtl",
    device = Devices.NEXUS_9,
    locale = "he",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
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
annotation class DevicePreviews
