package dorin_roman.app.kongfujava.screens.level.levels

import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.screens.level.LevelType

data class LevelItemModel(
    var levelState: PointState = PointState.LOCK,
    var levelNumber: Int,
    var levelScore: Int,
    var levelType : LevelType,
    var levelQuestion : String,
    var levelAnswers : List<String>
)