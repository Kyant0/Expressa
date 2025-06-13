package com.kyant.expressa.m3

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.kyant.expressa.m3.color.ColorScheme
import com.kyant.expressa.m3.color.StaticColors

val LocalColorScheme: ProvidableCompositionLocal<ColorScheme> =
    compositionLocalOf { error("CompositionLocal LocalColorScheme not present") }

val LocalStaticColors: ProvidableCompositionLocal<StaticColors> =
    compositionLocalOf { error("CompositionLocal LocalStaticColors not present") }
