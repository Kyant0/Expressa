package com.kyant.expressa.shape

import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.LayoutDirection.Ltr
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.rectangle
import com.kyant.expressa.requirePrecondition

@Immutable
open class RoundedCornerOmniShape(
    topStart: CornerSize,
    topEnd: CornerSize,
    bottomEnd: CornerSize,
    bottomStart: CornerSize,
    @param:FloatRange(from = 0.0, to = 1.0) val topStartSmoothing: Float = 0f,
    @param:FloatRange(from = 0.0, to = 1.0) val topEndSmoothing: Float = 0f,
    @param:FloatRange(from = 0.0, to = 1.0) val bottomEndSmoothing: Float = 0f,
    @param:FloatRange(from = 0.0, to = 1.0) val bottomStartSmoothing: Float = 0f
) :
    CornerBasedShape(
        topStart = topStart,
        topEnd = topEnd,
        bottomEnd = bottomEnd,
        bottomStart = bottomStart
    ), OmniShape {

    override val startAngle: Int = 0

    override val transformOrigin: TransformOrigin = TransformOrigin.Center

    init {
        requirePrecondition(
            topStartSmoothing >= 0f && topStartSmoothing <= 1f &&
                    topEndSmoothing >= 0f && topEndSmoothing <= 1f &&
                    bottomEndSmoothing >= 0f && bottomEndSmoothing <= 1f &&
                    bottomStartSmoothing >= 0f && bottomStartSmoothing <= 1f
        ) {
            "Corner smoothing can't be negative(topStartSmoothing = $topStartSmoothing, topEndSmoothing = " +
                    "$topEndSmoothing, bottomEndSmoothing = $bottomEndSmoothing, bottomStartSmoothing = " +
                    "$bottomStartSmoothing)!"
        }
    }

    final override fun toRoundedPolygon(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Float
    ): RoundedPolygon {
        val density = Density(density)
        val topStart = topStart.toPx(size, density)
        val topEnd = topEnd.toPx(size, density)
        val bottomEnd = bottomEnd.toPx(size, density)
        val bottomStart = bottomStart.toPx(size, density)

        return if (topStart + topEnd + bottomEnd + bottomStart == 0f) {
            RoundedPolygon.rectangle(
                width = size.width,
                height = size.height,
                centerX = size.width / 2,
                centerY = size.height / 2
            )
        } else {
            toRoundedPolygon(
                size = size,
                topStart = topStart,
                topEnd = topEnd,
                bottomEnd = bottomEnd,
                bottomStart = bottomStart,
                layoutDirection = layoutDirection
            )
        }
    }

    final override fun createOutline(
        size: Size,
        topStart: Float,
        topEnd: Float,
        bottomEnd: Float,
        bottomStart: Float,
        layoutDirection: LayoutDirection
    ): Outline {
        return if (topStart + topEnd + bottomEnd + bottomStart == 0f) {
            Outline.Rectangle(size.toRect())
        } else if (
            topEndSmoothing == 0f &&
            topStartSmoothing == 0f &&
            bottomEndSmoothing == 0f &&
            bottomStartSmoothing == 0f
        ) {
            val topLeft = CornerRadius(if (layoutDirection == Ltr) topStart else topEnd)
            val topRight = CornerRadius(if (layoutDirection == Ltr) topEnd else topStart)
            val bottomRight = CornerRadius(if (layoutDirection == Ltr) bottomEnd else bottomStart)
            val bottomLeft = CornerRadius(if (layoutDirection == Ltr) bottomStart else bottomEnd)

            Outline.Rounded(
                RoundRect(
                    rect = size.toRect(),
                    topLeft = topLeft,
                    topRight = topRight,
                    bottomRight = bottomRight,
                    bottomLeft = bottomLeft
                )
            )
        } else {
            Outline.Generic(
                this
                    .toRoundedPolygon(
                        size = size,
                        topStart = topStart,
                        topEnd = topEnd,
                        bottomEnd = bottomEnd,
                        bottomStart = bottomStart,
                        layoutDirection = layoutDirection
                    )
                    .toPath(
                        startAngle = startAngle
                    )
            )
        }
    }

    final override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize
    ): RoundedCornerOmniShape {
        return RoundedCornerOmniShape(
            topStart = topStart,
            topEnd = topEnd,
            bottomEnd = bottomEnd,
            bottomStart = bottomStart,
            topStartSmoothing = topStartSmoothing,
            topEndSmoothing = topEndSmoothing,
            bottomEndSmoothing = bottomEndSmoothing,
            bottomStartSmoothing = bottomStartSmoothing
        )
    }

    private fun toRoundedPolygon(
        size: Size,
        topStart: Float,
        topEnd: Float,
        bottomEnd: Float,
        bottomStart: Float,
        layoutDirection: LayoutDirection
    ): RoundedPolygon {
        val topLeft = if (layoutDirection == Ltr) topStart else topEnd
        val topRight = if (layoutDirection == Ltr) topEnd else topStart
        val bottomLeft = if (layoutDirection == Ltr) bottomStart else bottomEnd
        val bottomRight = if (layoutDirection == Ltr) bottomEnd else bottomStart

        val topLeftSmoothing = if (layoutDirection == Ltr) topStartSmoothing else topEndSmoothing
        val topRightSmoothing = if (layoutDirection == Ltr) topEndSmoothing else topStartSmoothing
        val bottomLeftSmoothing = if (layoutDirection == Ltr) bottomStartSmoothing else bottomEndSmoothing
        val bottomRightSmoothing = if (layoutDirection == Ltr) bottomEndSmoothing else bottomStartSmoothing

        return RoundedPolygon.rectangle(
            width = size.width,
            height = size.height,
            perVertexRounding = listOf(
                CornerRounding(bottomRight, bottomRightSmoothing),
                CornerRounding(bottomLeft, bottomLeftSmoothing),
                CornerRounding(topLeft, topLeftSmoothing),
                CornerRounding(topRight, topRightSmoothing)
            ),
            centerX = size.width / 2,
            centerY = size.height / 2
        )
    }

    fun copy(
        topStart: CornerSize = this.topStart,
        topEnd: CornerSize = this.topEnd,
        bottomEnd: CornerSize = this.bottomEnd,
        bottomStart: CornerSize = this.bottomStart,
        topStartSmoothing: Float = this.topStartSmoothing,
        topEndSmoothing: Float = this.topEndSmoothing,
        bottomEndSmoothing: Float = this.bottomEndSmoothing,
        bottomStartSmoothing: Float = this.bottomStartSmoothing
    ): RoundedCornerOmniShape {
        return RoundedCornerOmniShape(
            topStart = topStart,
            topEnd = topEnd,
            bottomEnd = bottomEnd,
            bottomStart = bottomStart,
            topStartSmoothing = topStartSmoothing,
            topEndSmoothing = topEndSmoothing,
            bottomEndSmoothing = bottomEndSmoothing,
            bottomStartSmoothing = bottomStartSmoothing
        )
    }

    override fun toString(): String {
        return "RoundedCornerOmniShape(topStart = $topStart, topEnd = $topEnd, bottomEnd = $bottomEnd, bottomStart = " +
                "$bottomStart, topStartSmoothing = $topStartSmoothing, topEndSmoothing = $topEndSmoothing, " +
                "bottomEndSmoothing = $bottomEndSmoothing, bottomStartSmoothing = $bottomStartSmoothing)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RoundedCornerOmniShape

        if (topStart != other.topStart) return false
        if (topEnd != other.topEnd) return false
        if (bottomEnd != other.bottomEnd) return false
        if (bottomStart != other.bottomStart) return false
        if (topStartSmoothing != other.topStartSmoothing) return false
        if (topEndSmoothing != other.topEndSmoothing) return false
        if (bottomEndSmoothing != other.bottomEndSmoothing) return false
        if (bottomStartSmoothing != other.bottomStartSmoothing) return false

        return true
    }

    override fun hashCode(): Int {
        var result = topStart.hashCode()
        result = 31 * result + topEnd.hashCode()
        result = 31 * result + bottomEnd.hashCode()
        result = 31 * result + bottomStart.hashCode()
        result = 31 * result + topStartSmoothing.hashCode()
        result = 31 * result + topEndSmoothing.hashCode()
        result = 31 * result + bottomEndSmoothing.hashCode()
        result = 31 * result + bottomStartSmoothing.hashCode()
        return result
    }

    internal companion object {

        @Stable
        val Zero: RoundedCornerOmniShape =
            RoundedCornerOmniShape(
                topStart = CornerSize(0f),
                topEnd = CornerSize(0f),
                bottomEnd = CornerSize(0f),
                bottomStart = CornerSize(0f)
            )
    }
}

