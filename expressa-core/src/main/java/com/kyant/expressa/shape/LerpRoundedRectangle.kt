package com.kyant.expressa.shape

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.util.fastCoerceAtLeast
import androidx.compose.ui.util.lerp

@Stable
fun lerp(
    start: RoundedRectangle,
    stop: RoundedRectangle,
    fraction: Float
): RoundedRectangle {
    return LerpRoundedRectangle(start, stop, fraction)
}

@Immutable
private data class LerpRoundedRectangle(
    val start: RoundedRectangle,
    val stop: RoundedRectangle,
    val fraction: Float
) :
    RoundedRectangle(
        topStart = LerpCornerSize(start.topStart, stop.topStart, fraction),
        topEnd = LerpCornerSize(start.topEnd, stop.topEnd, fraction),
        bottomEnd = LerpCornerSize(start.bottomEnd, stop.bottomEnd, fraction),
        bottomStart = LerpCornerSize(start.bottomStart, stop.bottomStart, fraction),
        cornerSmoothing =
            CornerSmoothing(
                circleFraction =
                    lerp(
                        start.cornerSmoothing.circleFraction,
                        stop.cornerSmoothing.circleFraction,
                        fraction.fastCoerceAtLeast(0f)
                    ),
                extendedFraction =
                    lerp(
                        start.cornerSmoothing.extendedFraction,
                        stop.cornerSmoothing.extendedFraction,
                        fraction.fastCoerceAtLeast(0f)
                    )
            )
    )

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
