package dorin_roman.app.kongfujava.data.logic

import dorin_roman.app.kongfujava.data.models.PointState

object WorldLogic {

    fun getWorldStateByScore(score: Int): PointState {
        return when (score) {
            in 70..90 -> PointState.THREE
            in 41..70 -> PointState.TWO
            in 21..40 -> PointState.ONE
            in 0..20 -> PointState.ZERO
            else -> {
                PointState.ZERO
            }
        }
    }

}