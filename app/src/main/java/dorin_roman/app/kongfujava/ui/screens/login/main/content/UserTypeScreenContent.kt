package dorin_roman.app.kongfujava.ui.screens.login.main.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EscalatorWarning
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.purpleColor1
import dorin_roman.app.kongfujava.ui.theme.secondaryTextColor
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
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = stringResource(id = R.string.app_logo)
    )
}

@Composable
fun UserTypeScreenContentTexts() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.welcome),
        style = MaterialTheme.typography.h4
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.user_type_subtitle_description).uppercase(),
        style = MaterialTheme.typography.h6
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.user_type_description),
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun UserTypeScreenContentButtons(
    navigateToLoginScreen: (UserType) -> Unit
) {

    Button(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(MaterialTheme.spacing.medium),
        colors = ButtonDefaults.buttonColors( backgroundColor = purpleColor1, contentColor = secondaryTextColor),
        onClick = {
            navigateToLoginScreen(UserType.Child)
        }
    ) {
        Icon(imageVector = Icons.Default.School,
            contentDescription = "student")
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = stringResource(id = R.string.user_student))
    }

    Button(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(MaterialTheme.spacing.medium),
        colors = ButtonDefaults.buttonColors( backgroundColor = purpleColor1, contentColor = secondaryTextColor),
        onClick = {
            navigateToLoginScreen(UserType.Parent)
        }
    ) {
        Icon(imageVector = Icons.Default.FamilyRestroom,
            contentDescription = "parent")
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = stringResource(id = R.string.user_parent))
    }

    Button(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(MaterialTheme.spacing.medium),
        colors = ButtonDefaults.buttonColors( backgroundColor = purpleColor1, contentColor = secondaryTextColor),
        onClick = {
            navigateToLoginScreen(UserType.Teacher)
        }
    ) {
        Icon(imageVector = Icons.Default.EscalatorWarning,
            contentDescription = "teacher")
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = stringResource(id = R.string.user_teacher))
    }
}