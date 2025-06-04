package com.kyant.expressa.m3.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.kyant.m3color.dynamiccolor.DynamicScheme

@JvmInline
@Immutable
value class LazyColorScheme(
    val dynamicScheme: DynamicScheme
) : ColorScheme {

    // primary colors

    @Stable
    override val primary: Color
        get() = Color(dynamicScheme.primary)

    @Stable
    override val onPrimary: Color
        get() = Color(dynamicScheme.onPrimary)

    @Stable
    override val primaryContainer: Color
        get() = Color(dynamicScheme.primaryContainer)

    @Stable
    override val onPrimaryContainer: Color
        get() = Color(dynamicScheme.onPrimaryContainer)

    // secondary colors

    @Stable
    override val secondary: Color
        get() = Color(dynamicScheme.secondary)

    @Stable
    override val onSecondary: Color
        get() = Color(dynamicScheme.onSecondary)

    @Stable
    override val secondaryContainer: Color
        get() = Color(dynamicScheme.secondaryContainer)

    @Stable
    override val onSecondaryContainer: Color
        get() = Color(dynamicScheme.onSecondaryContainer)

    // tertiary colors

    @Stable
    override val tertiary: Color
        get() = Color(dynamicScheme.tertiary)

    @Stable
    override val onTertiary: Color
        get() = Color(dynamicScheme.onTertiary)

    @Stable
    override val tertiaryContainer: Color
        get() = Color(dynamicScheme.tertiaryContainer)

    @Stable
    override val onTertiaryContainer: Color
        get() = Color(dynamicScheme.onTertiaryContainer)

    // error colors

    @Stable
    override val error: Color
        get() = Color(dynamicScheme.error)

    @Stable
    override val onError: Color
        get() = Color(dynamicScheme.onError)

    @Stable
    override val errorContainer: Color
        get() = Color(dynamicScheme.errorContainer)

    @Stable
    override val onErrorContainer: Color
        get() = Color(dynamicScheme.onErrorContainer)

    // surface colors

    @Stable
    override val surface: Color
        get() = Color(dynamicScheme.surface)

    @Stable
    override val onSurface: Color
        get() = Color(dynamicScheme.onSurface)

    @Stable
    override val surfaceVariant: Color
        get() = Color(dynamicScheme.surfaceVariant)

    @Stable
    override val onSurfaceVariant: Color
        get() = Color(dynamicScheme.onSurfaceVariant)

    @Stable
    override val surfaceContainerHighest: Color
        get() = Color(dynamicScheme.surfaceContainerHighest)

    @Stable
    override val surfaceContainerHigh: Color
        get() = Color(dynamicScheme.surfaceContainerHigh)

    @Stable
    override val surfaceContainer: Color
        get() = Color(dynamicScheme.surfaceContainer)

    @Stable
    override val surfaceContainerLow: Color
        get() = Color(dynamicScheme.surfaceContainerLow)

    @Stable
    override val surfaceContainerLowest: Color
        get() = Color(dynamicScheme.surfaceContainerLowest)

    @Stable
    override val inverseSurface: Color
        get() = Color(dynamicScheme.inverseSurface)

    @Stable
    override val inverseOnSurface: Color
        get() = Color(dynamicScheme.inverseOnSurface)

    // outline colors

    @Stable
    override val outline: Color
        get() = Color(dynamicScheme.outline)

    @Stable
    override val outlineVariant: Color
        get() = Color(dynamicScheme.outlineVariant)

    // add-on primary colors

    @Stable
    override val primaryFixed: Color
        get() = Color(dynamicScheme.primaryFixed)

    @Stable
    override val onPrimaryFixed: Color
        get() = Color(dynamicScheme.onPrimaryFixed)

    @Stable
    override val primaryFixedDim: Color
        get() = Color(dynamicScheme.primaryFixedDim)

    @Stable
    override val onPrimaryFixedVariant: Color
        get() = Color(dynamicScheme.onPrimaryFixedVariant)

    @Stable
    override val inversePrimary: Color
        get() = Color(dynamicScheme.inversePrimary)

    // add-on secondary colors

    @Stable
    override val secondaryFixed: Color
        get() = Color(dynamicScheme.secondaryFixed)

    @Stable
    override val onSecondaryFixed: Color
        get() = Color(dynamicScheme.onSecondaryFixed)

    @Stable
    override val secondaryFixedDim: Color
        get() = Color(dynamicScheme.secondaryFixedDim)

    @Stable
    override val onSecondaryFixedVariant: Color
        get() = Color(dynamicScheme.onSecondaryFixedVariant)

    // add-on tertiary colors

    @Stable
    override val tertiaryFixed: Color
        get() = Color(dynamicScheme.tertiaryFixed)

    @Stable
    override val onTertiaryFixed: Color
        get() = Color(dynamicScheme.onTertiaryFixed)

    @Stable
    override val tertiaryFixedDim: Color
        get() = Color(dynamicScheme.tertiaryFixedDim)

    @Stable
    override val onTertiaryFixedVariant: Color
        get() = Color(dynamicScheme.onTertiaryFixedVariant)

    // add-on surface colors

    @Stable
    override val background: Color
        get() = Color(dynamicScheme.background)

    @Stable
    override val onBackground: Color
        get() = Color(dynamicScheme.onBackground)

    @Stable
    override val surfaceBright: Color
        get() = Color(dynamicScheme.surfaceBright)

    @Stable
    override val surfaceDim: Color
        get() = Color(dynamicScheme.surfaceDim)

    @Stable
    override val scrim: Color
        get() = Color(dynamicScheme.scrim)

    @Stable
    override val shadow: Color
        get() = Color(dynamicScheme.shadow)
}
