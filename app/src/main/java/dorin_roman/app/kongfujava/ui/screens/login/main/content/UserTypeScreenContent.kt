package dorin_roman.app.kongfujava.ui.screens.login.main.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing
import dorin_roman.app.kongfujava.util.UserType

@Composable
fun UserTypeScreenContent(navigateToLoginScreen: (UserType) -> Unit) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxHeight()
    ) {
        Spacer(
            modifier = Modifier
                .weight(0.05f)
                .fillMaxWidth()
        )

        Box(modifier = Modifier.weight(0.2f)) {
            UserTypeScreenContentImage()
        }

        Column(
            modifier = Modifier
                .weight(0.35f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserTypeScreenContentTexts()
        }

        Column(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserTypeScreenContentButtons(
                navigateToLoginScreen = navigateToLoginScreen
            )
        }

        Spacer(
            modifier = Modifier
                .weight(0.05f)
                .fillMaxWidth()
        )
    }
}

@Composable
fun UserTypeScreenContentImage() {
    Image(
        modifier = Modifier
            .fillMaxSize(),
        painter = painterResource(id = R.drawable.ic_logo), // Fixme - change the image
        contentDescription = "app logo" // Fixme - change to string rss
    )
}

@Composable
fun UserTypeScreenContentTexts() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        textAlign = TextAlign.Center,
        text = "Welcome!", // Fixme - change to string rss
        style = MaterialTheme.typography.h4
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        textAlign = TextAlign.Center,
        text = "description 1", // Fixme - change to string rss
        style = MaterialTheme.typography.h6
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        textAlign = TextAlign.Center,
        text = "description 2", // Fixme - change to string rss
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun UserTypeScreenContentButtons(
    navigateToLoginScreen: (UserType) -> Unit
) {
    // Fixme- add icons to the buttons + change color to secondary

    Button(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(MaterialTheme.spacing.medium),
        onClick = {
            navigateToLoginScreen(UserType.Parent)
        }
    ) {
        Text(text = "I'm a parent") // Fixme - change to string rss
    }

    Button(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(MaterialTheme.spacing.medium),
        onClick = {
            navigateToLoginScreen(UserType.Teacher)
        }
    ) {
        Text(text = "I'm a student") // Fixme - change to string rss
    }

    Button(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(MaterialTheme.spacing.medium),
        onClick = {
            navigateToLoginScreen(UserType.Child)
        }
    ) {
        Text(text = "I'm a teacher") // Fixme - change to string rss
    }
}