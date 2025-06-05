package com.kyant.expressa.prelude

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.kyant.expressa.m3.LocalCornerShapes
import com.kyant.expressa.shape.RoundedCornerOmniShape

inline val cornerShapeExtraSmall: RoundedCornerOmniShape @Composable @ReadOnlyComposable get() = LocalCornerShapes.current.extraSmall
inline val cornerShapeSmall: RoundedCornerOmniShape @Composable @ReadOnlyComposable get() = LocalCornerShapes.current.small
inline val cornerShapeMedium: RoundedCornerOmniShape @Composable @ReadOnlyComposable get() = LocalCornerShapes.current.medium
inline val cornerShapeLarge: RoundedCornerOmniShape @Composable @ReadOnlyComposable get() = LocalCornerShapes.current.large
inline val cornerShapeLargeIncreased: RoundedCornerOmniShape @Composable @ReadOnlyComposable get() = LocalCornerShapes.current.largeIncreased
inline val cornerShapeExtraLarge: RoundedCornerOmniShape @Composable @ReadOnlyComposable get() = LocalCornerShapes.current.extraLarge
inline val cornerShapeExtraLargeIncreased: RoundedCornerOmniShape @Composable @ReadOnlyComposable get() = LocalCornerShapes.current.extraLargeIncreased
inline val cornerShapeExtraExtraLarge: RoundedCornerOmniShape @Composable @ReadOnlyComposable get() = LocalCornerShapes.current.extraExtraLarge
inline val cornerShapeFull: RoundedCornerOmniShape @Composable @ReadOnlyComposable get() = LocalCornerShapes.current.full
