package dorin_roman.app.kongfujava.screens.supervisor.components.drawer

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.ui.components.image.RoundedImage
import dorin_roman.app.kongfujava.ui.theme.spacing


@Composable
fun SupervisorDrawerHeader(
    imageUrl: String,
    email: String,
    className: String?,
    schoolName: String?,
    onImageSelected: (Uri) -> Unit,
    modifier: Modifier = Modifier
) {
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
            imageUri?.let {
                onImageSelected(it)
            }
        }

    Column(
        modifier = modifier
            .fillMaxWidth(0.3f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundedImage(
            modifier = Modifier
                .height(150.dp),
            imageUrl = imageUrl,
            onClick = {
                galleryLauncher.launch("image/*")
            }
        )
        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium),
            text = email,
            style = MaterialTheme.typography.subtitle1
        )
        if (!schoolName.isNullOrBlank() && !className.isNullOrBlank()) {
            Text(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium),
                text = "$schoolName $className",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}