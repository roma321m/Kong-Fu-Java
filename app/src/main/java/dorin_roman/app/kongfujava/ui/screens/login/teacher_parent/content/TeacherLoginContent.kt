package dorin_roman.app.kongfujava.ui.screens.login.teacher_parent.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing
import dorin_roman.app.kongfujava.util.UserType


@Composable
fun TeacherLoginScreenContent(
    navigateToMainScreen: () -> Unit,
    navigateToTeacherRegisterScreen: () -> Unit,
    navigateToParentRegisterScreen: () -> Unit,
    onLoginClicked: () -> Unit,
    userType: Int
) {
    Row(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxWidth()
    ) {
        Spacer(
            modifier = Modifier
                .weight(0.05f)
                .fillMaxHeight()
        )

        Column(
            modifier = Modifier
                .weight(0.35f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TeacherLoginScreenContentImage()

            TeacherLoginScreenContentTexts()
        }

        Spacer(
            modifier = Modifier
                .weight(0.05f)
                .fillMaxHeight()
        )

        Column(
            modifier = Modifier
                .weight(0.35f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TeacherLoginScreenContentTextButton(
                navigateToMainScreen = navigateToMainScreen,
                navigateToTeacherRegisterScreen = navigateToTeacherRegisterScreen,
                navigateToParentRegisterScreen = navigateToParentRegisterScreen,
                onLoginClicked = onLoginClicked,
                userType = userType
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
fun TeacherLoginScreenContentImage() {
    Image(
        modifier = Modifier
            .size(150.dp, 150.dp),
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = stringResource(id = R.string.app_logo)
    )
}

@Composable
fun TeacherLoginScreenContentTexts() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.login_description),
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun TeacherLoginScreenContentTextButton(
    navigateToMainScreen: () -> Unit,
    navigateToTeacherRegisterScreen: () -> Unit,
    navigateToParentRegisterScreen: () -> Unit,
    onLoginClicked: () -> Unit,
    userType: Int
) {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.login).uppercase(),
        style = MaterialTheme.typography.h4
    )

    var email by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = email,
        //leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        trailingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
        onValueChange = {
            email = it
        },
        label = { Text(text = stringResource(id = R.string.register_email)) },
        placeholder = { Text(text = stringResource(id = R.string.register_enter_email)) },
    )

    var password by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = password,
        //leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        trailingIcon = { Icon(imageVector = Icons.Default.Visibility, contentDescription = null) },
        onValueChange = {
            password = it
        },
        label = { Text(text = stringResource(id = R.string.register_password)) },
        placeholder = { Text(text = stringResource(id = R.string.register_enter_password)) },
    )

    Button(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(top = MaterialTheme.spacing.medium),
        onClick = {
            onLoginClicked()
            navigateToMainScreen()
        }
    ) {
        Text(text = stringResource(id = R.string.login).uppercase())
    }

    ClickableText(
        text = AnnotatedString(stringResource(id = R.string.login_create_account)),
        style = TextStyle(
            color = Color.Blue, //Fixme add to MaterialTheme
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .fillMaxWidth(0.6f),
        onClick = {
            if (userType == UserType.Teacher.ordinal) {
                navigateToTeacherRegisterScreen()
            } else if (userType == UserType.Parent.ordinal) {
                navigateToParentRegisterScreen()
            }
        }
    )

    Button(
        modifier = Modifier
            .fillMaxWidth(0.6f),
        onClick = {
            if (userType == UserType.Teacher.ordinal) {
                navigateToTeacherRegisterScreen()
            } else if (userType == UserType.Parent.ordinal) {
                navigateToParentRegisterScreen()
            }
        }
    ) {
        Text(text = stringResource(id = R.string.login_create_account).uppercase())
    }

}