@Stable
val CircleOmniShape: RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        topStartPercent = 50,
        topEndPercent = 50,
        bottomEndPercent = 50,
        bottomStartPercent = 50
    )

@Stable
fun CircleOmniShape(
    @FloatRange(from = 0.0, to = 1.0) smoothing: Float = 0f
): RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        topStartPercent = 50,
        topEndPercent = 50,
        bottomEndPercent = 50,
        bottomStartPercent = 50,
        topStartSmoothing = smoothing,
        topEndSmoothing = smoothing,
        bottomEndSmoothing = smoothing,
        bottomStartSmoothing = smoothing
    )

@Stable
fun RoundedCornerOmniShape(
    corner: CornerSize,
    @FloatRange(from = 0.0, to = 1.0) smoothing: Float = 0f
): RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        topStart = corner,
        topEnd = corner,
        bottomEnd = corner,
        bottomStart = corner,
        topStartSmoothing = smoothing,
        topEndSmoothing = smoothing,
        bottomEndSmoothing = smoothing,
        bottomStartSmoothing = smoothing
    )

@Stable
fun RoundedCornerOmniShape(
    size: Dp,
    @FloatRange(from = 0.0, to = 1.0) smoothing: Float = 0f
): RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        corner = CornerSize(size),
        smoothing = smoothing
    )

