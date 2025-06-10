package com.kyant.expressa.prelude

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.kyant.expressa.m3.LocalColorScheme
import com.kyant.expressa.m3.LocalStaticColors
import com.kyant.expressa.m3.color.StaticColors

inline val accent: Color @Composable @ReadOnlyComposable get() = LocalStaticColors.current.accent
inline val onAccent: Color @Composable @ReadOnlyComposable get() = LocalStaticColors.current.onAccent
inline val accentContainer: Color @Composable @ReadOnlyComposable get() = LocalStaticColors.current.accentContainer
inline val onAccentContainer: Color @Composable @ReadOnlyComposable get() = LocalStaticColors.current.onAccentContainer

inline val staticColors: StaticColors @Composable @ReadOnlyComposable get() = LocalColorScheme.current.asStaticColors()
inline val blueStaticColors: StaticColors @Composable @ReadOnlyComposable get() = LocalColorScheme.current.blue
inline val yellowStaticColors: StaticColors @Composable @ReadOnlyComposable get() = LocalColorScheme.current.yellow
inline val redStaticColors: StaticColors @Composable @ReadOnlyComposable get() = LocalColorScheme.current.red
inline val purpleStaticColors: StaticColors @Composable @ReadOnlyComposable get() = LocalColorScheme.current.purple
inline val blueVariantStaticColors: StaticColors @Composable @ReadOnlyComposable get() = LocalColorScheme.current.blueVariant
inline val cyanStaticColors: StaticColors @Composable @ReadOnlyComposable get() = LocalColorScheme.current.cyan
inline val greenStaticColors: StaticColors @Composable @ReadOnlyComposable get() = LocalColorScheme.current.green
inline val orangeStaticColors: StaticColors @Composable @ReadOnlyComposable get() = LocalColorScheme.current.orange
inline val pinkStaticColors: StaticColors @Composable @ReadOnlyComposable get() = LocalColorScheme.current.pink
