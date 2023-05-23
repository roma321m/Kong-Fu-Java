package dorin_roman.app.kongfujava.screens.level

import androidx.compose.runtime.MutableState
import dorin_roman.app.kongfujava.data.models.PointState

sealed class LevelEvent{
    class InitLevel(val levelId: Int) : LevelEvent()
    class UpdateLevelScore(val levelId: Int, val worldId: Int, val score: MutableState<Int>) : LevelEvent()
    class UpdateLevelState(val levelId: Int, val worldId: Int, val state: MutableState<PointState>) : LevelEvent()
}
