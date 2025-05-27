package com.kyant.expressa.prelude

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.kyant.expressa.m3.LocalCornerShapes
import com.kyant.expressa.shape.RoundedCornerOmniShape

val cornerShapeExtraSmall: RoundedCornerOmniShape @Composable @ReadOnlyComposable inline get() = LocalCornerShapes.current.extraSmall
val cornerShapeSmall: RoundedCornerOmniShape @Composable @ReadOnlyComposable inline get() = LocalCornerShapes.current.small
val cornerShapeMedium: RoundedCornerOmniShape @Composable @ReadOnlyComposable inline get() = LocalCornerShapes.current.medium
val cornerShapeLarge: RoundedCornerOmniShape @Composable @ReadOnlyComposable inline get() = LocalCornerShapes.current.large
val cornerShapeLargeIncreased: RoundedCornerOmniShape @Composable @ReadOnlyComposable inline get() = LocalCornerShapes.current.largeIncreased
val cornerShapeExtraLarge: RoundedCornerOmniShape @Composable @ReadOnlyComposable inline get() = LocalCornerShapes.current.extraLarge
val cornerShapeExtraLargeIncreased: RoundedCornerOmniShape @Composable @ReadOnlyComposable inline get() = LocalCornerShapes.current.extraLargeIncreased
val cornerShapeExtraExtraLarge: RoundedCornerOmniShape @Composable @ReadOnlyComposable inline get() = LocalCornerShapes.current.extraExtraLarge
val cornerShapeFull: RoundedCornerOmniShape @Composable @ReadOnlyComposable inline get() = LocalCornerShapes.current.full
