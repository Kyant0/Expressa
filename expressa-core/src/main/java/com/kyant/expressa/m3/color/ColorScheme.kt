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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.kyant.m3color.dynamiccolor.ColorSpec
import com.kyant.m3color.dynamiccolor.DynamicScheme
import com.kyant.m3color.dynamiccolor.Variant
import com.kyant.m3color.hct.Hct
import com.kyant.m3color.scheme.SchemeContent
import com.kyant.m3color.scheme.SchemeExpressive
import com.kyant.m3color.scheme.SchemeFidelity
import com.kyant.m3color.scheme.SchemeFruitSalad
import com.kyant.m3color.scheme.SchemeMonochrome
import com.kyant.m3color.scheme.SchemeNeutral
import com.kyant.m3color.scheme.SchemeRainbow
import com.kyant.m3color.scheme.SchemeTonalSpot
import com.kyant.m3color.scheme.SchemeVibrant

@Immutable
data class ColorScheme(
    // primary colors
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,

    // secondary colors
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,

    // tertiary colors
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,

    // error colors
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,

    // surface colors
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val surfaceContainerHighest: Color,
    val surfaceContainerHigh: Color,
    val surfaceContainer: Color,
    val surfaceContainerLow: Color,
    val surfaceContainerLowest: Color,
    val inverseSurface: Color,
    val inverseOnSurface: Color,

    // outline colors
    val outline: Color,
    val outlineVariant: Color,

    // add-on primary colors
    val primaryFixed: Color,
    val onPrimaryFixed: Color,
    val primaryFixedDim: Color,
    val onPrimaryFixedVariant: Color,
    val inversePrimary: Color,

    // add-on secondary colors
    val secondaryFixed: Color,
    val onSecondaryFixed: Color,
    val secondaryFixedDim: Color,
    val onSecondaryFixedVariant: Color,

    // add-on tertiary colors
    val tertiaryFixed: Color,
    val onTertiaryFixed: Color,
    val tertiaryFixedDim: Color,
    val onTertiaryFixedVariant: Color,

    // add-on surface colors
    val background: Color,
    val onBackground: Color,
    val surfaceBright: Color,
    val surfaceDim: Color,
    val scrim: Color,
    val shadow: Color
) {

    companion object {

        @Stable
        val Default: ColorScheme =
            materialColorScheme(
                sourceColor = Color(0xFF475D92),
                variant = Variant.TONAL_SPOT,
                isDark = false,
                contrastLevel = 0f
            )

        @Composable
        @ReadOnlyComposable
        fun systemDynamic(
            sourceColor: Color = systemColor(),
            variant: Variant = Variant.TONAL_SPOT,
            isDark: Boolean = isSystemInDarkTheme(),
            @FloatRange(from = -1.0, to = 1.0) contrastLevel: Float = systemContrast()
        ): ColorScheme =
            materialColorScheme(
                sourceColor = sourceColor,
                variant = variant,
                isDark = isDark,
                contrastLevel = contrastLevel
            )
    }
}

@Stable
fun materialColorScheme(
    sourceColor: Color,
    variant: Variant,
    isDark: Boolean,
    @FloatRange(from = -1.0, to = 1.0) contrastLevel: Float
): ColorScheme {
    val hct = Hct.fromInt(sourceColor.toArgb())
    val contrast = contrastLevel.toDouble()
    val spec = ColorSpec.SpecVersion.SPEC_2025
    val platform = DynamicScheme.Platform.PHONE

    val dynamicScheme = when (variant) {
        Variant.TONAL_SPOT -> SchemeTonalSpot(hct, isDark, contrast, spec, platform)
        Variant.NEUTRAL -> SchemeNeutral(hct, isDark, contrast, spec, platform)
        Variant.VIBRANT -> SchemeVibrant(hct, isDark, contrast, spec, platform)
        Variant.EXPRESSIVE -> SchemeExpressive(hct, isDark, contrast, spec, platform)
        Variant.RAINBOW -> SchemeRainbow(hct, isDark, contrast, spec, platform)
        Variant.FRUIT_SALAD -> SchemeFruitSalad(hct, isDark, contrast, spec, platform)
        Variant.MONOCHROME -> SchemeMonochrome(hct, isDark, contrast, spec, platform)
        Variant.FIDELITY -> SchemeFidelity(hct, isDark, contrast, spec, platform)
        Variant.CONTENT -> SchemeContent(hct, isDark, contrast, spec, platform)
    }

    return dynamicScheme.asColorScheme()
}

@Stable
fun DynamicScheme.asColorScheme(): ColorScheme {
    return ColorScheme(
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
