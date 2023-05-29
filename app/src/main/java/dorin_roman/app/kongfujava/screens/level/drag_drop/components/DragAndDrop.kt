package dorin_roman.app.kongfujava.screens.level.drag_drop.components

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection

internal val LocalDragTargetInfo = compositionLocalOf { DragTargetInfo() }

@Composable
fun <T> DragItem(
    modifier: Modifier = Modifier,
    dataToDrop: T,
    startDragging: () -> Unit,
    stopDragging: () -> Unit,
    content: @Composable () -> Unit,
) {
    var currentPosition by remember { mutableStateOf(Offset.Zero) }
    val currentState = LocalDragTargetInfo.current

    Box(
        modifier = modifier
            .onGloballyPositioned {
                currentPosition = it.localToWindow(Offset.Zero)
            }
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress(
                    onDragStart = {
                        startDragging()
                        currentState.dataToDrop = dataToDrop
                        currentState.isDragging = true
                        currentState.dragPosition = currentPosition + it
                        currentState.draggableComposable = content
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        currentState.dragOffset += Offset(dragAmount.x, dragAmount.y)
                    },
                    onDragEnd = {
                        stopDragging()
                        currentState.dragOffset = Offset.Zero
                        currentState.isDragging = false
                    },
                    onDragCancel = {
                        stopDragging()
                        currentState.dragOffset = Offset.Zero
                        currentState.isDragging = false
                    }
                )
            }
    ) {
        content()
    }
}

@Composable
fun <T> DropItem(
    modifier: Modifier,
    content: @Composable() (BoxScope.(isInBound: Boolean, data: T?, isDragging: Boolean) -> Unit)
) {
    val dragInfo = LocalDragTargetInfo.current
    val dragPosition = dragInfo.dragPosition
    val dragOffset = dragInfo.dragOffset
    var isCurrentDragTarget by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .onGloballyPositioned {
                it.boundsInWindow().let { rect ->
                    isCurrentDragTarget = rect.contains(dragPosition + dragOffset)
                }
            }
    ) {
        val data =
            if (isCurrentDragTarget && !dragInfo.isDragging) dragInfo.dataToDrop as T else null
        content(isCurrentDragTarget, data, dragInfo.isDragging)
    }
}

@Composable
fun DraggableScreen(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val state = remember {
        DragTargetInfo()
    }

    val localLayoutDirection = LocalLayoutDirection.current
    val configuration = LocalConfiguration.current

    CompositionLocalProvider(LocalDragTargetInfo provides state) {
        Box(modifier = modifier.fillMaxSize()) {
            content()
            if (state.isDragging) {
                var targetSize by remember {
                    mutableStateOf(IntSize.Zero)
                }
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            val y = state.dragPosition.y + state.dragOffset.y
                            val x =
                                -configuration.screenWidthDp * 1.47f + state.dragPosition.x + state.dragOffset.x
                            val offset = if (localLayoutDirection == LayoutDirection.Rtl) {
                                Offset(x, y)
                            } else {
                                state.dragPosition + state.dragOffset
                            }
                            scaleX = 0.5f
                            scaleY = 0.66f
                            alpha = if (targetSize == IntSize.Zero) 0f else .9f
                            translationX = offset.x.minus(targetSize.width / 2)
                            translationY = offset.y.minus(targetSize.height / 2)
                        }
                        .onGloballyPositioned {
                            targetSize = it.size
                        }
                ) {
                    state.draggableComposable?.invoke()
                }
            }
        }
    }
}

internal class DragTargetInfo {
    var isDragging: Boolean by mutableStateOf(false)
    var dragPosition by mutableStateOf(Offset.Zero)
    var dragOffset by mutableStateOf(Offset.Zero)
    var draggableComposable by mutableStateOf<(@Composable () -> Unit)?>(null)
    var dataToDrop by mutableStateOf<Any?>(null)
}