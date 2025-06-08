package com.kyant.expressa.m3.color

import android.app.UiModeManager
import android.content.Context
import android.os.Build
import androidx.annotation.FloatRange
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.kyant.expressa.graphics.Hct
import com.kyant.expressa.graphics.Hct.Companion.toHct
import com.kyant.expressa.mcu.DynamicScheme
import com.kyant.expressa.mcu.DynamicSchemeVariant

@Immutable
data class ColorScheme(
    val sourceHct: Hct,
    val variant: DynamicSchemeVariant,
    val isDark: Boolean,
    @param:FloatRange(from = -1.0, to = 1.0) val contrastLevel: Float
) {

    private val dynamicScheme: DynamicScheme =
        DynamicScheme(
            sourceHct = sourceHct,
            variant = variant,
            isDark = isDark,
            contrastLevel = contrastLevel.toDouble()
        )

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
    val primary: Color
        get() = _primary
            ?: Color(dynamicScheme.primary).also { _primary = it }

    @Stable
    val onPrimary: Color
        get() = _onPrimary
            ?: Color(dynamicScheme.onPrimary).also { _onPrimary = it }

    @Stable
    val primaryContainer: Color
        get() = _primaryContainer
            ?: Color(dynamicScheme.primaryContainer).also { _primaryContainer = it }

    @Stable
    val onPrimaryContainer: Color
        get() = _onPrimaryContainer
            ?: Color(dynamicScheme.onPrimaryContainer).also { _onPrimaryContainer = it }

    // secondary colors

    @Stable
    val secondary: Color
        get() = _secondary
            ?: Color(dynamicScheme.secondary).also { _secondary = it }

    @Stable
    val onSecondary: Color
        get() = _onSecondary
            ?: Color(dynamicScheme.onSecondary).also { _onSecondary = it }

    @Stable
    val secondaryContainer: Color
        get() = _secondaryContainer
            ?: Color(dynamicScheme.secondaryContainer).also { _secondaryContainer = it }

    @Stable
    val onSecondaryContainer: Color
        get() = _onSecondaryContainer
            ?: Color(dynamicScheme.onSecondaryContainer).also { _onSecondaryContainer = it }

    // tertiary colors

    @Stable
    val tertiary: Color
        get() = _tertiary
            ?: Color(dynamicScheme.tertiary).also { _tertiary = it }

    @Stable
    val onTertiary: Color
        get() = _onTertiary
            ?: Color(dynamicScheme.onTertiary).also { _onTertiary = it }

    @Stable
    val tertiaryContainer: Color
        get() = _tertiaryContainer
            ?: Color(dynamicScheme.tertiaryContainer).also { _tertiaryContainer = it }

    @Stable
    val onTertiaryContainer: Color
        get() = _onTertiaryContainer
            ?: Color(dynamicScheme.onTertiaryContainer).also { _onTertiaryContainer = it }

    // error colors

    @Stable
    val error: Color
        get() = _error
            ?: Color(dynamicScheme.error).also { _error = it }

    @Stable
    val onError: Color
        get() = _onError
            ?: Color(dynamicScheme.onError).also { _onError = it }

    @Stable
    val errorContainer: Color
        get() = _errorContainer
            ?: Color(dynamicScheme.errorContainer).also { _errorContainer = it }

    @Stable
    val onErrorContainer: Color
        get() = _onErrorContainer
            ?: Color(dynamicScheme.onErrorContainer).also { _onErrorContainer = it }

    // surface colors

    @Stable
    val surface: Color
        get() = _surface
            ?: Color(dynamicScheme.surface).also { _surface = it }

    @Stable
    val onSurface: Color
        get() = _onSurface
            ?: Color(dynamicScheme.onSurface).also { _onSurface = it }

    @Stable
    val surfaceVariant: Color
        get() = _surfaceVariant
            ?: Color(dynamicScheme.surfaceVariant).also { _surfaceVariant = it }

    @Stable
    val onSurfaceVariant: Color
        get() = _onSurfaceVariant
            ?: Color(dynamicScheme.onSurfaceVariant).also { _onSurfaceVariant = it }

    @Stable
    val surfaceContainerHighest: Color
        get() = _surfaceContainerHighest
            ?: Color(dynamicScheme.surfaceContainerHighest).also { _surfaceContainerHighest = it }

    @Stable
    val surfaceContainerHigh: Color
        get() = _surfaceContainerHigh
            ?: Color(dynamicScheme.surfaceContainerHigh).also { _surfaceContainerHigh = it }

    @Stable
    val surfaceContainer: Color
        get() = _surfaceContainer
            ?: Color(dynamicScheme.surfaceContainer).also { _surfaceContainer = it }

    @Stable
    val surfaceContainerLow: Color
        get() = _surfaceContainerLow
            ?: Color(dynamicScheme.surfaceContainerLow).also { _surfaceContainerLow = it }

    @Stable
    val surfaceContainerLowest: Color
        get() = _surfaceContainerLowest
            ?: Color(dynamicScheme.surfaceContainerLowest).also { _surfaceContainerLowest = it }

    @Stable
    val inverseSurface: Color
        get() = _inverseSurface
            ?: Color(dynamicScheme.inverseSurface).also { _inverseSurface = it }

    @Stable
    val inverseOnSurface: Color
        get() = _inverseOnSurface
            ?: Color(dynamicScheme.inverseOnSurface).also { _inverseOnSurface = it }

    // outline colors

    @Stable
    val outline: Color
        get() = _outline
            ?: Color(dynamicScheme.outline).also { _outline = it }

    @Stable
    val outlineVariant: Color
        get() = _outlineVariant
            ?: Color(dynamicScheme.outlineVariant).also { _outlineVariant = it }

    // add-on primary colors

    @Stable
    val primaryFixed: Color
        get() = _primaryFixed
            ?: Color(dynamicScheme.primaryFixed).also { _primaryFixed = it }

    @Stable
    val onPrimaryFixed: Color
        get() = _onPrimaryFixed
            ?: Color(dynamicScheme.onPrimaryFixed).also { _onPrimaryFixed = it }

    @Stable
    val primaryFixedDim: Color
        get() = _primaryFixedDim
            ?: Color(dynamicScheme.primaryFixedDim).also { _primaryFixedDim = it }

    @Stable
    val onPrimaryFixedVariant: Color
        get() = _onPrimaryFixedVariant
            ?: Color(dynamicScheme.onPrimaryFixedVariant).also { _onPrimaryFixedVariant = it }

    @Stable
    val inversePrimary: Color
        get() = _inversePrimary
            ?: Color(dynamicScheme.inversePrimary).also { _inversePrimary = it }

    // add-on secondary colors

    @Stable
    val secondaryFixed: Color
        get() = _secondaryFixed
            ?: Color(dynamicScheme.secondaryFixed).also { _secondaryFixed = it }

    @Stable
    val onSecondaryFixed: Color
        get() = _onSecondaryFixed
            ?: Color(dynamicScheme.onSecondaryFixed).also { _onSecondaryFixed = it }

    @Stable
    val secondaryFixedDim: Color
        get() = _secondaryFixedDim
            ?: Color(dynamicScheme.secondaryFixedDim).also { _secondaryFixedDim = it }

    @Stable
    val onSecondaryFixedVariant: Color
        get() = _onSecondaryFixedVariant
            ?: Color(dynamicScheme.onSecondaryFixedVariant).also { _onSecondaryFixedVariant = it }

    // add-on tertiary colors

    @Stable
    val tertiaryFixed: Color
        get() = _tertiaryFixed
            ?: Color(dynamicScheme.tertiaryFixed).also { _tertiaryFixed = it }

    @Stable
    val onTertiaryFixed: Color
        get() = _onTertiaryFixed
            ?: Color(dynamicScheme.onTertiaryFixed).also { _onTertiaryFixed = it }

    @Stable
    val tertiaryFixedDim: Color
        get() = _tertiaryFixedDim
            ?: Color(dynamicScheme.tertiaryFixedDim).also { _tertiaryFixedDim = it }

    @Stable
    val onTertiaryFixedVariant: Color
        get() = _onTertiaryFixedVariant
            ?: Color(dynamicScheme.onTertiaryFixedVariant).also { _onTertiaryFixedVariant = it }

    // add-on surface colors

    @Stable
    val background: Color
        get() = _background
            ?: Color(dynamicScheme.background).also { _background = it }

    @Stable
    val onBackground: Color
        get() = _onBackground
            ?: Color(dynamicScheme.onBackground).also { _onBackground = it }

    @Stable
    val surfaceBright: Color
        get() = _surfaceBright
            ?: Color(dynamicScheme.surfaceBright).also { _surfaceBright = it }

    @Stable
    val surfaceDim: Color
        get() = _surfaceDim
            ?: Color(dynamicScheme.surfaceDim).also { _surfaceDim = it }

    @Stable
    val scrim: Color
        get() = _scrim
            ?: Color(dynamicScheme.scrim).also { _scrim = it }

    @Stable
    val shadow: Color
        get() = _shadow
            ?: Color(dynamicScheme.shadow).also { _shadow = it }

    companion object {

        @Stable
        val Default: ColorScheme =
            ColorScheme(
                sourceHct = Color(0xFF475D92).toHct(),
                variant = DynamicSchemeVariant.TonalSpot,
                isDark = false,
                contrastLevel = 0f
            )

        @Composable
        @ReadOnlyComposable
        fun systemDynamic(
            sourceHct: Hct = systemColor().toHct(),
            variant: DynamicSchemeVariant = DynamicSchemeVariant.TonalSpot,
            isDark: Boolean = isSystemInDarkTheme(),
            @FloatRange(from = -1.0, to = 1.0) contrastLevel: Float = systemContrast()
        ): ColorScheme =
            ColorScheme(
                sourceHct = sourceHct,
                variant = variant,
                isDark = isDark,
                contrastLevel = contrastLevel
            )

        @Composable
        @ReadOnlyComposable
        fun systemColor(): Color {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                colorResource(android.R.color.system_accent1_600)
            } else {
                Color(0xFF475D92)
            }
        }

        @Composable
        @ReadOnlyComposable
        fun systemContrast(): Float {
            val context = LocalContext.current
            val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager?
            return if (uiModeManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                uiModeManager.contrast
            } else {
                0f
            }
        }
    }
}
