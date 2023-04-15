package dorin_roman.app.kongfujava.screens.supervisor.add_users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import dorin_roman.app.kongfujava.screens.supervisor.add_users.components.SupervisorAddUsersCreateCode
import dorin_roman.app.kongfujava.screens.supervisor.add_users.components.SupervisorAddUsersImage
import dorin_roman.app.kongfujava.screens.supervisor.add_users.components.SupervisorAddUsersInformation
import dorin_roman.app.kongfujava.ui.components.DevicePreviews
import dorin_roman.app.kongfujava.ui.theme.KongFuJavaTheme


@Composable
fun SupervisorAddUsersScreen(
    modifier: Modifier = Modifier,
    viewModel: SupervisorAddUsersViewModel = hiltViewModel()
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
            .padding(8.dp),
    ) {

        val (imageCard, createCodeCard, informationCard) = createRefs()

        SupervisorAddUsersImage(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .constrainAs(imageCard) {
                    top.linkTo(parent.top, margin = 8.dp)
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                    end.linkTo(informationCard.start, margin = 16.dp)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
        )

        SupervisorAddUsersInformation(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .constrainAs(informationCard) {
                    top.linkTo(parent.top, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                    bottom.linkTo(createCodeCard.top, margin = 8.dp)
                    height = Dimension.fillToConstraints
                    width = Dimension.preferredWrapContent
                }
        )

        SupervisorAddUsersCreateCode(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .constrainAs(createCodeCard) {
                    top.linkTo(informationCard.bottom, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                    start.linkTo(informationCard.start)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
            hasActiveCode = viewModel.hasActiveCode,
            minutes = viewModel.minutes,
            seconds = viewModel.seconds,
            code = viewModel.code,
            onCreateCodeClicked = {
                viewModel.handle(SupervisorAddUsersEvent.CreateCodeClicked)
            }
        )
    }
}

@DevicePreviews
@Composable
fun SupervisorAddUsersScreenPreview() {
    KongFuJavaTheme {
        SupervisorAddUsersScreen()
    }
}