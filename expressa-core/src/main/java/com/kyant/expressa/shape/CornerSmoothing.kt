package com.kyant.expressa.shape

import androidx.annotation.FloatRange
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import kotlin.math.PI
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin

@Immutable
data class CornerSmoothing(
    @param:FloatRange(from = 0.0, to = 1.0) val circleFraction: Float,
    @param:FloatRange(from = 0.0) val extendedFraction: Float
) {

    private val halfPI = (PI / 2f).toFloat()
    private val circleRadians = halfPI * circleFraction
    private val bezierRadians = (halfPI - circleRadians) / 2f

    private val sin = sin(bezierRadians)
    private val cos = cos(bezierRadians)
    private val a = 1f - sin / (1f + cos) // 1f - tan(bezierRadians / 2f), 2/3 at arcsin(0.6)
    private val d = 1.5f * sin / (1f + cos) / (1f + cos)
    private val ad = a + d // minimum 17/18 at arcsin(0.6)

    internal fun Path.topRightCorner0(size: Size, r: Float, dy: Float) {
        val w = size.width
        cubicTo(
            w,
            r * ad,
            w,
            r * a,
            w - r * (1f - cos),
            r * (1f - sin)
        )
    }

    internal fun Path.topRightCircle(size: Size, r: Float) {
        if (circleRadians > 0f) {
            arcToRad(
                rect = Rect(
                    center = Offset(size.width - r, r),
                    radius = r
                ),
                startAngleRadians = -bezierRadians,
                sweepAngleRadians = -circleRadians,
                forceMoveTo = false
            )
        }
    }

    internal fun Path.topRightCorner1(size: Size, r: Float, dx: Float) {
        val w = size.width
        cubicTo(
            w - r * a,
            0f,
            w - r * ad,
            0f,
            w - r - dx,
            0f
        )
    }

    internal fun Path.topLeftCorner1(size: Size, r: Float, dx: Float) {
        cubicTo(
            r * ad,
            0f,
            r * a,
            0f,
            r * (1f - sin),
            r * (1f - cos)
        )
    }

    internal fun Path.topLeftCircle(size: Size, r: Float) {
        if (circleRadians > 0f) {
            arcToRad(
                rect = Rect(
                    center = Offset(r, r),
                    radius = r
                ),
                startAngleRadians = -(halfPI + bezierRadians),
                sweepAngleRadians = -circleRadians,
                forceMoveTo = false
            )
        }
    }

    internal fun Path.topLeftCorner0(size: Size, r: Float, dy: Float) {
        cubicTo(
            0f,
            r * a,
            0f,
            r * ad,
            0f,
            r + dy
        )
    }

    internal fun Path.bottomLeftCorner0(size: Size, r: Float, dy: Float) {
        val h = size.height
        cubicTo(
            0f,
            h - r * ad,
            0f,
            h - r * a,
            r * (1f - cos),
            h - r * (1f - sin)
        )
    }

    internal fun Path.bottomLeftCircle(size: Size, r: Float) {
        if (circleRadians > 0f) {
            arcToRad(
                rect = Rect(
                    center = Offset(r, size.height - r),
                    radius = r
                ),
                startAngleRadians = -(halfPI * 2 + bezierRadians),
                sweepAngleRadians = -circleRadians,
                forceMoveTo = false
            )
        }
    }

    internal fun Path.bottomLeftCorner1(size: Size, r: Float, dx: Float) {
        val h = size.height
        cubicTo(
            r * a,
            h,
            r * ad,
            h,
            r - dx,
            h
        )
    }

    internal fun Path.bottomRightCorner1(size: Size, r: Float, dx: Float) {
        val w = size.width
        val h = size.height
        cubicTo(
            w - r * ad,
            h,
            w - r * a,
            h,
            w - r * (1f - sin),
            h - r * (1f - cos)
        )
    }

    internal fun Path.bottomRightCircle(size: Size, r: Float) {
        if (circleRadians > 0f) {
            arcToRad(
                rect = Rect(
                    center = Offset(size.width - r, size.height - r),
                    radius = r
                ),
                startAngleRadians = -(halfPI * 3 + bezierRadians),
                sweepAngleRadians = -circleRadians,
                forceMoveTo = false
            )
        }
    }

    internal fun Path.bottomRightCorner0(size: Size, r: Float, dy: Float) {
        val w = size.width
        val h = size.height
        cubicTo(
            w,
            h - r * a,
            w,
            h - r * ad,
            w,
            h - r + dy
        )
    }

    companion object {

        // ~= 16.26 deg, bezierRadians = arcsin(0.6)
        private val DefaultCircleFraction = 1f - 2f * asin(0.6f) / (PI / 2f).toFloat()

        private const val DefaultExtendedFraction = 0.75f

        @Stable
        val Default: CornerSmoothing =
            CornerSmoothing(
                circleFraction = DefaultCircleFraction,
                extendedFraction = DefaultExtendedFraction
            )

        @Stable
        val None: CornerSmoothing =
            CornerSmoothing(
                circleFraction = 1f,
                extendedFraction = 0f
            )
    }
}
