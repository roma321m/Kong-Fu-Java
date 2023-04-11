package dorin_roman.app.kongfujava.screens.worlds.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.domain.models.World
import dorin_roman.app.kongfujava.ui.components.*

@Composable
fun WorldsItemView(world: World, navigateToLevel: () -> Unit) {
    val modifier = Modifier
        .size(height = 600.dp, width = 400.dp)
        .padding(20.dp)

    Card(
        modifier = (if (world.state == PointState.LOCK.ordinal) modifier.alpha(0.6f) else modifier.alpha(1f)),
        elevation = 4.dp,
        shape = RoundedCornerShape(size = 12.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(all = 20.dp)
        ) {
            val (image, title, starts, button) = createRefs()

            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .size(height = 200.dp, width = 400.dp)
                    .constrainAs(image) {
                        linkTo(start = parent.start, end = parent.end)
                        top.linkTo(parent.top)
                    },
                painter = painterResource(id = getImage(world.id)),
                contentDescription = world.name,
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        linkTo(start = parent.start, end = parent.end)
                        top.linkTo(image.bottom, 16.dp)
                    },
                text = world.name.uppercase(),
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onPrimary)
            )

            Row(
                modifier =
                Modifier
                    .constrainAs(starts) {
                        linkTo(start = parent.start, end = parent.end)
                        top.linkTo(title.bottom, 8.dp)
                    },
            ) {
                //Fixme add ifElse for Lock/Open/or after points
                when (world.state) {
                    PointState.LOCK.ordinal -> {
                        ZeroLevelWhite()
                    }
                    PointState.ZERO.ordinal -> {
                        ZeroLevelYellow()
                    }
                    PointState.ONE.ordinal -> {
                        OneLevel()
                    }
                    PointState.TWO.ordinal -> {
                        TwoLevel()
                    }
                    PointState.THREE.ordinal -> {
                        ThreeLevel()
                    }
                }
            }

            IconButton(modifier = Modifier
                .constrainAs(button) {
                    linkTo(start = parent.start, end = parent.end)
                    bottom.linkTo(parent.bottom, 16.dp)
                },
                onClick = {
                    if (world.state != PointState.LOCK.ordinal) {
                        navigateToLevel()
                    }
                }) {
                Icon(
                    imageVector = if (world.state == PointState.LOCK.ordinal) Icons.Default.Lock else Icons.Default.PlayCircle,
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier
                        .size(70.dp)
                )
            }


        }
    }
}

private fun getImage(id: Int): Int =
    when (id) {
        0 -> R.drawable.variables_background
        1 -> R.drawable.if_else_backgorund
        2 -> R.drawable.loops_backgroud
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
        navigateToLevel = { }
    )
}