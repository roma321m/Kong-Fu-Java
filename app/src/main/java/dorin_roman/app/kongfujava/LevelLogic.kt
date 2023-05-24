package dorin_roman.app.kongfujava

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

    fun getScore(hint: Int, mistake: Int): Int {
        return 6 - hint - mistake
    }

    fun getState(score: Int): PointState {
        return when (score) {
            5, 6 -> getLevelState(PointState.THREE.ordinal)
            3, 4 -> getLevelState(PointState.TWO.ordinal)
            1, 2 -> getLevelState(PointState.ONE.ordinal)
            0 -> getLevelState(PointState.ZERO.ordinal)
            else -> {
                getLevelState(PointState.ZERO.ordinal)
            }
        }
    }

}