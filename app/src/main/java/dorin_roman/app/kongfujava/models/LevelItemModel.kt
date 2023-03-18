package dorin_roman.app.kongfujava.models

import dorin_roman.app.kongfujava.util.LevelState

data class LevelItemModel(
    var levelState: LevelState = LevelState.LOCK,
    var levelNumber: Int,
    var levelScore: Int,
    )