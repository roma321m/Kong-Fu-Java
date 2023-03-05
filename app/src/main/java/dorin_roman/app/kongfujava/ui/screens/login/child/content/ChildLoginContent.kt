package dorin_roman.app.kongfujava.ui.screens.login.child.content

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
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.purpleColor1
import dorin_roman.app.kongfujava.ui.theme.redColor1
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun ChildLoginScreenContent(
    navigateToMainScreen: () -> Unit,
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
            ChildLoginScreenContentImage()

            ChildLoginScreenContentTexts()
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
            ChildLoginScreenContentTextButton(
                navigateToMainScreen = navigateToMainScreen,
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
fun ChildLoginScreenContentImage() {
    Image(
        modifier = Modifier
            .size(150.dp,150.dp) ,
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = stringResource(id = R.string.app_logo)
    )
}

@Composable
fun ChildLoginScreenContentTexts() {
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
fun ChildLoginScreenContentTextButton(
    navigateToMainScreen: () -> Unit,
) {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.login).uppercase(),
        style = MaterialTheme.typography.h4
    )

    var studentCode by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = studentCode,
        //leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        trailingIcon = { Icon(imageVector = Icons.Default.Visibility, contentDescription = null) },
        onValueChange = {
            studentCode = it
        },
        modifier = Modifier.padding(top = MaterialTheme.spacing.small),
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
        onClick = {
            navigateToMainScreen()
        }
    ) {
        Text(text = stringResource(id = R.string.login).uppercase())
    }

}

