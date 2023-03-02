package dorin_roman.app.kongfujava.ui.screens.login.teacher.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.purpleColor1
import dorin_roman.app.kongfujava.ui.theme.redColor1
import dorin_roman.app.kongfujava.ui.theme.secondaryTextColor
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun TeacherLoginScreenContent(
    navigateToMainScreen: () -> Unit,
    navigateToTeacherRegisterScreen: () -> Unit,
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

//        Box(modifier = Modifier.weight(0.2f)) {
//            TeacherLoginScreenContentImage()
//        }

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
                navigateToTeacherRegisterScreen = navigateToTeacherRegisterScreen
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
            .fillMaxSize(),
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
) {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.login).uppercase(),
        style = MaterialTheme.typography.h4
    )

    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        //leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        trailingIcon = { Icon(imageVector = Icons.Default.Visibility, contentDescription = null) },
        onValueChange = {
            text = it
        },
        label = { Text(text = stringResource(id = R.string.login_student_code)) },
        placeholder = { Text(text = stringResource(id = R.string.login_enter_student_code)) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = purpleColor1,
            focusedLabelColor = purpleColor1,
            errorLabelColor = redColor1,
            errorBorderColor = redColor1,
            focusedBorderColor = purpleColor1,
            unfocusedBorderColor = purpleColor1
        )
    )

    Button(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(MaterialTheme.spacing.medium),
        colors = ButtonDefaults.buttonColors(backgroundColor = purpleColor1, contentColor = secondaryTextColor),
        onClick = {
            navigateToMainScreen()
        }
    ) {
        Text(text = stringResource(id = R.string.login).uppercase())
    }

    // FIXME - temp button
    Button(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(MaterialTheme.spacing.medium),
        colors = ButtonDefaults.buttonColors(backgroundColor = purpleColor1, contentColor = secondaryTextColor),
        onClick = {
            navigateToTeacherRegisterScreen()
        }
    ) {
        Text(text = "temp - teacher register".uppercase())
    }

}