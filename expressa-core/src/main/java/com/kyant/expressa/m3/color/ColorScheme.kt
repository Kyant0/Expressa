package com.kyant.expressa.m3.color

import android.app.UiModeManager
import android.content.Context
import android.os.Build
import androidx.annotation.FloatRange
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.kyant.expressa.graphics.Hct
import com.kyant.expressa.graphics.Hct.Companion.toHct
import com.kyant.expressa.m3.LocalColorScheme
import com.kyant.expressa.mcu.DynamicScheme
import com.kyant.expressa.mcu.DynamicSchemeVariant
import com.kyant.expressa.mcu.Leakable
import com.kyant.expressa.mcu.LeakableObject
import com.kyant.expressa.mcu.TonalPalette

@OptIn(LeakableObject::class)
@Composable
fun rememberColorScheme(
    sourceHct: Hct = LocalColorScheme.current.sourceHct,
    variant: DynamicSchemeVariant = LocalColorScheme.current.variant,
    isDark: Boolean = LocalColorScheme.current.isDark,
    @FloatRange(from = -1.0, to = 1.0) contrastLevel: Float = LocalColorScheme.current.contrastLevel
): ColorScheme {
    val colorScheme = remember(sourceHct, variant, isDark, contrastLevel) {
        ColorScheme(
            sourceHct = sourceHct,
            variant = variant,
            isDark = isDark,
            contrastLevel = contrastLevel
        )
    }

    DisposableEffect(colorScheme) {
        onDispose { colorScheme.free() }
    }

    return colorScheme
}

