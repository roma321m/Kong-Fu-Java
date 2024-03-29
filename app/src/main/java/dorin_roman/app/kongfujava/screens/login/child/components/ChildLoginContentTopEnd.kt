package dorin_roman.app.kongfujava.screens.login.child.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.screens.login.child.ChildLoginStepState
import dorin_roman.app.kongfujava.screens.login.child.ChildLoginStepState.AGE
import dorin_roman.app.kongfujava.screens.login.child.ChildLoginStepState.FINAL
import dorin_roman.app.kongfujava.screens.login.child.ChildLoginStepState.IMAGE
import dorin_roman.app.kongfujava.screens.login.child.ChildLoginStepState.NAME
import dorin_roman.app.kongfujava.screens.login.child.components.steps.ChildLoginAgeStep
import dorin_roman.app.kongfujava.screens.login.child.components.steps.ChildLoginCodeStep
import dorin_roman.app.kongfujava.screens.login.child.components.steps.ChildLoginImageStep
import dorin_roman.app.kongfujava.screens.login.child.components.steps.ChildLoginNameStep
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun ChildLoginContentTopEnd(
    codeVisibility: () -> Boolean,
    nameVisibility: () -> Boolean,
    ageVisibility: () -> Boolean,
    imageVisibility: () -> Boolean,
    textCode: String,
    name: String,
    age: String,
    onTextCodeChange: (code: String) -> Unit,
    onNameChange: (name: String) -> Unit,
    onAgeChange: (age: String) -> Unit,
    onImageChange: (image: Uri) -> Unit,
    onNextClicked: (step: ChildLoginStepState) -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.large)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.child_login_title),
            style = MaterialTheme.typography.h4,
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        Box(
            modifier = Modifier
                .padding(bottom = MaterialTheme.spacing.large),
            contentAlignment = Alignment.BottomCenter
        ) {
            ChildLoginCodeStep(
                visibility = codeVisibility(),
                textCode = textCode,
                onNextClicked = {
                    onNextClicked(NAME)
                },
                onTextCodeChange = { newTextCode ->
                    onTextCodeChange(newTextCode)
                },
            )

            ChildLoginNameStep(
                visibility = nameVisibility(),
                name = name,
                onNextClicked = {
                    onNextClicked(AGE)
                },
                onNameChange = { newName ->
                    onNameChange(newName)
                },
            )

            ChildLoginAgeStep(
                visibility = ageVisibility(),
                age = age,
                onNextClicked = {
                    onNextClicked(IMAGE)
                },
                onAgeChange = { newAge ->
                    onAgeChange(newAge)
                },
            )

            ChildLoginImageStep(
                visibility = imageVisibility(),
                onNextClicked = {
                    onNextClicked(FINAL)
                },
                onImageChange = { image ->
                    onImageChange(image)
                },
            )
        }
    }
}
