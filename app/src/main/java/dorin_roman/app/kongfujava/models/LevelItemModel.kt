package dorin_roman.app.kongfujava.models

import dorin_roman.app.kongfujava.util.PointState

data class LevelItemModel(
    var levelState: PointState = PointState.LOCK,
    var levelNumber: Int,
    var levelScore: Int,
)