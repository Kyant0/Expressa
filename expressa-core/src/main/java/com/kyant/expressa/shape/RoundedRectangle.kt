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
import androidx.compose.ui.geometry.center
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.LayoutDirection.Ltr
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCoerceAtMost
import androidx.compose.ui.util.fastCoerceIn
import kotlin.math.min

@Immutable
open class RoundedRectangle(
    topStart: CornerSize,
    topEnd: CornerSize,
    bottomEnd: CornerSize,
    bottomStart: CornerSize,
    val cornerSmoothing: CornerSmoothing = CornerSmoothing.Default
) :
    CornerBasedShape(
        topStart = topStart,
        topEnd = topEnd,
        bottomEnd = bottomEnd,
        bottomStart = bottomStart
    ) {

    override fun createOutline(
        size: Size,
        topStart: Float,
        topEnd: Float,
        bottomEnd: Float,
        bottomStart: Float,
        layoutDirection: LayoutDirection
    ): Outline {
        if (topStart + topEnd + bottomEnd + bottomStart == 0f) {
            return Outline.Rectangle(size.toRect())
        }

        val (width, height) = size
        val (centerX, centerY) = size.center

        val maxR = min(centerX, centerY)
        val topLeft = (if (layoutDirection == Ltr) topStart else topEnd).fastCoerceIn(0f, maxR)
        val topRight = (if (layoutDirection == Ltr) topEnd else topStart).fastCoerceIn(0f, maxR)
        val bottomRight = (if (layoutDirection == Ltr) bottomEnd else bottomStart).fastCoerceIn(0f, maxR)
        val bottomLeft = (if (layoutDirection == Ltr) bottomStart else bottomEnd).fastCoerceIn(0f, maxR)

        if (cornerSmoothing.circleFraction >= 1f ||
            (width == height &&
                    topLeft == centerX &&
                    topLeft == topRight && bottomLeft == bottomRight)
        ) {
            return Outline.Rounded(
                RoundRect(
                    rect = size.toRect(),
                    topLeft = CornerRadius(topLeft),
                    topRight = CornerRadius(topRight),
                    bottomRight = CornerRadius(bottomRight),
                    bottomLeft = CornerRadius(bottomLeft)
                )
            )
        }

        with(cornerSmoothing) {
            val topRightDy = (topRight * extendedFraction).fastCoerceAtMost(centerY - topRight)
            val topRightDx = (topRight * extendedFraction).fastCoerceAtMost(centerX - topRight)
            val topLeftDx = (topLeft * extendedFraction).fastCoerceAtMost(centerX - topLeft)
            val topLeftDy = (topLeft * extendedFraction).fastCoerceAtMost(centerY - topLeft)
            val bottomLeftDy = (bottomLeft * extendedFraction).fastCoerceAtMost(centerY - bottomLeft)
            val bottomLeftDx = (bottomLeft * extendedFraction).fastCoerceAtMost(centerX - bottomLeft)
            val bottomRightDx = (bottomRight * extendedFraction).fastCoerceAtMost(centerX - bottomRight)
            val bottomRightDy = (bottomRight * extendedFraction).fastCoerceAtMost(centerY - bottomRight)

            return Outline.Generic(
                Path().apply {
                    when {
                        topRight == maxR && topLeft == maxR && bottomLeft == maxR && bottomRight == maxR -> { // capsule
                            if (width > height) {
                                // right circle
                                rightCircle(size, maxR)
                                // top right corner
                                topRightCorner1(size, topRight, topRightDx)
                                // top line
                                lineTo(topLeft + topLeftDx, 0f)
                                // top left corner
                                topLeftCorner1(size, topLeft, topLeftDx)
                                // left circle
                                leftCircle(size, maxR)
                                // bottom left corner
                                bottomLeftCorner1(size, bottomLeft, -bottomLeftDx)
                                // bottom line
                                lineTo(width - bottomRight - bottomRightDx, height)
                                // bottom right corner
                                bottomRightCorner1(size, bottomRight, -bottomRightDx)
                            } else {
                                // right line
                                moveTo(width, height - bottomRight - bottomRightDy)
                                lineTo(width, topRight + topRightDy)
                                // top right corner
                                topRightCorner0(size, topRight, -topRightDy)
                                // top circle
                                topCircle(size, maxR)
                                // top left corner
                                topLeftCorner0(size, topLeft, topLeftDy)
                                // left line
                                lineTo(0f, height - bottomLeft - bottomLeftDy)
                                // bottom left corner
                                bottomLeftCorner0(size, bottomLeft, bottomLeftDy)
                                // bottom circle
                                bottomCircle(size, maxR)
                                // bottom right corner
                                bottomRightCorner0(size, bottomRight, -bottomRightDy)
                            }
                        }

                        else -> {
                            // right line
                            moveTo(width, height - bottomRight - bottomRightDy)
                            lineTo(width, topRight + topRightDy)

                            // top right corner
                            if (topRight > 0f) {
                                topRightCorner0(size, topRight, -topRightDy)
                                topRightCircle(size, topRight)
                                topRightCorner1(size, topRight, topRightDx)
                            }

                            // top line
                            lineTo(topLeft + topLeftDx, 0f)

                            // top left corner
                            if (topLeft > 0f) {
                                topLeftCorner1(size, topLeft, topLeftDx)
                                topLeftCircle(size, topLeft)
                                topLeftCorner0(size, topLeft, topLeftDy)
                            }

                            // left line
                            lineTo(0f, height - bottomLeft - bottomLeftDy)

                            // bottom left corner
                            if (bottomLeft > 0f) {
                                bottomLeftCorner0(size, bottomLeft, bottomLeftDy)
                                bottomLeftCircle(size, bottomLeft)
                                bottomLeftCorner1(size, bottomLeft, -bottomLeftDx)
                            }

                            // bottom line
                            lineTo(width - bottomRight - bottomRightDx, height)

                            // bottom right corner
                            if (bottomRight > 0f) {
                                bottomRightCorner1(size, bottomRight, -bottomRightDx)
                                bottomRightCircle(size, bottomRight)
                                bottomRightCorner0(size, bottomRight, -bottomRightDy)
                            }
                        }
                    }
                }
            )
        }
    }

    override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize
    ): RoundedRectangle {
        return RoundedRectangle(
            topStart = topStart,
            topEnd = topEnd,
            bottomEnd = bottomEnd,
            bottomStart = bottomStart,
            cornerSmoothing = cornerSmoothing
        )
    }

    fun copy(
        topStart: CornerSize = this.topStart,
        topEnd: CornerSize = this.topEnd,
        bottomEnd: CornerSize = this.bottomEnd,
        bottomStart: CornerSize = this.bottomStart,
        cornerSmoothing: CornerSmoothing = this.cornerSmoothing
    ): RoundedRectangle {
        return RoundedRectangle(
            topStart = topStart,
            topEnd = topEnd,
            bottomEnd = bottomEnd,
            bottomStart = bottomStart,
            cornerSmoothing = cornerSmoothing
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RoundedRectangle) return false

        if (topStart != other.topStart) return false
        if (topEnd != other.topEnd) return false
        if (bottomEnd != other.bottomEnd) return false
        if (bottomStart != other.bottomStart) return false
        if (cornerSmoothing != other.cornerSmoothing) return false

        return true
    }

    override fun hashCode(): Int {
        var result = topStart.hashCode()
        result = 31 * result + topEnd.hashCode()
        result = 31 * result + bottomEnd.hashCode()
        result = 31 * result + bottomStart.hashCode()
        result = 31 * result + cornerSmoothing.hashCode()
        return result
    }

    override fun toString(): String {
        return "RoundedRectangle(topStart=$topStart, topEnd=$topEnd, bottomEnd=$bottomEnd, " +
                "bottomStart=$bottomStart, cornerSmoothing=$cornerSmoothing)"
    }
}

