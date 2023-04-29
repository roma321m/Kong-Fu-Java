package dorin_roman.app.kongfujava.ui.components

import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.data.models.PointState
import dorin_roman.app.kongfujava.screens.worlds.components.WORLD_TYPE

@Composable
fun Stars(state: PointState, type: String) {
    when (state) {
        PointState.LOCK -> {
            if (type == WORLD_TYPE) {
                ZeroLevelWhite(type)
            } else {
                LockLevel(type)
            }
        }

        PointState.ZERO -> {
            ZeroLevelYellow(type)
        }

        PointState.ONE -> {
            OneLevel(type)
        }

        PointState.TWO -> {
            TwoLevel(type)
        }

        PointState.THREE -> {
            ThreeLevel(type)
        }
    }
}