@Stable
fun RoundedCornerOmniShape(
    @FloatRange(from = 0.0) size: Float,
    @FloatRange(from = 0.0, to = 1.0) smoothing: Float
): RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        corner = CornerSize(size),
        smoothing = smoothing
    )

@Stable
fun RoundedCornerOmniShape(
    @IntRange(from = 0, to = 100) percent: Int,
    @FloatRange(from = 0.0, to = 1.0) smoothing: Float
): RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        corner = CornerSize(percent),
        smoothing = smoothing
    )

@Stable
fun RoundedCornerOmniShape(
    topStart: Dp = 0.dp,
    topEnd: Dp = 0.dp,
    bottomEnd: Dp = 0.dp,
    bottomStart: Dp = 0.dp,
    @FloatRange(from = 0.0, to = 1.0) smoothing: Float = 0f
): RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        topStart = CornerSize(topStart),
        topEnd = CornerSize(topEnd),
        bottomEnd = CornerSize(bottomEnd),
        bottomStart = CornerSize(bottomStart),
        topStartSmoothing = smoothing,
        topEndSmoothing = smoothing,
        bottomEndSmoothing = smoothing,
        bottomStartSmoothing = smoothing
    )

@Stable
fun RoundedCornerOmniShape(
    topStart: Dp = 0.dp,
    topEnd: Dp = 0.dp,
    bottomEnd: Dp = 0.dp,
    bottomStart: Dp = 0.dp,
    @FloatRange(from = 0.0, to = 1.0) topStartSmoothing: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) topEndSmoothing: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) bottomEndSmoothing: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) bottomStartSmoothing: Float = 0f
): RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        topStart = CornerSize(topStart),
        topEnd = CornerSize(topEnd),
        bottomEnd = CornerSize(bottomEnd),
        bottomStart = CornerSize(bottomStart),
        topStartSmoothing = topStartSmoothing,
        topEndSmoothing = topEndSmoothing,
        bottomEndSmoothing = bottomEndSmoothing,
        bottomStartSmoothing = bottomStartSmoothing
    )

