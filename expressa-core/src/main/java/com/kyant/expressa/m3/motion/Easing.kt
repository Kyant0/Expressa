package com.kyant.expressa.m3.motion

import android.graphics.Path
import android.view.animation.PathInterpolator
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing

val EaseEmphasized: Easing = { t -> EaseEmphasizedInterpolator.getInterpolation(t) }
val EaseEmphasizedComplement: Easing = { t -> EaseEmphasizedComplementInterpolator.getInterpolation(t) }
val EaseEmphasizedDecelerate: Easing = CubicBezierEasing(0.05f, 0.7f, 0.1f, 1f)
val EaseEmphasizedAccelerate: Easing = CubicBezierEasing(0.3f, 0f, 0.8f, 0.15f)

val EaseStandard: Easing = CubicBezierEasing(0.2f, 0f, 0f, 1f)
val EaseStandardDecelerate: Easing = CubicBezierEasing(0f, 0f, 0f, 1f)
val EaseStandardAccelerate: Easing = CubicBezierEasing(0.3f, 0f, 1f, 1f)

private val EaseEmphasizedInterpolator =
    PathInterpolator(
        Path().apply {
            moveTo(0f, 0f)
            cubicTo(0.05f, 0f, 0.133333f, 0.06f, 0.166666f, 0.4f)
            cubicTo(0.208333f, 0.82f, 0.25f, 1f, 1f, 1f)
        }
    )

private val EaseEmphasizedComplementInterpolator =
    PathInterpolator(
        Path().apply {
            moveTo(0f, 0f)
            cubicTo(0.1217f, 0.0462f, 0.15f, 0.4686f, 0.1667f, 0.66f)
            cubicTo(0.1834f, 0.8878f, 0.1667f, 1f, 1f, 1f)
        }
    )
