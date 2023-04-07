package dorin_roman.app.kongfujava.screens.level.tutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.level.tutorial.components.TutorialScreenContent
import dorin_roman.app.kongfujava.ui.components.*
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun TutorialScreen(
    tutorialViewModel: TutorialViewModel = hiltViewModel()
) {
    // fixme - temp
    Scaffold(
        topBar = {
            TopBar(onBackPressed = {}, title = R.string.tutorial_questions)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(MaterialTheme.colors.secondary)
                .padding(padding)
        ) {
            VerticalFortySixtyLayout(
                fortyLayout = {
                    TutorialScreenContent()
                },
                sixtyLayout = {
                    SideScreenImage(R.drawable.ic_panda_question)
                }
            )
        }
    }
}

@DevicePreviews
@Composable
fun WorldsScreenPreview() {
    KongFuJavaTheme {
        TutorialScreen()
    }
}