@OptIn(LeakableObject::class)
@Immutable
class ColorScheme
@LeakableObject constructor(
    val sourceHct: Hct,
    val variant: DynamicSchemeVariant,
    val isDark: Boolean,
    @param:FloatRange(from = -1.0, to = 1.0) val contrastLevel: Float
) : Leakable {

    private val dynamicScheme =
        DynamicScheme(
            sourceHct = sourceHct,
            variant = variant,
            isDark = isDark,
            contrastLevel = contrastLevel.toDouble()
        )

    override fun isFreed(): Boolean {
        return dynamicScheme.isFreed()
    }

    override fun free() {
        dynamicScheme.free()
    }

    // primary colors
    private var _primary = Color.Unspecified
    private var _onPrimary = Color.Unspecified
    private var _primaryContainer = Color.Unspecified
    private var _onPrimaryContainer = Color.Unspecified

    // secondary colors
    private var _secondary = Color.Unspecified
    private var _onSecondary = Color.Unspecified
    private var _secondaryContainer = Color.Unspecified
    private var _onSecondaryContainer = Color.Unspecified

    // tertiary colors
    private var _tertiary = Color.Unspecified
    private var _onTertiary = Color.Unspecified
    private var _tertiaryContainer = Color.Unspecified
    private var _onTertiaryContainer = Color.Unspecified

    // error colors
    private var _error = Color.Unspecified
    private var _onError = Color.Unspecified
    private var _errorContainer = Color.Unspecified
    private var _onErrorContainer = Color.Unspecified

    // surface colors
    private var _surface = Color.Unspecified
    private var _onSurface = Color.Unspecified
    private var _surfaceVariant = Color.Unspecified
    private var _onSurfaceVariant = Color.Unspecified
    private var _surfaceContainerHighest = Color.Unspecified
    private var _surfaceContainerHigh = Color.Unspecified
    private var _surfaceContainer = Color.Unspecified
    private var _surfaceContainerLow = Color.Unspecified
    private var _surfaceContainerLowest = Color.Unspecified
    private var _inverseSurface = Color.Unspecified
    private var _inverseOnSurface = Color.Unspecified

    // outline colors
    private var _outline = Color.Unspecified
    private var _outlineVariant = Color.Unspecified

    // add-on primary colors
    private var _primaryFixed = Color.Unspecified
    private var _onPrimaryFixed = Color.Unspecified
    private var _primaryFixedDim = Color.Unspecified
    private var _onPrimaryFixedVariant = Color.Unspecified
    private var _inversePrimary = Color.Unspecified

    // add-on secondary colors
    private var _secondaryFixed = Color.Unspecified
    private var _onSecondaryFixed = Color.Unspecified
    private var _secondaryFixedDim = Color.Unspecified
    private var _onSecondaryFixedVariant = Color.Unspecified

    // add-on tertiary colors
    private var _tertiaryFixed = Color.Unspecified
    private var _onTertiaryFixed = Color.Unspecified
    private var _tertiaryFixedDim = Color.Unspecified
    private var _onTertiaryFixedVariant = Color.Unspecified

    // add-on surface colors
    private var _background = Color.Unspecified
    private var _onBackground = Color.Unspecified
    private var _surfaceBright = Color.Unspecified
    private var _surfaceDim = Color.Unspecified
    private var _scrim = Color.Unspecified
    private var _shadow = Color.Unspecified

    val primaryTonalPalette: TonalPalette get() = dynamicScheme.primaryTonalPalette
    val secondaryTonalPalette: TonalPalette get() = dynamicScheme.secondaryTonalPalette
    val tertiaryTonalPalette: TonalPalette get() = dynamicScheme.tertiaryTonalPalette
    val neutralTonalPalette: TonalPalette get() = dynamicScheme.neutralTonalPalette
    val neutralVariantTonalPalette: TonalPalette get() = dynamicScheme.neutralVariantTonalPalette
    val errorTonalPalette: TonalPalette get() = dynamicScheme.errorTonalPalette

    // primary colors

    @Stable
    val primary: Color
        get() = _primary.takeOrElse {
            Color(dynamicScheme.primary).also { _primary = it }
        }

    @Stable
    val onPrimary: Color
        get() = _onPrimary.takeOrElse {
            Color(dynamicScheme.onPrimary).also { _onPrimary = it }
        }

    @Stable
    val primaryContainer: Color
        get() = _primaryContainer.takeOrElse {
            Color(dynamicScheme.primaryContainer).also { _primaryContainer = it }
        }

    @Stable
    val onPrimaryContainer: Color
        get() = _onPrimaryContainer.takeOrElse {
            Color(dynamicScheme.onPrimaryContainer).also { _onPrimaryContainer = it }
        }

    // secondary colors

    @Stable
    val secondary: Color
        get() = _secondary.takeOrElse {
            Color(dynamicScheme.secondary).also { _secondary = it }
        }

    @Stable
    val onSecondary: Color
        get() = _onSecondary.takeOrElse {
            Color(dynamicScheme.onSecondary).also { _onSecondary = it }
        }

    @Stable
    val secondaryContainer: Color
        get() = _secondaryContainer.takeOrElse {
            Color(dynamicScheme.secondaryContainer).also { _secondaryContainer = it }
        }

    @Stable
    val onSecondaryContainer: Color
        get() = _onSecondaryContainer.takeOrElse {
            Color(dynamicScheme.onSecondaryContainer).also { _onSecondaryContainer = it }
        }

    // tertiary colors

    @Stable
    val tertiary: Color
        get() = _tertiary.takeOrElse {
            Color(dynamicScheme.tertiary).also { _tertiary = it }
        }

    @Stable
    val onTertiary: Color
        get() = _onTertiary.takeOrElse {
            Color(dynamicScheme.onTertiary).also { _onTertiary = it }
        }

    @Stable
    val tertiaryContainer: Color
        get() = _tertiaryContainer.takeOrElse {
            Color(dynamicScheme.tertiaryContainer).also { _tertiaryContainer = it }
        }

    @Stable
    val onTertiaryContainer: Color
        get() = _onTertiaryContainer.takeOrElse {
            Color(dynamicScheme.onTertiaryContainer).also { _onTertiaryContainer = it }
        }

    // error colors

    @Stable
    val error: Color
        get() = _error.takeOrElse {
            Color(dynamicScheme.error).also { _error = it }
        }

    @Stable
    val onError: Color
        get() = _onError.takeOrElse {
            Color(dynamicScheme.onError).also { _onError = it }
        }

    @Stable
    val errorContainer: Color
        get() = _errorContainer.takeOrElse {
            Color(dynamicScheme.errorContainer).also { _errorContainer = it }
        }

    @Stable
    val onErrorContainer: Color
        get() = _onErrorContainer.takeOrElse {
            Color(dynamicScheme.onErrorContainer).also { _onErrorContainer = it }
        }

    // surface colors

    @Stable
    val surface: Color
        get() = _surface.takeOrElse {
            Color(dynamicScheme.surface).also { _surface = it }
        }

    @Stable
    val onSurface: Color
        get() = _onSurface.takeOrElse {
            Color(dynamicScheme.onSurface).also { _onSurface = it }
        }

    @Stable
    val surfaceVariant: Color
        get() = _surfaceVariant.takeOrElse {
            Color(dynamicScheme.surfaceVariant).also { _surfaceVariant = it }
        }

    @Stable
    val onSurfaceVariant: Color
        get() = _onSurfaceVariant.takeOrElse {
            Color(dynamicScheme.onSurfaceVariant).also { _onSurfaceVariant = it }
        }

    @Stable
    val surfaceContainerHighest: Color
        get() = _surfaceContainerHighest.takeOrElse {
            Color(dynamicScheme.surfaceContainerHighest).also { _surfaceContainerHighest = it }
        }

    @Stable
    val surfaceContainerHigh: Color
        get() = _surfaceContainerHigh.takeOrElse {
            Color(dynamicScheme.surfaceContainerHigh).also { _surfaceContainerHigh = it }
        }

    @Stable
    val surfaceContainer: Color
        get() = _surfaceContainer.takeOrElse {
            Color(dynamicScheme.surfaceContainer).also { _surfaceContainer = it }
        }

    @Stable
    val surfaceContainerLow: Color
        get() = _surfaceContainerLow.takeOrElse {
            Color(dynamicScheme.surfaceContainerLow).also { _surfaceContainerLow = it }
        }

    @Stable
    val surfaceContainerLowest: Color
        get() = _surfaceContainerLowest.takeOrElse {
            Color(dynamicScheme.surfaceContainerLowest).also { _surfaceContainerLowest = it }
        }

    @Stable
    val inverseSurface: Color
        get() = _inverseSurface.takeOrElse {
            Color(dynamicScheme.inverseSurface).also { _inverseSurface = it }
        }

    @Stable
    val inverseOnSurface: Color
        get() = _inverseOnSurface.takeOrElse {
            Color(dynamicScheme.inverseOnSurface).also { _inverseOnSurface = it }
        }

    // outline colors

    @Stable
    val outline: Color
        get() = _outline.takeOrElse {
            Color(dynamicScheme.outline).also { _outline = it }
        }

    @Stable
    val outlineVariant: Color
        get() = _outlineVariant.takeOrElse {
            Color(dynamicScheme.outlineVariant).also { _outlineVariant = it }
        }

    // add-on primary colors

    @Stable
    val primaryFixed: Color
        get() = _primaryFixed.takeOrElse {
            Color(dynamicScheme.primaryFixed).also { _primaryFixed = it }
        }

    @Stable
    val onPrimaryFixed: Color
        get() = _onPrimaryFixed.takeOrElse {
            Color(dynamicScheme.onPrimaryFixed).also { _onPrimaryFixed = it }
        }

    @Stable
    val primaryFixedDim: Color
        get() = _primaryFixedDim.takeOrElse {
            Color(dynamicScheme.primaryFixedDim).also { _primaryFixedDim = it }
        }

    @Stable
    val onPrimaryFixedVariant: Color
        get() = _onPrimaryFixedVariant.takeOrElse {
            Color(dynamicScheme.onPrimaryFixedVariant).also { _onPrimaryFixedVariant = it }
        }

    @Stable
    val inversePrimary: Color
        get() = _inversePrimary.takeOrElse {
            Color(dynamicScheme.inversePrimary).also { _inversePrimary = it }
        }

    // add-on secondary colors

    @Stable
    val secondaryFixed: Color
        get() = _secondaryFixed.takeOrElse {
            Color(dynamicScheme.secondaryFixed).also { _secondaryFixed = it }
        }

    @Stable
    val onSecondaryFixed: Color
        get() = _onSecondaryFixed.takeOrElse {
            Color(dynamicScheme.onSecondaryFixed).also { _onSecondaryFixed = it }
        }

    @Stable
    val secondaryFixedDim: Color
        get() = _secondaryFixedDim.takeOrElse {
            Color(dynamicScheme.secondaryFixedDim).also { _secondaryFixedDim = it }
        }

    @Stable
    val onSecondaryFixedVariant: Color
        get() = _onSecondaryFixedVariant.takeOrElse {
            Color(dynamicScheme.onSecondaryFixedVariant).also { _onSecondaryFixedVariant = it }
        }

    // add-on tertiary colors

    @Stable
    val tertiaryFixed: Color
        get() = _tertiaryFixed.takeOrElse {
            Color(dynamicScheme.tertiaryFixed).also { _tertiaryFixed = it }
        }

    @Stable
    val onTertiaryFixed: Color
        get() = _onTertiaryFixed.takeOrElse {
            Color(dynamicScheme.onTertiaryFixed).also { _onTertiaryFixed = it }
        }

    @Stable
    val tertiaryFixedDim: Color
        get() = _tertiaryFixedDim.takeOrElse {
            Color(dynamicScheme.tertiaryFixedDim).also { _tertiaryFixedDim = it }
        }

    @Stable
    val onTertiaryFixedVariant: Color
        get() = _onTertiaryFixedVariant.takeOrElse {
            Color(dynamicScheme.onTertiaryFixedVariant).also { _onTertiaryFixedVariant = it }
        }

    // add-on surface colors

    @Stable
    val background: Color
        get() = _background.takeOrElse {
            Color(dynamicScheme.background).also { _background = it }
        }

    @Stable
    val onBackground: Color
        get() = _onBackground.takeOrElse {
            Color(dynamicScheme.onBackground).also { _onBackground = it }
        }

    @Stable
    val surfaceBright: Color
        get() = _surfaceBright.takeOrElse {
            Color(dynamicScheme.surfaceBright).also { _surfaceBright = it }
        }

    @Stable
    val surfaceDim: Color
        get() = _surfaceDim.takeOrElse {
            Color(dynamicScheme.surfaceDim).also { _surfaceDim = it }
        }

    @Stable
    val scrim: Color
        get() = _scrim.takeOrElse {
            Color(dynamicScheme.scrim).also { _scrim = it }
        }

    @Stable
    val shadow: Color
        get() = _shadow.takeOrElse {
            Color(dynamicScheme.shadow).also { _shadow = it }
        }

    companion object {

        @Composable
        fun systemDynamic(
            sourceHct: Hct = systemColor().toHct(),
            variant: DynamicSchemeVariant = DynamicSchemeVariant.TonalSpot,
            isDark: Boolean = isSystemInDarkTheme(),
            @FloatRange(from = -1.0, to = 1.0) contrastLevel: Float = systemContrast()
        ): ColorScheme =
            rememberColorScheme(
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

    @Composable
    fun copy(
        sourceHct: Hct = this.sourceHct,
        variant: DynamicSchemeVariant = this.variant,
        isDark: Boolean = this.isDark,
        @FloatRange(from = -1.0, to = 1.0) contrastLevel: Float = this.contrastLevel
    ): ColorScheme {
        return rememberColorScheme(
            sourceHct = sourceHct,
            variant = variant,
            isDark = isDark,
            contrastLevel = contrastLevel
        )
    }

    @LeakableObject
    fun copyLeaked(
        sourceHct: Hct = this.sourceHct,
        variant: DynamicSchemeVariant = this.variant,
        isDark: Boolean = this.isDark,
        @FloatRange(from = -1.0, to = 1.0) contrastLevel: Float = this.contrastLevel
    ): ColorScheme {
        return ColorScheme(
            sourceHct = sourceHct,
            variant = variant,
            isDark = isDark,
            contrastLevel = contrastLevel
        )
    }

    override fun toString(): String {
        return "ColorScheme(sourceHct=$sourceHct, variant=$variant, isDark=$isDark, contrastLevel=$contrastLevel)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ColorScheme

        if (sourceHct != other.sourceHct) return false
        if (variant != other.variant) return false
        if (isDark != other.isDark) return false
        if (contrastLevel != other.contrastLevel) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sourceHct.hashCode()
        result = 31 * result + variant.hashCode()
        result = 31 * result + isDark.hashCode()
        result = 31 * result + contrastLevel.hashCode()
        return result
    }
}
