package dorin_roman.app.kongfujava.models

import androidx.compose.ui.graphics.painter.Painter
import dorin_roman.app.kongfujava.util.PointState

data class WorldItemModel(
    var worldName: String,
    var worldPic: Painter,
    var worldState: PointState = PointState.LOCK,
    var worldScore: Int,
    var worldLevels: List<LevelItemModel>
)
