package dorin_roman.app.kongfujava.screens.worlds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.RequestState
import dorin_roman.app.kongfujava.domain.models.World
import dorin_roman.app.kongfujava.screens.worlds.components.WorldsMapContent
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.components.TopBar
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun WorldsMapScreen(
    navigateToLevel: () -> Unit,
    worldsMapViewModel: WorldsMapViewModel = hiltViewModel()
) {
    val worlds by worldsMapViewModel.worlds.collectAsState()

    Scaffold(
        topBar = {
            TopBar(onBackPressed = {}, title = R.string.worlds_map)
        },
        content = { paddingValues ->
            if (worlds is RequestState.Success) {
                WorldsMapContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.secondary)
                        .padding(paddingValues),
                    worlds = (worlds as RequestState.Success<List<World>>).data,
                    navigateToLevel = navigateToLevel
                )
            }
        })
}


@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        WorldsMapScreen({})
    }
}