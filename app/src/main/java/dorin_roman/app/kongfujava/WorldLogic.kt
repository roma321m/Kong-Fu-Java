package dorin_roman.app.kongfujava

import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.screens.level.LevelType

object WorldLogic {

//    fun getWorldStateByScore(score: Int): PointState {
//        return when (score) {
//           in 70..90 -> PointState.THREE
//            in 41..70 -> PointState.TWO
//           in  21..40 -> PointState.ONE
//           in 0..20 -> PointState.ZERO
//            else -> {
//                PointState.ZERO
//            }
//        }
//    }

    //fixme delete this if works
    fun getWorldStateByScore(score: Int): PointState {
        return when (score) {
            in 13..20 -> PointState.THREE
            in 7..12 -> PointState.TWO
            in 4..6 -> PointState.ONE
            in 0..3-> PointState.ZERO
            else -> {
                PointState.ZERO
            }
        }
    }

}