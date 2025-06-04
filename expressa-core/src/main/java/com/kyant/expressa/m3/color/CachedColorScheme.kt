package com.kyant.expressa.m3.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class CachedColorScheme(
    // primary colors
    override val primary: Color,
    override val onPrimary: Color,
    override val primaryContainer: Color,
    override val onPrimaryContainer: Color,

    // secondary colors
    override val secondary: Color,
    override val onSecondary: Color,
    override val secondaryContainer: Color,
    override val onSecondaryContainer: Color,

    // tertiary colors
    override val tertiary: Color,
    override val onTertiary: Color,
    override val tertiaryContainer: Color,
    override val onTertiaryContainer: Color,

    // error colors
    override val error: Color,
    override val onError: Color,
    override val errorContainer: Color,
    override val onErrorContainer: Color,

    // surface colors
    override val surface: Color,
    override val onSurface: Color,
    override val surfaceVariant: Color,
    override val onSurfaceVariant: Color,
    override val surfaceContainerHighest: Color,
    override val surfaceContainerHigh: Color,
    override val surfaceContainer: Color,
    override val surfaceContainerLow: Color,
    override val surfaceContainerLowest: Color,
    override val inverseSurface: Color,
    override val inverseOnSurface: Color,

    // outline colors
    override val outline: Color,
    override val outlineVariant: Color,

    // add-on primary colors
    override val primaryFixed: Color,
    override val onPrimaryFixed: Color,
    override val primaryFixedDim: Color,
    override val onPrimaryFixedVariant: Color,
    override val inversePrimary: Color,

    // add-on secondary colors
    override val secondaryFixed: Color,
    override val onSecondaryFixed: Color,
    override val secondaryFixedDim: Color,
    override val onSecondaryFixedVariant: Color,

    // add-on tertiary colors
    override val tertiaryFixed: Color,
    override val onTertiaryFixed: Color,
    override val tertiaryFixedDim: Color,
    override val onTertiaryFixedVariant: Color,

    // add-on surface colors
    override val background: Color,
    override val onBackground: Color,
    override val surfaceBright: Color,
    override val surfaceDim: Color,
    override val scrim: Color,
    override val shadow: Color
) : ColorScheme
