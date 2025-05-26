package com.kyant.expressa.shape

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.util.fastForEach
import androidx.graphics.shapes.Cubic
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.TransformResult
import kotlin.math.PI
import kotlin.math.atan2

internal fun RoundedPolygon.transformed(matrix: Matrix): RoundedPolygon =
    transformed { x, y ->
        val transformedPoint = matrix.map(Offset(x, y))
        TransformResult(transformedPoint.x, transformedPoint.y)
    }

internal fun RoundedPolygon.toPath(
    path: Path = Path(),
    startAngle: Int = 0,
    rotationPivotX: Float = centerX,
    rotationPivotY: Float = centerY
): Path {
    var first = true
    var firstCubic: Cubic? = null

    path.rewind()

    cubics.fastForEach { cubic ->
        if (first) {
            path.moveTo(cubic.anchor0X, cubic.anchor0Y)
            if (startAngle != 0) {
                firstCubic = cubic
            }
            first = false
        }
        path.cubicTo(
            cubic.control0X,
            cubic.control0Y,
            cubic.control1X,
            cubic.control1Y,
            cubic.anchor1X,
            cubic.anchor1Y
        )
    }

    path.close()

    if (startAngle != 0 && firstCubic != null) {
        val angleToFirstCubic =
            radiansToDegrees(
                atan2(
                    y = firstCubic.anchor0Y - rotationPivotY,
                    x = firstCubic.anchor0X - rotationPivotX
                )
            )
        // Rotate the Path to to start from the given angle.
        path.transform(Matrix().apply {
            resetToPivotedTransform(
                pivotX = rotationPivotX,
                pivotY = rotationPivotY,
                rotationZ = -angleToFirstCubic + startAngle
            )
        })
    }

    return path
}

internal fun Morph.toPath(
    progress: Float,
    path: Path = Path(),
    startAngle: Int = 0,
    rotationPivotX: Float = 0f,
    rotationPivotY: Float = 0f,
): Path {
    var first = true
    var firstCubic: Cubic? = null

    path.rewind()

    forEachCubic(progress) { cubic ->
        if (first) {
            path.moveTo(cubic.anchor0X, cubic.anchor0Y)
            if (startAngle != 0) {
                firstCubic = cubic
            }
            first = false
        }
        path.cubicTo(
            cubic.control0X,
            cubic.control0Y,
            cubic.control1X,
            cubic.control1Y,
            cubic.anchor1X,
            cubic.anchor1Y
        )
    }

    path.close()

    if (startAngle != 0 && firstCubic != null) {
        val angleToFirstCubic =
            radiansToDegrees(
                atan2(
                    firstCubic.anchor0Y - rotationPivotY,
                    firstCubic.anchor0X - rotationPivotX
                )
            )

        path.transform(Matrix().apply {
            resetToPivotedTransform(
                pivotX = rotationPivotX,
                pivotY = rotationPivotY,
                rotationZ = -angleToFirstCubic + startAngle
            )
        })
    }

    return path
}

internal fun RoundedPolygon.getRotation(
    startAngle: Int,
    rotationPivotX: Float = centerX,
    rotationPivotY: Float = centerY
): Float {
    val firstCubic = cubics.firstOrNull()

    return if (startAngle != 0 && firstCubic != null) {
        val angleToFirstCubic =
            radiansToDegrees(
                atan2(
                    firstCubic.anchor0Y - rotationPivotY,
                    firstCubic.anchor0X - rotationPivotX
                )
            )

        -angleToFirstCubic + startAngle
    } else {
        0f
    }
}

private fun radiansToDegrees(radians: Float): Float {
    return (radians * 180.0 / PI).toFloat()
}
