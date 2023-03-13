package dorin_roman.app.kongfujava.ui.screens.login.child.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.purpleColor1
import dorin_roman.app.kongfujava.ui.theme.redColor1
import dorin_roman.app.kongfujava.ui.theme.spacing
import dorin_roman.app.kongfujava.view_models.ChildLoginContentViewModel


@Composable
fun ChildLoginScreenContent(
    navigateToMainScreen: () -> Unit,
    onLoginClicked: () -> Unit,
    childLoginContentViewModel: ChildLoginContentViewModel
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

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.small),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.login).uppercase(),
                style = MaterialTheme.typography.h4
            )

            ChildLoginScreenStudentAge(
                navigateToMainScreen = navigateToMainScreen,
                onLoginClicked = onLoginClicked,
                childLoginContentViewModel = childLoginContentViewModel
            )

            ChildLoginScreenStudentName(
                childLoginContentViewModel = childLoginContentViewModel
            )

            ChildLoginScreenStudentCode(
                childLoginContentViewModel = childLoginContentViewModel
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
            .size(150.dp, 150.dp),
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
fun ChildLoginScreenStudentCode(
    childLoginContentViewModel: ChildLoginContentViewModel
) {

    var studentCode by remember { mutableStateOf(TextFieldValue("")) }

    AnimatedVisibility(
        visible = childLoginContentViewModel.studentCodeVisible,
        exit = slideOutHorizontally(
            targetOffsetX = { it * 2 },
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = studentCode,
                //leadingIcon = { Icon(imageVector = Icons.Default.Visibility, contentDescription = "emailIcon") },
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
                    childLoginContentViewModel.handle(ChildLoginContentEvent.EnterStudentCode(studentCode.text))
                }
            ) {
                Text(text = stringResource(id = R.string.next).uppercase())
            }
        }
    }

}

@Composable
fun ChildLoginScreenStudentName(
    childLoginContentViewModel: ChildLoginContentViewModel
) {

    var studentName by remember { mutableStateOf(TextFieldValue("")) }

    AnimatedVisibility(
        visible = childLoginContentViewModel.studentNameVisible,
        exit = slideOutHorizontally(
            targetOffsetX = { it * 2 },
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = studentName,
                //leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "emailIcon") },
                trailingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
                onValueChange = {
                    studentName = it
                },
                modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                label = { Text(text = stringResource(id = R.string.login_student_name)) },
                placeholder = { Text(text = stringResource(id = R.string.login_enter_student_name)) },
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
                    childLoginContentViewModel.handle(ChildLoginContentEvent.EnterStudentName(studentName.text))
                }
            ) {
                Text(text = stringResource(id = R.string.next).uppercase())
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChildLoginScreenStudentAge(
    navigateToMainScreen: () -> Unit,
    onLoginClicked: () -> Unit,
    childLoginContentViewModel: ChildLoginContentViewModel
) {
    val listItems = arrayOf("9", "10", "11", "12", "13", "14")

    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }

    // remember the selected item
    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }


    AnimatedVisibility(
        visible = childLoginContentViewModel.studentAgeVisible,
        exit = slideOutHorizontally(
            targetOffsetX = { it * 2 },
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // box
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                // text field
                TextField(
                    value = selectedItem,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(text = stringResource(id = R.string.login_student_age)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    }
                )

                // menu
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    // this is a column scope
                    // all the items are added vertically
                    listItems.forEach { selectedOption ->
                        // menu item
                        DropdownMenuItem(onClick = {
                            selectedItem = selectedOption
                            expanded = false
                        }) {
                            Text(text = selectedOption)
                        }
                    }
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(MaterialTheme.spacing.medium),
                onClick = {
                    childLoginContentViewModel.handle(ChildLoginContentEvent.EnterStudentAge(selectedItem.toInt()))
                    onLoginClicked()
                    navigateToMainScreen()
                }
            ) {
                Text(text = stringResource(id = R.string.login).uppercase())
            }
        }
    }

}

@Preview
@Composable
fun ChildLoginScreenContentPreview() {
    ChildLoginScreenContent(navigateToMainScreen = {}, onLoginClicked = {}, childLoginContentViewModel = viewModel())
}

