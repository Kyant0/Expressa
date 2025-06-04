package com.kyant.expressa.m3.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.kyant.m3color.dynamiccolor.DynamicScheme

@Immutable
data class LazyColorScheme(
    val dynamicScheme: DynamicScheme
) : ColorScheme {

    // primary colors
    private var _primary: Color? = null
    private var _onPrimary: Color? = null
    private var _primaryContainer: Color? = null
    private var _onPrimaryContainer: Color? = null

    // secondary colors
    private var _secondary: Color? = null
    private var _onSecondary: Color? = null
    private var _secondaryContainer: Color? = null
    private var _onSecondaryContainer: Color? = null

    // tertiary colors
    private var _tertiary: Color? = null
    private var _onTertiary: Color? = null
    private var _tertiaryContainer: Color? = null
    private var _onTertiaryContainer: Color? = null

    // error colors
    private var _error: Color? = null
    private var _onError: Color? = null
    private var _errorContainer: Color? = null
    private var _onErrorContainer: Color? = null

    // surface colors
    private var _surface: Color? = null
    private var _onSurface: Color? = null
    private var _surfaceVariant: Color? = null
    private var _onSurfaceVariant: Color? = null
    private var _surfaceContainerHighest: Color? = null
    private var _surfaceContainerHigh: Color? = null
    private var _surfaceContainer: Color? = null
    private var _surfaceContainerLow: Color? = null
    private var _surfaceContainerLowest: Color? = null
    private var _inverseSurface: Color? = null
    private var _inverseOnSurface: Color? = null

    // outline colors
    private var _outline: Color? = null
    private var _outlineVariant: Color? = null

    // add-on primary colors
    private var _primaryFixed: Color? = null
    private var _onPrimaryFixed: Color? = null
    private var _primaryFixedDim: Color? = null
    private var _onPrimaryFixedVariant: Color? = null
    private var _inversePrimary: Color? = null

    // add-on secondary colors
    private var _secondaryFixed: Color? = null
    private var _onSecondaryFixed: Color? = null
    private var _secondaryFixedDim: Color? = null
    private var _onSecondaryFixedVariant: Color? = null

    // add-on tertiary colors
    private var _tertiaryFixed: Color? = null
    private var _onTertiaryFixed: Color? = null
    private var _tertiaryFixedDim: Color? = null
    private var _onTertiaryFixedVariant: Color? = null

    // add-on surface colors
    private var _background: Color? = null
    private var _onBackground: Color? = null
    private var _surfaceBright: Color? = null
    private var _surfaceDim: Color? = null
    private var _scrim: Color? = null
    private var _shadow: Color? = null

    // primary colors

    @Stable
    override val primary: Color
        get() = _primary
            ?: Color(dynamicScheme.primary).also { _primary = it }

    @Stable
    override val onPrimary: Color
        get() = _onPrimary
            ?: Color(dynamicScheme.onPrimary).also { _onPrimary = it }

    @Stable
    override val primaryContainer: Color
        get() = _primaryContainer
            ?: Color(dynamicScheme.primaryContainer).also { _primaryContainer = it }

    @Stable
    override val onPrimaryContainer: Color
        get() = _onPrimaryContainer
            ?: Color(dynamicScheme.onPrimaryContainer).also { _onPrimaryContainer = it }

    // secondary colors

    @Stable
    override val secondary: Color
        get() = _secondary
            ?: Color(dynamicScheme.secondary).also { _secondary = it }

    @Stable
    override val onSecondary: Color
        get() = _onSecondary
            ?: Color(dynamicScheme.onSecondary).also { _onSecondary = it }

    @Stable
    override val secondaryContainer: Color
        get() = _secondaryContainer
            ?: Color(dynamicScheme.secondaryContainer).also { _secondaryContainer = it }

    @Stable
    override val onSecondaryContainer: Color
        get() = _onSecondaryContainer
            ?: Color(dynamicScheme.onSecondaryContainer).also { _onSecondaryContainer = it }

    // tertiary colors

    @Stable
    override val tertiary: Color
        get() = _tertiary
            ?: Color(dynamicScheme.tertiary).also { _tertiary = it }

    @Stable
    override val onTertiary: Color
        get() = _onTertiary
            ?: Color(dynamicScheme.onTertiary).also { _onTertiary = it }

    @Stable
    override val tertiaryContainer: Color
        get() = _tertiaryContainer
            ?: Color(dynamicScheme.tertiaryContainer).also { _tertiaryContainer = it }

    @Stable
    override val onTertiaryContainer: Color
        get() = _onTertiaryContainer
            ?: Color(dynamicScheme.onTertiaryContainer).also { _onTertiaryContainer = it }

    // error colors

    @Stable
    override val error: Color
        get() = _error
            ?: Color(dynamicScheme.error).also { _error = it }

    @Stable
    override val onError: Color
        get() = _onError
            ?: Color(dynamicScheme.onError).also { _onError = it }

    @Stable
    override val errorContainer: Color
        get() = _errorContainer
            ?: Color(dynamicScheme.errorContainer).also { _errorContainer = it }

    @Stable
    override val onErrorContainer: Color
        get() = _onErrorContainer
            ?: Color(dynamicScheme.onErrorContainer).also { _onErrorContainer = it }

    // surface colors

    @Stable
    override val surface: Color
        get() = _surface
            ?: Color(dynamicScheme.surface).also { _surface = it }

    @Stable
    override val onSurface: Color
        get() = _onSurface
            ?: Color(dynamicScheme.onSurface).also { _onSurface = it }

    @Stable
    override val surfaceVariant: Color
        get() = _surfaceVariant
            ?: Color(dynamicScheme.surfaceVariant).also { _surfaceVariant = it }

    @Stable
    override val onSurfaceVariant: Color
        get() = _onSurfaceVariant
            ?: Color(dynamicScheme.onSurfaceVariant).also { _onSurfaceVariant = it }

    @Stable
    override val surfaceContainerHighest: Color
        get() = _surfaceContainerHighest
            ?: Color(dynamicScheme.surfaceContainerHighest).also { _surfaceContainerHighest = it }

    @Stable
    override val surfaceContainerHigh: Color
        get() = _surfaceContainerHigh
            ?: Color(dynamicScheme.surfaceContainerHigh).also { _surfaceContainerHigh = it }

    @Stable
    override val surfaceContainer: Color
        get() = _surfaceContainer
            ?: Color(dynamicScheme.surfaceContainer).also { _surfaceContainer = it }

    @Stable
    override val surfaceContainerLow: Color
        get() = _surfaceContainerLow
            ?: Color(dynamicScheme.surfaceContainerLow).also { _surfaceContainerLow = it }

    @Stable
    override val surfaceContainerLowest: Color
        get() = _surfaceContainerLowest
            ?: Color(dynamicScheme.surfaceContainerLowest).also { _surfaceContainerLowest = it }

    @Stable
    override val inverseSurface: Color
        get() = _inverseSurface
            ?: Color(dynamicScheme.inverseSurface).also { _inverseSurface = it }

    @Stable
    override val inverseOnSurface: Color
        get() = _inverseOnSurface
            ?: Color(dynamicScheme.inverseOnSurface).also { _inverseOnSurface = it }

    // outline colors

    @Stable
    override val outline: Color
        get() = _outline
            ?: Color(dynamicScheme.outline).also { _outline = it }

    @Stable
    override val outlineVariant: Color
        get() = _outlineVariant
            ?: Color(dynamicScheme.outlineVariant).also { _outlineVariant = it }

    // add-on primary colors

    @Stable
    override val primaryFixed: Color
        get() = _primaryFixed
            ?: Color(dynamicScheme.primaryFixed).also { _primaryFixed = it }

    @Stable
    override val onPrimaryFixed: Color
        get() = _onPrimaryFixed
            ?: Color(dynamicScheme.onPrimaryFixed).also { _onPrimaryFixed = it }

    @Stable
    override val primaryFixedDim: Color
        get() = _primaryFixedDim
            ?: Color(dynamicScheme.primaryFixedDim).also { _primaryFixedDim = it }

    @Stable
    override val onPrimaryFixedVariant: Color
        get() = _onPrimaryFixedVariant
            ?: Color(dynamicScheme.onPrimaryFixedVariant).also { _onPrimaryFixedVariant = it }

    @Stable
    override val inversePrimary: Color
        get() = _inversePrimary
            ?: Color(dynamicScheme.inversePrimary).also { _inversePrimary = it }

    // add-on secondary colors

    @Stable
    override val secondaryFixed: Color
        get() = _secondaryFixed
            ?: Color(dynamicScheme.secondaryFixed).also { _secondaryFixed = it }

    @Stable
    override val onSecondaryFixed: Color
        get() = _onSecondaryFixed
            ?: Color(dynamicScheme.onSecondaryFixed).also { _onSecondaryFixed = it }

    @Stable
    override val secondaryFixedDim: Color
        get() = _secondaryFixedDim
            ?: Color(dynamicScheme.secondaryFixedDim).also { _secondaryFixedDim = it }

    @Stable
    override val onSecondaryFixedVariant: Color
        get() = _onSecondaryFixedVariant
            ?: Color(dynamicScheme.onSecondaryFixedVariant).also { _onSecondaryFixedVariant = it }

    // add-on tertiary colors

    @Stable
    override val tertiaryFixed: Color
        get() = _tertiaryFixed
            ?: Color(dynamicScheme.tertiaryFixed).also { _tertiaryFixed = it }

    @Stable
    override val onTertiaryFixed: Color
        get() = _onTertiaryFixed
            ?: Color(dynamicScheme.onTertiaryFixed).also { _onTertiaryFixed = it }

    @Stable
    override val tertiaryFixedDim: Color
        get() = _tertiaryFixedDim
            ?: Color(dynamicScheme.tertiaryFixedDim).also { _tertiaryFixedDim = it }

    @Stable
    override val onTertiaryFixedVariant: Color
        get() = _onTertiaryFixedVariant
            ?: Color(dynamicScheme.onTertiaryFixedVariant).also { _onTertiaryFixedVariant = it }

    // add-on surface colors

    @Stable
    override val background: Color
        get() = _background
            ?: Color(dynamicScheme.background).also { _background = it }

    @Stable
    override val onBackground: Color
        get() = _onBackground
            ?: Color(dynamicScheme.onBackground).also { _onBackground = it }

    @Stable
    override val surfaceBright: Color
        get() = _surfaceBright
            ?: Color(dynamicScheme.surfaceBright).also { _surfaceBright = it }

    @Stable
    override val surfaceDim: Color
        get() = _surfaceDim
            ?: Color(dynamicScheme.surfaceDim).also { _surfaceDim = it }

    @Stable
    override val scrim: Color
        get() = _scrim
            ?: Color(dynamicScheme.scrim).also { _scrim = it }

    @Stable
    override val shadow: Color
        get() = _shadow
            ?: Color(dynamicScheme.shadow).also { _shadow = it }
}
