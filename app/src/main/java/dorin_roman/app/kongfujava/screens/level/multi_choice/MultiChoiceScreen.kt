package dorin_roman.app.kongfujava.screens.level.multi_choice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.level.multi_choice.components.MultiChoiceScreenContent
import dorin_roman.app.kongfujava.ui.components.*
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme

@Composable
fun MultiChoiceScreen(
    multiChoiceViewModel: MultiChoiceViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBar(onBackPressed = {}, title = R.string.multi_choice_questions)
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
                    MultiChoiceScreenContent()
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
        MultiChoiceScreen()
    }
}