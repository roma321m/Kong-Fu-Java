package dorin_roman.app.kongfujava.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun TopBar(onBackPressed: () -> Unit, title: Int, hasBackButton: Boolean = true) {

    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Title(id = title, modifier = Modifier)
        },
        navigationIcon = {
            if (hasBackButton) {
                BackButton(onBackPressed = onBackPressed)
            }
        },
        actions = {
            MenuButton(onClick = { showMenu = !showMenu })
            DropDownMenuAppBar(
                showMenu = showMenu,
                onDismissRequest = { showMenu = false }
            )
        },
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onSecondary,
    )
}

@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        TopBar({}, 0)
    }
}