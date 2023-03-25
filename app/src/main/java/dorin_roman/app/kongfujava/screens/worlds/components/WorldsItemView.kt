package dorin_roman.app.kongfujava.screens.worlds.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.screens.worlds.WorldItemModel
import dorin_roman.app.kongfujava.ui.components.*


@Composable
fun WorldsItemView(worldItemModel: WorldItemModel) {
    Card(
        modifier = Modifier.alpha(0.6f),
        elevation = 4.dp,
        shape = RoundedCornerShape(size = 12.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Column(
            modifier = Modifier.padding(all = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .size(height = 100.dp, width = 200.dp),
                painter = worldItemModel.worldPic,
                contentDescription = "lucy pic",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(height = 16.dp))
            Text(
                text = worldItemModel.worldName,
                style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onPrimary)
            )
            Row(modifier = Modifier.height(height = 30.dp)) {
                //Fixme add ifElse for Lock/Open/or after points
                when (worldItemModel.worldState) {
                    PointState.LOCK -> {
                        LockLevel()
                    }
                    PointState.ZERO -> {
                        ZeroLevelYellow()
                    }
                    PointState.ONE -> {
                        OneLevel()
                    }
                    PointState.TWO -> {
                        TwoLevel()
                    }
                    PointState.THREE -> {
                        ThreeLevel()
                    }
                }
            }
            Spacer(modifier = Modifier.height(height = 16.dp))
            IconButton(onClick = {
                if (worldItemModel.worldState != PointState.LOCK) {
                    //TODO onclick navigate to levels
                }
            }) {
                Icon(
                    imageVector = if (worldItemModel.worldState == PointState.LOCK) Icons.Default.Lock else Icons.Default.PlayCircle,
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun WorldsItemViewPreview() {
    WorldsItemView(
        WorldItemModel(
            worldName = "VARIABLES WORLDS",
            worldPic = painterResource(id = R.drawable.ic_panda_register),
            worldScore = 0,
            worldState = PointState.ZERO,
            worldLevels = listOf()
        )
    )
}