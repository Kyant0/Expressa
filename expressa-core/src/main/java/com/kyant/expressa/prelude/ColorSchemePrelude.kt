package com.kyant.expressa.prelude

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.kyant.expressa.m3.LocalColorScheme

inline val primary: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.primary
inline val onPrimary: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onPrimary
inline val primaryContainer: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.primaryContainer
inline val onPrimaryContainer: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onPrimaryContainer

inline val secondary: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.secondary
inline val onSecondary: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onSecondary
inline val secondaryContainer: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.secondaryContainer
inline val onSecondaryContainer: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onSecondaryContainer

inline val tertiary: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.tertiary
inline val onTertiary: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onTertiary
inline val tertiaryContainer: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.tertiaryContainer
inline val onTertiaryContainer: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onTertiaryContainer

inline val error: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.error
inline val onError: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onError
inline val errorContainer: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.errorContainer
inline val onErrorContainer: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onErrorContainer

inline val surface: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.surface
inline val onSurface: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onSurface
inline val surfaceVariant: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.surfaceVariant
inline val onSurfaceVariant: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onSurfaceVariant
inline val surfaceContainerHighest: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.surfaceContainerHighest
inline val surfaceContainerHigh: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.surfaceContainerHigh
inline val surfaceContainer: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.surfaceContainer
inline val surfaceContainerLow: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.surfaceContainerLow
inline val surfaceContainerLowest: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.surfaceContainerLowest
inline val inverseSurface: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.inverseSurface
inline val inverseOnSurface: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.inverseOnSurface

inline val outline: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.outline
inline val outlineVariant: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.outlineVariant

inline val primaryFixed: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.primaryFixed
inline val onPrimaryFixed: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onPrimaryFixed
inline val primaryFixedDim: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.primaryFixedDim
inline val onPrimaryFixedVariant: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onPrimaryFixedVariant
inline val inversePrimary: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.inversePrimary

inline val secondaryFixed: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.secondaryFixed
inline val onSecondaryFixed: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onSecondaryFixed
inline val secondaryFixedDim: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.secondaryFixedDim
inline val onSecondaryFixedVariant: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onSecondaryFixedVariant

inline val tertiaryFixed: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.tertiaryFixed
inline val onTertiaryFixed: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onTertiaryFixed
inline val tertiaryFixedDim: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.tertiaryFixedDim
inline val onTertiaryFixedVariant: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onTertiaryFixedVariant

inline val background: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.background
inline val onBackground: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.onBackground
inline val surfaceBright: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.surfaceBright
inline val surfaceDim: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.surfaceDim
inline val scrim: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.scrim
inline val shadow: Color @Composable @ReadOnlyComposable get() = LocalColorScheme.current.shadow
