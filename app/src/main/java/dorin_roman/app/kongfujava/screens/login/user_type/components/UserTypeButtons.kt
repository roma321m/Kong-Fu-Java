package dorin_roman.app.kongfujava.screens.login.user_type.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EscalatorWarning
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.UserType
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun UserTypeButtons(
    navigateToLoginScreen: (UserType) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.large)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(MaterialTheme.spacing.medium),
            onClick = {
                navigateToLoginScreen(UserType.Child)
            }
        ) {
            Icon(
                imageVector = Icons.Default.School,
                contentDescription = stringResource(R.string.user_type_student),
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.large))
            Text(text = stringResource(id = R.string.user_type_im_student))
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(MaterialTheme.spacing.medium),
            onClick = {
                navigateToLoginScreen(UserType.Parent)
            }
        ) {
            Icon(
                imageVector = Icons.Default.FamilyRestroom,
                contentDescription = stringResource(R.string.user_type_parent)
            )
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.large))
            Text(text = stringResource(id = R.string.user_type_im_parent))
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(MaterialTheme.spacing.medium),
            onClick = {
                navigateToLoginScreen(UserType.Teacher)
            }
        ) {
            Icon(
                imageVector = Icons.Default.EscalatorWarning,
                contentDescription = stringResource(R.string.user_type_teacher)
            )
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.large))
            Text(text = stringResource(id = R.string.user_type_im_teacher))
        }
    }
}