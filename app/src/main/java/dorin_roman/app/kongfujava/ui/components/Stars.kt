package dorin_roman.app.kongfujava.ui.components

import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.data.models.PointState

@Composable
fun Stars(state: PointState) {
    when (state) {
        PointState.LOCK -> {
            ZeroLevelWhite()
        }
        PointState.ZERO -> {
            ZeroLevelYellow()
        }
        PointState.ONE -> {
            OneLevel()
        }
        PointState.TWO -> {
            TwoLevel()
        }
        PointState.THREE -> {
            ThreeLevel()
        }
    }
}