package dorin_roman.app.kongfujava.screens.level.levels_map


sealed class LevelsMapEvent {
    class InitLevels(val worldId: Int) : LevelsMapEvent()
}