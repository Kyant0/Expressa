package com.kyant.expressa.shape

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.util.lerp
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon

@Immutable
data class ShapeMorph(
    val start: OmniShape,
    val end: OmniShape
) {

    private var _path: Path? = null
    private var _workingPath: Path? = null
    private var _size: Size = Size.Unspecified
    private var _density: Float = Float.NaN
    private var _startPolygon: RoundedPolygon? = null
    private var _endPolygon: RoundedPolygon? = null
    private var _morph: Morph? = null

    @Stable
    fun toShape(fraction: Float): Shape {
        if (fraction == 0f) {
            return start
        } else if (fraction == 1f) {
            return end
        } else if (start == end) {
            return start
        }

        return if (start is RoundedCornerOmniShape && end is RoundedCornerOmniShape) {
            LerpRoundedCornerOmniShape(
                start = start,
                stop = end,
                fraction = fraction
            )
        } else if (start == RectangleOmniShape && end is RoundedCornerOmniShape) {
            LerpRoundedCornerOmniShape(
                start = RoundedCornerOmniShape.Zero,
                stop = end,
                fraction = fraction
            )
        } else if (start is RoundedCornerOmniShape && end == RectangleOmniShape) {
            LerpRoundedCornerOmniShape(
                start = start,
                stop = RoundedCornerOmniShape.Zero,
                fraction = fraction
            )
        } else if (start is RoundedPolygonOmniShape && end is RoundedPolygonOmniShape) {
            val path = _path ?: Path().also { _path = it }
            val startNormalized = _startPolygon ?: start.normalizedPolygon.also { _startPolygon = it }
            val endNormalized = _endPolygon ?: end.normalizedPolygon.also { _endPolygon = it }
            val morph =
                _morph ?: try {
                    Morph(startNormalized, endNormalized).also { _morph = it }
                } catch (_: IllegalArgumentException) {
                    return if (fraction < 0.5f) start else end
                }
            val pivotX =
                lerp(
                    start.transformOrigin.pivotFractionX,
                    end.transformOrigin.pivotFractionX,
                    fraction
                )
            val pivotY =
                lerp(
                    start.transformOrigin.pivotFractionY,
                    end.transformOrigin.pivotFractionY,
                    fraction
                )
            val rotation =
                lerp(
                    startNormalized.getRotation(
                        startAngle = start.startAngle,
                        rotationPivotX = pivotX,
                        rotationPivotY = pivotY
                    ),
                    endNormalized.getRotation(
                        startAngle = end.startAngle,
                        rotationPivotX = pivotX,
                        rotationPivotY = pivotY
                    ),
                    fraction
                )
            val morphPath =
                morph.toPath(
                    progress = fraction,
                    path = path,
                    startAngle = 0,
                    rotationPivotX = pivotX,
                    rotationPivotY = pivotY
                )

            object : Shape {
                override fun createOutline(
                    size: Size,
                    layoutDirection: LayoutDirection,
                    density: Density
                ): Outline {
                    val workingPath = _workingPath ?: Path().also { _workingPath = it }
                    workingPath.rewind()
                    workingPath.addPath(morphPath)

                    workingPath.transform(Matrix().apply {
                        resetToPivotedTransform(
                            pivotX = pivotX,
                            pivotY = pivotY,
                            translationX = size.center.x,
                            translationY = size.center.y,
                            rotationZ = rotation,
                            scaleX = size.width,
                            scaleY = size.height
                        )
                    })

                    return Outline.Generic(workingPath)
                }
            }
        } else {
            val path = _path ?: Path().also { _path = it }

            object : Shape {
                override fun createOutline(
                    size: Size,
                    layoutDirection: LayoutDirection,
                    density: Density
                ): Outline {
                    if (size != _size || density.density != _density) {
                        _size = size
                        _density = density.density
                        _startPolygon = null
                        _endPolygon = null
                        _morph = null
                    }

                    val startPolygon =
                        _startPolygon
                            ?: start.toRoundedPolygon(size, layoutDirection, density.density)
                                .also { _startPolygon = it }
                    val endPolygon =
                        _endPolygon
                            ?: end.toRoundedPolygon(size, layoutDirection, density.density)
                                .also { _endPolygon = it }
                    val morph =
                        _morph ?: try {
                            Morph(startPolygon, endPolygon).also { _morph = it }
                        } catch (_: IllegalArgumentException) {
                            return (if (fraction < 0.5f) start else end)
                                .createOutline(
                                    size = size,
                                    layoutDirection = layoutDirection,
                                    density = density
                                )
                        }
                    val pivotX =
                        lerp(
                            start.transformOrigin.pivotFractionX * size.width,
                            end.transformOrigin.pivotFractionX * size.width,
                            fraction
                        )
                    val pivotY =
                        lerp(
                            start.transformOrigin.pivotFractionY * size.height,
                            end.transformOrigin.pivotFractionY * size.height,
                            fraction
                        )
                    val rotation =
                        lerp(
                            startPolygon.getRotation(
                                startAngle = start.startAngle,
                                rotationPivotX = pivotX,
                                rotationPivotY = pivotY
                            ),
                            endPolygon.getRotation(
                                startAngle = end.startAngle,
                                rotationPivotX = pivotX,
                                rotationPivotY = pivotY
                            ),
                            fraction
                        )
                    val morphPath =
                        morph.toPath(
                            progress = fraction,
                            path = path,
                            startAngle = 0,
                            rotationPivotX = pivotX,
                            rotationPivotY = pivotY
                        )

                    val workingPath = _workingPath ?: Path().also { _workingPath = it }
                    workingPath.rewind()
                    workingPath.addPath(morphPath)

                    workingPath.transform(Matrix().apply {
                        resetToPivotedTransform(
                            pivotX = pivotX,
                            pivotY = pivotY,
                            rotationZ = rotation
                        )
                    })

                    return Outline.Generic(workingPath)
                }
            }
        }
    }
}