@Stable
fun RoundedCornerOmniShape(
    @FloatRange(from = 0.0) topStart: Float = 0f,
    @FloatRange(from = 0.0) topEnd: Float = 0f,
    @FloatRange(from = 0.0) bottomEnd: Float = 0f,
    @FloatRange(from = 0.0) bottomStart: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) smoothing: Float = 0f
): RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        topStart = CornerSize(topStart),
        topEnd = CornerSize(topEnd),
        bottomEnd = CornerSize(bottomEnd),
        bottomStart = CornerSize(bottomStart),
        topStartSmoothing = smoothing,
        topEndSmoothing = smoothing,
        bottomEndSmoothing = smoothing,
        bottomStartSmoothing = smoothing
    )

@Stable
fun RoundedCornerOmniShape(
    @FloatRange(from = 0.0) topStart: Float = 0f,
    @FloatRange(from = 0.0) topEnd: Float = 0f,
    @FloatRange(from = 0.0) bottomEnd: Float = 0f,
    @FloatRange(from = 0.0) bottomStart: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) topStartSmoothing: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) topEndSmoothing: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) bottomEndSmoothing: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) bottomStartSmoothing: Float = 0f
): RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        topStart = CornerSize(topStart),
        topEnd = CornerSize(topEnd),
        bottomEnd = CornerSize(bottomEnd),
        bottomStart = CornerSize(bottomStart),
        topStartSmoothing = topStartSmoothing,
        topEndSmoothing = topEndSmoothing,
        bottomEndSmoothing = bottomEndSmoothing,
        bottomStartSmoothing = bottomStartSmoothing
    )

@Stable
fun RoundedCornerOmniShape(
    @IntRange(from = 0, to = 100) topStartPercent: Int = 0,
    @IntRange(from = 0, to = 100) topEndPercent: Int = 0,
    @IntRange(from = 0, to = 100) bottomEndPercent: Int = 0,
    @IntRange(from = 0, to = 100) bottomStartPercent: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) smoothing: Float = 0f
): RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        topStart = CornerSize(topStartPercent),
        topEnd = CornerSize(topEndPercent),
        bottomEnd = CornerSize(bottomEndPercent),
        bottomStart = CornerSize(bottomStartPercent),
        topStartSmoothing = smoothing,
        topEndSmoothing = smoothing,
        bottomEndSmoothing = smoothing,
        bottomStartSmoothing = smoothing
    )

@Stable
fun RoundedCornerOmniShape(
    @IntRange(from = 0, to = 100) topStartPercent: Int = 0,
    @IntRange(from = 0, to = 100) topEndPercent: Int = 0,
    @IntRange(from = 0, to = 100) bottomEndPercent: Int = 0,
    @IntRange(from = 0, to = 100) bottomStartPercent: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) topStartSmoothing: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) topEndSmoothing: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) bottomEndSmoothing: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) bottomStartSmoothing: Float = 0f
): RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        topStart = CornerSize(topStartPercent),
        topEnd = CornerSize(topEndPercent),
        bottomEnd = CornerSize(bottomEndPercent),
        bottomStart = CornerSize(bottomStartPercent),
        topStartSmoothing = topStartSmoothing,
        topEndSmoothing = topEndSmoothing,
        bottomEndSmoothing = bottomEndSmoothing,
        bottomStartSmoothing = bottomStartSmoothing
    )
