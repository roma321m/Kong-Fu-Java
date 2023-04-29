package dorin_roman.app.kongfujava.screens.level.levels_map

import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.screens.level.LevelType

data class LevelItemModel(
    var levelId: Int,
    var levelState: PointState = PointState.LOCK,
    var levelNumber: Int,
    var levelScore: Int,
    var levelType: LevelType,
)