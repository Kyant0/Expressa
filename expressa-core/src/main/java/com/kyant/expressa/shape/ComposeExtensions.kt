package com.kyant.expressa.shape

import androidx.annotation.FloatRange
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.TransformOrigin
import androidx.graphics.shapes.RoundedPolygon

@Stable
fun RoundedCornerShape.toOmniShape(
    @FloatRange(from = 0.0, to = 1.0) smoothing: Float = 0f
): RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        topStart = topStart,
        topEnd = topEnd,
        bottomEnd = bottomEnd,
        bottomStart = bottomStart,
        topStartSmoothing = smoothing,
        topEndSmoothing = smoothing,
        bottomEndSmoothing = smoothing,
        bottomStartSmoothing = smoothing
    )

@Stable
fun RoundedCornerShape.toOmniShape(
    @FloatRange(from = 0.0, to = 1.0) topStartSmoothing: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) topEndSmoothing: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) bottomEndSmoothing: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) bottomStartSmoothing: Float = 0f
): RoundedCornerOmniShape =
    RoundedCornerOmniShape(
        topStart = topStart,
        topEnd = topEnd,
        bottomEnd = bottomEnd,
        bottomStart = bottomStart,
        topStartSmoothing = topStartSmoothing,
        topEndSmoothing = topEndSmoothing,
        bottomEndSmoothing = bottomEndSmoothing,
        bottomStartSmoothing = bottomStartSmoothing
    )

@Stable
fun RoundedPolygon.toOmniShape(
    startAngle: Int = 0,
    transformOrigin: TransformOrigin = TransformOrigin.Center
): RoundedPolygonOmniShape =
    RoundedPolygonOmniShape(
        roundedPolygon = this,
        startAngle = startAngle,
        transformOrigin = transformOrigin
    )
