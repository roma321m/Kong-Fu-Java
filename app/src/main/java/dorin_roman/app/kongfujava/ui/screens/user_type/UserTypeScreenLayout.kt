package dorin_roman.app.kongfujava.ui.screens.user_type

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import dorin_roman.app.kongfujava.ui.theme.spacing
import dorin_roman.app.kongfujava.util.UserType


@Composable
fun UserTypeScreenLayout(
    navigateToLoginScreen: (UserType) -> Unit,
    screenHeight: Dp,
    screenWidth: Dp
) {
    Surface {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .width(screenWidth.times(0.4f))
                    .height(screenHeight)
                    .background(MaterialTheme.colors.background)
            ) {
                UserTypeScreenColumn(navigateToLoginScreen = navigateToLoginScreen)
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Box(
                modifier = Modifier
                    .width(screenWidth.times(0.6f))
                    .height(screenHeight)
                    .background(MaterialTheme.colors.primary),
                contentAlignment = Alignment.Center
            ) {
                UserTypeScreenImage()
            }
        }
    }
}