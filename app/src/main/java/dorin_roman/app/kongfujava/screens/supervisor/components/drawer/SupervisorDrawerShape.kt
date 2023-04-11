package dorin_roman.app.kongfujava.screens.supervisor.components.drawer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class SupervisorDrawerShape(
    private val widthOffset: Dp,
    private val scale: Float
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return when (layoutDirection) {
            LayoutDirection.Ltr -> Outline.Rectangle(
                Rect(
                    topLeft = Offset.Zero,
                    bottomRight = Offset(
                        x = size.width * scale + with(density) { widthOffset.toPx() },
                        y = size.height
                    )
                )
            )
            LayoutDirection.Rtl -> Outline.Rectangle(
                Rect(
                    topLeft = Offset.Zero, // Fixme rtl not working good !!!!!
                    bottomRight = Offset(
                        x = size.width * scale + with(density) { widthOffset.toPx() },
                        y = size.height
                    )
                )
            )
        }
    }
}