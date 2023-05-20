package dorin_roman.app.kongfujava.screens.supervisor.components.drawer

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection


class SupervisorDrawerShape(
    private val scale: Float
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return when (layoutDirection) {
            LayoutDirection.Ltr -> Outline.Rectangle(
                Rect(0f, 0f, scale * size.width, size.height)
            )

            LayoutDirection.Rtl -> Outline.Rectangle(// Fixme rtl not working good !!!!!
                Rect(0f, 0f, scale * size.width, size.height)
            )
        }
    }
}