package com.kyant.expressa.shape

import androidx.annotation.FloatRange
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.util.fastCoerceAtLeast
import androidx.compose.ui.util.fastCoerceIn
import androidx.compose.ui.util.lerp

@Immutable
internal data class LerpRoundedCornerOmniShape(
    val start: RoundedCornerOmniShape,
    val stop: RoundedCornerOmniShape,
    val fraction: Float
) :
    RoundedCornerOmniShape(
        topStart = lerpCornerSize(start.topStart, stop.topStart, fraction),
        topEnd = lerpCornerSize(start.topEnd, stop.topEnd, fraction),
        bottomEnd = lerpCornerSize(start.bottomEnd, stop.bottomEnd, fraction),
        bottomStart = lerpCornerSize(start.bottomStart, stop.bottomStart, fraction),
        topStartSmoothing =
            lerp(start.topStartSmoothing, stop.topStartSmoothing, fraction.fastCoerceIn(0f, 1f)),
        topEndSmoothing =
            lerp(start.topEndSmoothing, stop.topEndSmoothing, fraction.fastCoerceIn(0f, 1f)),
        bottomEndSmoothing =
            lerp(start.bottomEndSmoothing, stop.bottomEndSmoothing, fraction.fastCoerceIn(0f, 1f)),
        bottomStartSmoothing =
            lerp(start.bottomStartSmoothing, stop.bottomStartSmoothing, fraction.fastCoerceIn(0f, 1f))
    ), OmniShape

@Stable
private fun lerpCornerSize(
    start: CornerSize,
    stop: CornerSize,
    @FloatRange(from = 0.0, to = 1.0) fraction: Float
): CornerSize = LerpCornerSize(start, stop, fraction)

@Immutable
private data class LerpCornerSize(
    private val start: CornerSize,
    private val stop: CornerSize,
    @param:FloatRange(from = 0.0, to = 1.0) private val fraction: Float
) : CornerSize {

    override fun toPx(shapeSize: Size, density: Density): Float {
        return lerp(
            start.toPx(shapeSize, density),
            stop.toPx(shapeSize, density),
            fraction
        ).fastCoerceAtLeast(0f)
    }
}
