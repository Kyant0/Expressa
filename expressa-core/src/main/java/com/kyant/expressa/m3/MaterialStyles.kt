package com.kyant.expressa.m3

import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.staticCompositionLocalOf
import com.kyant.expressa.m3.color.ColorScheme
import com.kyant.expressa.m3.color.ColorSchemeProvider
import com.kyant.expressa.m3.motion.MotionScheme
import com.kyant.expressa.m3.shape.CornerShapes
import com.kyant.expressa.m3.typography.Typography

inline val LocalColorSchemeProvider: CompositionLocal<ColorSchemeProvider>
    get() = localColorSchemeProvider

inline val LocalColorScheme: CompositionLocal<ColorScheme>
    get() = localColorScheme

val LocalMotionScheme: ProvidableCompositionLocal<MotionScheme> =
    staticCompositionLocalOf { MotionScheme.Expressive }

val LocalCornerShapes: ProvidableCompositionLocal<CornerShapes> =
    staticCompositionLocalOf { CornerShapes.Default }

val LocalTypography: ProvidableCompositionLocal<Typography> =
    staticCompositionLocalOf<Typography> { Typography.Default }

@PublishedApi
internal val localColorSchemeProvider: ProvidableCompositionLocal<ColorSchemeProvider> =
    compositionLocalOf { ColorSchemeProvider.Default }

@PublishedApi
internal val localColorScheme: ProvidableCompositionLocal<ColorScheme> =
    compositionLocalOf(policy = referentialEqualityPolicy()) {
        ColorSchemeProvider.Default.toColorScheme()
    }
