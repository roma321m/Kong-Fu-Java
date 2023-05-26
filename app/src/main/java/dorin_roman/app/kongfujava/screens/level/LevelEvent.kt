package dorin_roman.app.kongfujava.screens.level

sealed class LevelEvent {
    class InitLevel(val levelId: Int, val worldId: Int) : LevelEvent()
    class UpdateLevelScore(val hintCount: Int = 0, val mistakesCount: Int = 0) : LevelEvent()
    object UpdateLevelState : LevelEvent()
    object HandleExit : LevelEvent()
}
