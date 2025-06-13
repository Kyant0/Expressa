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
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon

@Immutable
data class ShapeMorph(
    val start: InterpolableShape,
    val end: InterpolableShape
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

        return if (start is RoundedRectangle && end is RoundedRectangle) {
            LerpRoundedRectangle(
                start = start,
                stop = end,
                fraction = fraction
            )
        } else if (start == InterpolableRectangle && end is RoundedRectangle) {
            LerpRoundedRectangle(
                start = RoundedRectangle.Zero,
                stop = end,
                fraction = fraction
            )
        } else if (start is RoundedRectangle && end == InterpolableRectangle) {
            LerpRoundedRectangle(
                start = start,
                stop = RoundedRectangle.Zero,
                fraction = fraction
            )
        } else if (start is InterpolableRoundedPolygon && end is InterpolableRoundedPolygon) {
            val path = _path ?: Path().also { _path = it }
            val startNormalized = _startPolygon ?: start.normalizedPolygon.also { _startPolygon = it }
            val endNormalized = _endPolygon ?: end.normalizedPolygon.also { _endPolygon = it }
            val morph =
                _morph ?: try {
                    Morph(startNormalized, endNormalized).also { _morph = it }
                } catch (_: IllegalArgumentException) {
                    return if (fraction < 0.5f) start else end
                }
            val morphPath = morph.toPath(fraction, path)

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
                            pivotX = 0.5f,
                            pivotY = 0.5f,
                            translationX = size.center.x,
                            translationY = size.center.y,
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
                    val morphPath = morph.toPath(fraction, path)

                    return Outline.Generic(morphPath)
                }
            }
        }
    }
}
