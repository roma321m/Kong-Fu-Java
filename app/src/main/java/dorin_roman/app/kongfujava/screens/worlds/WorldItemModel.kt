package dorin_roman.app.kongfujava.screens.worlds

import androidx.compose.ui.graphics.painter.Painter
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.screens.level.levels.LevelItemModel

data class WorldItemModel(
    var worldName: String,
    var worldPic: Painter,
    var worldState: PointState = PointState.LOCK,
    var worldScore: Int,
    var worldLevels: List<LevelItemModel>
)
