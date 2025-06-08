package com.kyant.expressa.m3

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.kyant.expressa.m3.color.ColorScheme
import com.kyant.expressa.m3.motion.MotionScheme
import com.kyant.expressa.m3.shape.CornerShapes
import com.kyant.expressa.m3.typography.Typography

val LocalColorScheme: ProvidableCompositionLocal<ColorScheme> =
    compositionLocalOf { error("CompositionLocal LocalColorScheme not present") }

val LocalMotionScheme: ProvidableCompositionLocal<MotionScheme> =
    staticCompositionLocalOf { MotionScheme.Expressive }

val LocalCornerShapes: ProvidableCompositionLocal<CornerShapes> =
    staticCompositionLocalOf { CornerShapes.Default }

val LocalTypography: ProvidableCompositionLocal<Typography> =
    staticCompositionLocalOf { Typography.Default }
