package dorin_roman.app.kongfujava.screens.worlds.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.domain.models.World
import dorin_roman.app.kongfujava.ui.components.Stars
import dorin_roman.app.kongfujava.ui.theme.elevation

const val WORLD_TYPE ="World"

@Composable
fun WorldsItemView(
    world: World,
    navigateToMapLevels: (id: Int) -> Unit
) {

    Card(
        modifier = Modifier
            .then(
                if (world.state == PointState.LOCK.ordinal) {
                    Modifier.alpha(0.6f)
                } else {
                    Modifier.clickable {
                        navigateToMapLevels(world.id)
                    }
                }
            )
            .fillMaxHeight()
            .width(500.dp)
            .padding(20.dp),
        elevation = MaterialTheme.elevation.small,
        shape = RoundedCornerShape(size = 40.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(all = 20.dp)
        ) {
            val (image, title, starts, button) = createRefs()

            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 40.dp))
                    .constrainAs(image) {
                        linkTo(start = parent.start, end = parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(title.top, 16.dp)
                        height = Dimension.fillToConstraints
                    },
                painter = painterResource(id = getImage(world.id)),
                contentDescription = world.name,
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        linkTo(start = parent.start, end = parent.end)
                        bottom.linkTo(starts.top)
                    },
                text = world.name.uppercase(),
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onPrimary)
            )

            Row(
                modifier = Modifier
                    .constrainAs(starts) {
                        linkTo(start = parent.start, end = parent.end)
                        bottom.linkTo(button.top, 30.dp)
                    }
            ) {
                Stars(state = PointState.values()[world.state], type = WORLD_TYPE)
            }

            Icon(
                modifier = Modifier
                    .constrainAs(button) {
                        linkTo(start = parent.start, end = parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(70.dp),
                imageVector = if (world.state == PointState.LOCK.ordinal) {
                    Icons.Default.Lock
                } else {
                    Icons.Default.PlayCircle
                },
                contentDescription = "Play",
                tint = Color.White
            )

        }
    }
}

private fun getImage(id: Int): Int =
    when (id) {
        0 -> R.drawable.variables_background
        1 -> R.drawable.if_else_backgorund
        2 -> R.drawable.loops_background
        3 -> R.drawable.arrays_background
        4 -> R.drawable.strings_background
        else -> {
            R.drawable.variables_background
        }
    }


@Preview
@Composable
fun WorldsItemViewPreview() {
    WorldsItemView(
        world = World(
            id = 0,
            name = "VARIABLES WORLDS",
            score = 0,
            state = PointState.ZERO.ordinal
        ),
        navigateToMapLevels = { }
    )
}