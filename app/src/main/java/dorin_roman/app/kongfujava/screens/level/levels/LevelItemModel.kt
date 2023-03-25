package dorin_roman.app.kongfujava.screens.level.levels

import dorin_roman.app.kongfujava.data.models.PointState

data class LevelItemModel(
    var levelState: PointState = PointState.LOCK,
    var levelNumber: Int,
    var levelScore: Int,
)