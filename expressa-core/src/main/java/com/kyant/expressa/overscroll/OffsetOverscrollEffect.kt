package com.kyant.expressa.overscroll

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.OverscrollEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.LayoutAwareModifierNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.util.fastRoundToInt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.sign
import kotlin.math.sqrt

@Composable
fun rememberOffsetOverscrollEffect(): OffsetOverscrollEffect {
    val scope = rememberCoroutineScope()
    return remember(scope) {
        OffsetOverscrollEffect(scope)
    }
}

class OffsetOverscrollEffect internal constructor(
    private val scope: CoroutineScope
) : OverscrollEffect {

    private var limit = Offset.Zero
    private var stiffnessX = 200f
    private var stiffnessY = 200f
    private val rubberBandX = RubberBand()
    private val rubberBandY = RubberBand()

    private val overscrollOffsetXAnimation = Animatable(0f)
    private val contentOffsetX by derivedStateOf {
        rubberBandX.valueOf(overscrollOffsetXAnimation.value).fastRoundToInt()
    }

    private val overscrollOffsetYAnimation = Animatable(0f)
    private val contentOffsetY by derivedStateOf {
        rubberBandY.valueOf(overscrollOffsetYAnimation.value).fastRoundToInt()
    }

    override fun applyToScroll(
        delta: Offset,
        source: NestedScrollSource,
        performScroll: (Offset) -> Offset
    ): Offset {
        // in pre scroll we relax the overscroll if needed
        // relaxation: when we are in progress of the overscroll and user scrolls in the
        // different direction = substract the overscroll first
        val sameXDirection = sign(delta.x) == sign(overscrollOffsetXAnimation.value)
        val sameYDirection = sign(delta.y) == sign(overscrollOffsetYAnimation.value)
        val consumedXByPreScroll =
            if (abs(overscrollOffsetXAnimation.value) > 0.5 && !sameXDirection) {
                val prevOverscrollValue = overscrollOffsetXAnimation.value
                val newOverscrollValue = overscrollOffsetXAnimation.value + delta.x
                if (sign(prevOverscrollValue) != sign(newOverscrollValue)) {
                    // sign changed, coerce to start scrolling and exit
                    scope.launch {
                        overscrollOffsetXAnimation.snapTo(0f)
                    }
                    Offset(delta.x + prevOverscrollValue, 0f)
                } else {
                    scope.launch {
                        overscrollOffsetXAnimation.snapTo(overscrollOffsetXAnimation.value + delta.x)
                    }
                    delta.copy(y = 0f)
                }
            } else {
                Offset.Zero
            }
        val consumedYByPreScroll =
            if (abs(overscrollOffsetYAnimation.value) > 0.5 && !sameYDirection) {
                val prevOverscrollValue = overscrollOffsetYAnimation.value
                val newOverscrollValue = overscrollOffsetYAnimation.value + delta.y
                if (sign(prevOverscrollValue) != sign(newOverscrollValue)) {
                    // sign changed, coerce to start scrolling and exit
                    scope.launch {
                        overscrollOffsetYAnimation.snapTo(0f)
                    }
                    Offset(0f, delta.y + prevOverscrollValue)
                } else {
                    scope.launch {
                        overscrollOffsetYAnimation.snapTo(overscrollOffsetYAnimation.value + delta.y)
                    }
                    delta.copy(x = 0f)
                }
            } else {
                Offset.Zero
            }
        val consumedByPreScroll = consumedXByPreScroll + consumedYByPreScroll
        val leftForScroll = delta - consumedByPreScroll
        val consumedByScroll = performScroll(leftForScroll)
        val overscrollDelta = leftForScroll - consumedByScroll
        // if it is a drag, not a fling, add the delta left to our over scroll value
        if (source == NestedScrollSource.UserInput) {
            if (abs(overscrollDelta.x) > 0.5) {
                scope.launch {
                    overscrollOffsetXAnimation.snapTo(overscrollOffsetXAnimation.value + overscrollDelta.x)
                }
            }
            if (abs(overscrollDelta.y) > 0.5) {
                scope.launch {
                    overscrollOffsetYAnimation.snapTo(overscrollOffsetYAnimation.value + overscrollDelta.y)
                }
            }
        }
        return consumedByPreScroll + consumedByScroll
    }

    override suspend fun applyToFling(
        velocity: Velocity,
        performFling: suspend (Velocity) -> Velocity
    ) {
        val consumed = performFling(velocity)
        // when the fling happens - we just gradually animate our overscroll to 0
        val remaining = velocity - consumed
        if (remaining.x != 0f || overscrollOffsetXAnimation.value != 0f) {
            if (limit.x != 0f) {
                overscrollOffsetXAnimation.animateTo(
                    targetValue = 0f,
                    animationSpec = spring(stiffness = stiffnessX),
                    initialVelocity = remaining.x
                )
            } else {
                overscrollOffsetXAnimation.snapTo(0f)
            }
        }
        if (remaining.y != 0f || overscrollOffsetYAnimation.value != 0f) {
            if (limit.y != 0f) {
                overscrollOffsetYAnimation.animateTo(
                    targetValue = 0f,
                    animationSpec = spring(stiffness = stiffnessY),
                    initialVelocity = remaining.y
                )
            } else {
                overscrollOffsetYAnimation.snapTo(0f)
            }
        }
    }

    override val isInProgress: Boolean = false

    override val node: DelegatableNode =
        object : LayoutModifierNode, LayoutAwareModifierNode, Modifier.Node() {

            override val shouldAutoInvalidate: Boolean = false

            override fun MeasureScope.measure(
                measurable: Measurable,
                constraints: Constraints
            ): MeasureResult {
                val placeable = measurable.measure(constraints)

                return layout(placeable.width, placeable.height) {
                    placeable.place(contentOffsetX, contentOffsetY)
                }
            }

            override fun onRemeasured(size: IntSize) {
                limit = Offset(size.width.toFloat(), size.height.toFloat())
                rubberBandX.updateLimit(limit.x)
                rubberBandY.updateLimit(limit.y)

                val (width, height) = size
                stiffnessX =
                    if (width > 0f) {
                        200f / sqrt(limit.x / width)
                    } else {
                        200f
                    }
                stiffnessY =
                    if (height > 0f) {
                        200f / sqrt(limit.y / height)
                    } else {
                        200f
                    }
            }
        }
}
