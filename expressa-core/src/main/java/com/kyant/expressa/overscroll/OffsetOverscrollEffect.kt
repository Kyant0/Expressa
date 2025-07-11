package com.kyant.expressa.overscroll

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.util.fastRoundToInt
import kotlinx.coroutines.CoroutineScope

/** An [OverscrollEffect] that offsets the content by the overscroll value. */
class OffsetOverscrollEffect(
    private val orientation: Orientation,
    animationScope: CoroutineScope,
    animationSpec: AnimationSpec<Float> = DefaultAnimationSpec,
    private val progressConverter: ProgressConverter = ProgressConverter.Default
) : BaseContentOverscrollEffect(orientation, animationScope, animationSpec) {

    override val node: DelegatableNode =
        object : Modifier.Node(), LayoutModifierNode {

            override val shouldAutoInvalidate: Boolean = false

            override fun MeasureScope.measure(
                measurable: Measurable,
                constraints: Constraints,
            ): MeasureResult {
                val placeable = measurable.measure(constraints)

                return layout(placeable.width, placeable.height) {
                    val maxDistance = when (orientation) {
                        Orientation.Horizontal -> constraints.maxWidth.toFloat()
                        Orientation.Vertical -> constraints.maxHeight.toFloat()
                    }
                    val offsetPx = computeOffset(overscrollDistance, maxDistance)
                    placeable.placeRelativeWithLayer(position = offsetPx.toIntOffset())
                }
            }
        }

    private fun computeOffset(overscrollDistance: Float, maxDistance: Float): Int {
        val progress = progressConverter.convert(overscrollDistance / maxDistance)
        return (progress * maxDistance).fastRoundToInt()
    }

    internal companion object {

        val DefaultAnimationSpec = spring(1f, 150f, 0.5f)
    }
}

@Composable
fun rememberOffsetOverscrollEffect(
    orientation: Orientation,
    animationSpec: AnimationSpec<Float> = OffsetOverscrollEffect.DefaultAnimationSpec,
    progressConverter: ProgressConverter = ProgressConverter.Default
): OffsetOverscrollEffect {
    val animationScope = rememberCoroutineScope()
    return remember(orientation, animationScope, animationSpec, progressConverter) {
        OffsetOverscrollEffect(
            orientation = orientation,
            animationScope = animationScope,
            animationSpec = animationSpec,
            progressConverter = progressConverter
        )
    }
}
