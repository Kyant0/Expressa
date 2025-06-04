package com.kyant.expressa.m3.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorScheme(
    // primary colors
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,

    // secondary colors
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,

    // tertiary colors
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,

    // error colors
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,

    // surface colors
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val surfaceContainerHighest: Color,
    val surfaceContainerHigh: Color,
    val surfaceContainer: Color,
    val surfaceContainerLow: Color,
    val surfaceContainerLowest: Color,
    val inverseSurface: Color,
    val inverseOnSurface: Color,

    // outline colors
    val outline: Color,
    val outlineVariant: Color,

    // add-on primary colors
    val primaryFixed: Color,
    val onPrimaryFixed: Color,
    val primaryFixedDim: Color,
    val onPrimaryFixedVariant: Color,
    val inversePrimary: Color,

    // add-on secondary colors
    val secondaryFixed: Color,
    val onSecondaryFixed: Color,
    val secondaryFixedDim: Color,
    val onSecondaryFixedVariant: Color,

    // add-on tertiary colors
    val tertiaryFixed: Color,
    val onTertiaryFixed: Color,
    val tertiaryFixedDim: Color,
    val onTertiaryFixedVariant: Color,

    // add-on surface colors
    val background: Color,
    val onBackground: Color,
    val surfaceBright: Color,
    val surfaceDim: Color,
    val scrim: Color,
    val shadow: Color
)
