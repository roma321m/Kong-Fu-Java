package dorin_roman.app.kongfujava.screens.worlds

import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.domain.models.World

sealed class WorldsEvent {
    class UpdateWorld(val world: World) : WorldsEvent()
    class UpdateWorldScore(val score: Int) : WorldsEvent()
    class UpdateWorldState(val state: PointState) : WorldsEvent()
}