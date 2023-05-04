package dorin_roman.app.kongfujava.screens.supervisor.add_users.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@Composable
fun SupervisorAddUsersCreateCode(
    modifier: Modifier = Modifier,
    hasActiveCode: Boolean,
    minutes: String,
    seconds: String,
    code: String,
    onCreateCodeClicked: () -> Unit
) {
    if (hasActiveCode) {
        SupervisorAddUsersActiveCode(
            modifier = modifier,
            minutes = minutes,
            seconds = seconds,
            code = code,
        )
    } else {
        SupervisorAddUsersNoActiveCode(
            modifier = modifier,
            onCreateCodeClicked = onCreateCodeClicked
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SupervisorAddUsersCreateCodePreview1() {
    KongFuJavaTheme {
        SupervisorAddUsersCreateCode(
            hasActiveCode = false,
            minutes = "",
            seconds = "",
            code = "",
            onCreateCodeClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SupervisorAddUsersCreateCodePreview2() {
    KongFuJavaTheme {
        SupervisorAddUsersCreateCode(
            hasActiveCode = true,
            minutes = "04",
            seconds = "59",
            code = "AABBCC",
            onCreateCodeClicked = {}
        )
    }
}