@Stable
val RectangleShape: RoundedRectangle = RoundedRectangle(0f)

@Stable
val CapsuleShape: RoundedRectangle = CapsuleShape()

@Suppress("FunctionName")
@Stable
fun CapsuleShape(
    cornerSmoothing: CornerSmoothing = CornerSmoothing.Default
): RoundedRectangle =
    RoundedRectangle(
        topStartPercent = 50,
        topEndPercent = 50,
        bottomEndPercent = 50,
        bottomStartPercent = 50,
        cornerSmoothing = cornerSmoothing
    )

@Stable
fun RoundedRectangle(
    corner: CornerSize,
    cornerSmoothing: CornerSmoothing = CornerSmoothing.Default
): RoundedRectangle =
    RoundedRectangle(
        topStart = corner,
        topEnd = corner,
        bottomEnd = corner,
        bottomStart = corner,
        cornerSmoothing = cornerSmoothing
    )

@Stable
fun RoundedRectangle(
    size: Dp,
    cornerSmoothing: CornerSmoothing = CornerSmoothing.Default
): RoundedRectangle =
    RoundedRectangle(
        corner = CornerSize(size),
        cornerSmoothing = cornerSmoothing
    )

@Stable
fun RoundedRectangle(
    @FloatRange(from = 0.0) size: Float,
    cornerSmoothing: CornerSmoothing = CornerSmoothing.Default
): RoundedRectangle =
    RoundedRectangle(
        corner = CornerSize(size),
        cornerSmoothing = cornerSmoothing
    )

