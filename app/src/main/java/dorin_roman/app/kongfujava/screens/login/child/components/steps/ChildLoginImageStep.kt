package dorin_roman.app.kongfujava.screens.login.child.components.steps

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.kongFuRed
import dorin_roman.app.kongfujava.ui.theme.purpleColor1
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun ChildLoginImageStep(
    visibility: Boolean,
    onImageChange: (image: Uri) -> Unit,
    onNextClicked: () -> Unit
) {
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
            imageUri?.let {
                onImageChange(it)
            }
        }

    AnimatedVisibility(
        visible = visibility,
        exit = slideOutHorizontally(
            targetOffsetX = { it * 2 },
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearEasing
            )
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxWidth(0.5f)
                    .clickable {
                        galleryLauncher.launch("image/*")
                    },
                value = stringResource(R.string.child_login_picture),
                enabled = false,
                onValueChange = {},
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.AddAPhoto,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.child_login_add_a_picture))
                },
                placeholder = {
                    Text(stringResource(R.string.child_login_add_a_picture))
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = purpleColor1,
                    focusedLabelColor = purpleColor1,
                    errorLabelColor = kongFuRed,
                    errorBorderColor = kongFuRed,
                    focusedBorderColor = purpleColor1,
                    unfocusedBorderColor = purpleColor1
                )
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(MaterialTheme.spacing.medium),
                onClick = {
                    onNextClicked()
                }
            ) {
                Text(stringResource(R.string.child_login_title))
            }
        }
    }
}