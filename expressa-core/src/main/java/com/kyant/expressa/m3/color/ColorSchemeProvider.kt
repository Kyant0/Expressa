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
data class ColorSchemeProvider(
    val isLazy: Boolean,
    val sourceHct: Hct,
    val variant: DynamicSchemeVariant,
    val isDark: Boolean,
    @param:FloatRange(from = -1.0, to = 1.0) val contrastLevel: Float
) {

    @Stable
    val dynamicScheme: DynamicScheme
        get() = DynamicScheme(
            sourceHct = sourceHct,
            variant = variant,
            isDark = isDark,
            contrastLevel = contrastLevel.toDouble()
        )

    @Stable
    fun toColorScheme(): ColorScheme {
        return if (isLazy) {
            dynamicScheme.asLazyColorScheme()
        } else {
            dynamicScheme.toCachedColorScheme()
        }
    }

    companion object {

        @Stable
        val Default: ColorSchemeProvider =
            ColorSchemeProvider(
                isLazy = true,
                sourceHct = Color(0xFF475D92).toHct(),
                variant = DynamicSchemeVariant.TonalSpot,
                isDark = false,
                contrastLevel = 0f
            )

        @Composable
        @ReadOnlyComposable
        fun systemDynamic(
            isLazy: Boolean = true,
            sourceHct: Hct = systemColor().toHct(),
            variant: DynamicSchemeVariant = DynamicSchemeVariant.TonalSpot,
            isDark: Boolean = isSystemInDarkTheme(),
            @FloatRange(from = -1.0, to = 1.0) contrastLevel: Float = systemContrast()
        ): ColorSchemeProvider =
            ColorSchemeProvider(
                isLazy = isLazy,
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

        @Stable
        private fun DynamicScheme.toCachedColorScheme(): CachedColorScheme {
            return CachedColorScheme(
                // primary colors
                primary = Color(primary),
                onPrimary = Color(onPrimary),
                primaryContainer = Color(primaryContainer),
                onPrimaryContainer = Color(onPrimaryContainer),

                // secondary colors
                secondary = Color(secondary),
                onSecondary = Color(onSecondary),
                secondaryContainer = Color(secondaryContainer),
                onSecondaryContainer = Color(onSecondaryContainer),

                // tertiary colors
                tertiary = Color(tertiary),
                onTertiary = Color(onTertiary),
                tertiaryContainer = Color(tertiaryContainer),
                onTertiaryContainer = Color(onTertiaryContainer),

                // error colors
                error = Color(error),
                onError = Color(onError),
                errorContainer = Color(errorContainer),
                onErrorContainer = Color(onErrorContainer),

                // surface colors
                surface = Color(surface),
                onSurface = Color(onSurface),
                surfaceVariant = Color(surfaceVariant),
                onSurfaceVariant = Color(onSurfaceVariant),
                surfaceContainerHighest = Color(surfaceContainerHighest),
                surfaceContainerHigh = Color(surfaceContainerHigh),
                surfaceContainer = Color(surfaceContainer),
                surfaceContainerLow = Color(surfaceContainerLow),
                surfaceContainerLowest = Color(surfaceContainerLowest),
                inverseSurface = Color(inverseSurface),
                inverseOnSurface = Color(inverseOnSurface),

                // outline colors
                outline = Color(outline),
                outlineVariant = Color(outlineVariant),

                // add-on primary colors
                primaryFixed = Color(primaryFixed),
                onPrimaryFixed = Color(onPrimaryFixed),
                primaryFixedDim = Color(primaryFixedDim),
                onPrimaryFixedVariant = Color(onPrimaryFixedVariant),
                inversePrimary = Color(inversePrimary),

                // add-on secondary colors
                secondaryFixed = Color(secondaryFixed),
                onSecondaryFixed = Color(onSecondaryFixed),
                secondaryFixedDim = Color(secondaryFixedDim),
                onSecondaryFixedVariant = Color(onSecondaryFixedVariant),

                // add-on tertiary colors
                tertiaryFixed = Color(tertiaryFixed),
                onTertiaryFixed = Color(onTertiaryFixed),
                tertiaryFixedDim = Color(tertiaryFixedDim),
                onTertiaryFixedVariant = Color(onTertiaryFixedVariant),

                // add-on surface colors
                background = Color(background),
                onBackground = Color(onBackground),
                surfaceBright = Color(surfaceBright),
                surfaceDim = Color(surfaceDim),
                scrim = Color(scrim),
                shadow = Color(shadow)
            )
        }

        @Stable
        private fun DynamicScheme.asLazyColorScheme(): LazyColorScheme {
            return LazyColorScheme(this)
        }
    }
}
