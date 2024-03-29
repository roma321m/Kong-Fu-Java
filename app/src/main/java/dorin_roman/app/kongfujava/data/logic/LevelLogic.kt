package dorin_roman.app.kongfujava.data.logic

import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.screens.level.LevelType

object LevelLogic {

    fun getLevelState(state: Int): PointState {
        when (state) {
            PointState.LOCK.ordinal -> return PointState.LOCK
            PointState.ZERO.ordinal -> return PointState.ZERO
            PointState.ONE.ordinal -> return PointState.ONE
            PointState.TWO.ordinal -> return PointState.TWO
            PointState.THREE.ordinal -> return PointState.THREE
        }
        return PointState.LOCK
    }

    fun getLevelType(type: Int): LevelType {
        when (type) {
            LevelType.TUTORIAL.ordinal -> return LevelType.TUTORIAL
            LevelType.MULTI.ordinal -> return LevelType.MULTI
            LevelType.DRAG_DROP.ordinal -> return LevelType.DRAG_DROP
        }
        return LevelType.TUTORIAL
    }

    fun getLevelScore(hint: Int, mistake: Int): Int {
        return 3 - hint - mistake
    }

    fun getLevelStateByScore(score: Int): PointState {
        return when (score) {
            3 -> PointState.THREE
            2 -> PointState.TWO
            1 -> PointState.ONE
            0 -> PointState.ZERO
            else -> {
              PointState.ZERO
            }
        }
    }

}