package dorin_roman.app.kongfujava.screens.level

sealed class LevelEvent {
    class InitLevel(val levelId: Int, val worldId: Int) : LevelEvent()
    object UpdateLevelScore : LevelEvent()
    object UpdateLevelState : LevelEvent()
    object UpdateLevelHint : LevelEvent()
    object UpdateLevelMistakes : LevelEvent()
    object FinishLevel : LevelEvent()
}