@Stable
fun RoundedRectangle(
    @IntRange(from = 0, to = 100) percent: Int,
    cornerSmoothing: CornerSmoothing = CornerSmoothing.Default
): RoundedRectangle =
    RoundedRectangle(
        corner = CornerSize(percent),
        cornerSmoothing = cornerSmoothing
    )

@Stable
fun RoundedRectangle(
    topStart: Dp = 0.dp,
    topEnd: Dp = 0.dp,
    bottomEnd: Dp = 0.dp,
    bottomStart: Dp = 0.dp,
    cornerSmoothing: CornerSmoothing = CornerSmoothing.Default
): RoundedRectangle =
    RoundedRectangle(
        topStart = CornerSize(topStart),
        topEnd = CornerSize(topEnd),
        bottomEnd = CornerSize(bottomEnd),
        bottomStart = CornerSize(bottomStart),
        cornerSmoothing = cornerSmoothing
    )

@Stable
fun RoundedRectangle(
    @FloatRange(from = 0.0) topStart: Float = 0f,
    @FloatRange(from = 0.0) topEnd: Float = 0f,
    @FloatRange(from = 0.0) bottomEnd: Float = 0f,
    @FloatRange(from = 0.0) bottomStart: Float = 0f,
    cornerSmoothing: CornerSmoothing = CornerSmoothing.Default
): RoundedRectangle =
    RoundedRectangle(
        topStart = CornerSize(topStart),
        topEnd = CornerSize(topEnd),
        bottomEnd = CornerSize(bottomEnd),
        bottomStart = CornerSize(bottomStart),
        cornerSmoothing = cornerSmoothing
    )

@Stable
fun RoundedRectangle(
    @IntRange(from = 0, to = 100) topStartPercent: Int = 0,
    @IntRange(from = 0, to = 100) topEndPercent: Int = 0,
    @IntRange(from = 0, to = 100) bottomEndPercent: Int = 0,
    @IntRange(from = 0, to = 100) bottomStartPercent: Int = 0,
    cornerSmoothing: CornerSmoothing = CornerSmoothing.Default
): RoundedRectangle =
    RoundedRectangle(
        topStart = CornerSize(topStartPercent),
        topEnd = CornerSize(topEndPercent),
        bottomEnd = CornerSize(bottomEndPercent),
        bottomStart = CornerSize(bottomStartPercent),
        cornerSmoothing = cornerSmoothing,
    )
