package dorin_roman.app.kongfujava.ui.components.popup

import androidx.compose.runtime.Composable
import dorin_roman.app.kongfujava.R
import dorin_roman.app.kongfujava.data.models.PointState

@Composable
fun PopUpImage(levelState: PointState) : Int {
    return when (levelState) {
        PointState.LOCK -> R.drawable.img_panda_order
        PointState.ZERO -> R.drawable.img_panda_lose
        PointState.ONE -> R.drawable.img_panda_lose
        PointState.TWO -> R.drawable.img_panda_win
        PointState.THREE -> R.drawable.img_panda_win
    }

}