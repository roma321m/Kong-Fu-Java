package dorin_roman.app.kongfujava.screens.level.levels_map.components

import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.domain.models.Level

sealed class LevelsEvent {

    class UpdateLevel(val level: Level) : LevelsEvent()
    class UpdateLevelScore(val score: Int) : LevelsEvent()
    class UpdateLevelState(val state: PointState) : LevelsEvent()
    class InitLevels(val worldId: Int) : LevelsEvent()

}