package dorin_roman.app.kongfujava.screens.login.child.components.steps

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.ui.theme.spacing


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChildLoginAgeStep(
    visibility: Boolean,
    age: String,
    onAgeChange: (age: String) -> Unit,
    onNextClicked: () -> Unit
) {
    val ages = arrayOf("9", "10", "11", "12", "13", "14")

    var expanded by remember {
        mutableStateOf(false)
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

            ExposedDropdownMenuBox(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxWidth(0.5f),
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = age,
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text(stringResource(R.string.login_student_age))
                    },
                    placeholder = {
                        Text(stringResource(R.string.login_student_age))
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    ages.forEach { age ->
                        DropdownMenuItem(
                            onClick = {
                                onAgeChange(age)
                                expanded = false
                            }
                        ) {
                            Text(text = age)
                        }
                    }
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(MaterialTheme.spacing.medium),
                onClick = {
                    onNextClicked()
                }
            ) {
                Text(stringResource(R.string.login))
            }
        }
    }

}