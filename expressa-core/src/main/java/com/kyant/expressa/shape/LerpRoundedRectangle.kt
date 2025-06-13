package com.kyant.expressa.shape

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.util.fastCoerceAtLeast
import androidx.compose.ui.util.fastCoerceIn
import androidx.compose.ui.util.lerp

@Immutable
internal data class LerpRoundedRectangle(
    val start: RoundedRectangle,
    val stop: RoundedRectangle,
    val fraction: Float
) :
    RoundedRectangle(
        topStart = LerpCornerSize(start.topStart, stop.topStart, fraction),
        topEnd = LerpCornerSize(start.topEnd, stop.topEnd, fraction),
        bottomEnd = LerpCornerSize(start.bottomEnd, stop.bottomEnd, fraction),
        bottomStart = LerpCornerSize(start.bottomStart, stop.bottomStart, fraction),
        topStartSmoothing =
            lerp(start.topStartSmoothing, stop.topStartSmoothing, fraction.fastCoerceIn(0f, 1f)),
        topEndSmoothing =
            lerp(start.topEndSmoothing, stop.topEndSmoothing, fraction.fastCoerceIn(0f, 1f)),
        bottomEndSmoothing =
            lerp(start.bottomEndSmoothing, stop.bottomEndSmoothing, fraction.fastCoerceIn(0f, 1f)),
        bottomStartSmoothing =
            lerp(start.bottomStartSmoothing, stop.bottomStartSmoothing, fraction.fastCoerceIn(0f, 1f))
    ), InterpolableShape

@Immutable
private data class LerpCornerSize(
    private val start: CornerSize,
    private val stop: CornerSize,
    private val fraction: Float
) : CornerSize {

    override fun toPx(shapeSize: Size, density: Density): Float {
        return lerp(
            start.toPx(shapeSize, density),
            stop.toPx(shapeSize, density),
            fraction
        ).fastCoerceAtLeast(0f)
    }
}
