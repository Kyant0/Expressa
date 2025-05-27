package com.kyant.expressa.m3.shape

import androidx.annotation.FloatRange
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.dp
import com.kyant.expressa.shape.CircleOmniShape
import com.kyant.expressa.shape.RectangleOmniShape
import com.kyant.expressa.shape.RoundedCornerOmniShape

@Immutable
data class CornerShapes(
    val none: RectangleOmniShape,
    val extraSmall: RoundedCornerOmniShape,
    val small: RoundedCornerOmniShape,
    val medium: RoundedCornerOmniShape,
    val large: RoundedCornerOmniShape,
    val largeIncreased: RoundedCornerOmniShape,
    val extraLarge: RoundedCornerOmniShape,
    val extraLargeIncreased: RoundedCornerOmniShape,
    val extraExtraLarge: RoundedCornerOmniShape,
    val full: RoundedCornerOmniShape
) {

    companion object {

        @Stable
        val Default: CornerShapes = CornerShapes(
            none = RectangleOmniShape,
            extraSmall = RoundedCornerOmniShape(4.dp),
            small = RoundedCornerOmniShape(8.dp),
            medium = RoundedCornerOmniShape(12.dp),
            large = RoundedCornerOmniShape(16.dp),
            largeIncreased = RoundedCornerOmniShape(20.dp),
            extraLarge = RoundedCornerOmniShape(28.dp),
            extraLargeIncreased = RoundedCornerOmniShape(32.dp),
            extraExtraLarge = RoundedCornerOmniShape(48.dp),
            full = CircleOmniShape
        )

        @Stable
        fun RoundedCornerOmniShape.smoothed(
            @FloatRange(from = 0.0, to = 1.0) smoothing: Float = 0.48f
        ): RoundedCornerOmniShape =
            this.copy(
                topStartSmoothing = smoothing,
                topEndSmoothing = smoothing,
                bottomEndSmoothing = smoothing,
                bottomStartSmoothing = smoothing
            )

        @Stable
        fun RoundedCornerOmniShape.start(): RoundedCornerOmniShape =
            this.copy(
                topEnd = ZeroCornerSize,
                bottomEnd = ZeroCornerSize
            )

        @Stable
        fun RoundedCornerOmniShape.top(): RoundedCornerOmniShape =
            this.copy(
                bottomStart = ZeroCornerSize,
                bottomEnd = ZeroCornerSize
            )

        @Stable
        fun RoundedCornerOmniShape.end(): RoundedCornerOmniShape =
            this.copy(
                topStart = ZeroCornerSize,
                bottomStart = ZeroCornerSize
            )

        @Stable
        fun RoundedCornerOmniShape.bottom(): RoundedCornerOmniShape =
            this.copy(
                topStart = ZeroCornerSize,
                topEnd = ZeroCornerSize
            )
    }
}
