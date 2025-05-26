package com.kyant.expressa.m3.typography

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp

@Immutable
data class Typography(
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val displaySmall: TextStyle,

    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,

    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,

    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,

    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,

    val displayLargeEmphasized: TextStyle,
    val displayMediumEmphasized: TextStyle,
    val displaySmallEmphasized: TextStyle,

    val headlineLargeEmphasized: TextStyle,
    val headlineMediumEmphasized: TextStyle,
    val headlineSmallEmphasized: TextStyle,

    val titleLargeEmphasized: TextStyle,
    val titleMediumEmphasized: TextStyle,
    val titleSmallEmphasized: TextStyle,

    val bodyLargeEmphasized: TextStyle,
    val bodyMediumEmphasized: TextStyle,
    val bodySmallEmphasized: TextStyle,

    val labelLargeEmphasized: TextStyle,
    val labelMediumEmphasized: TextStyle,
    val labelSmallEmphasized: TextStyle
) {

    companion object {

        @Stable
        val Default: Typography = materialTypography()
    }
}

@Stable
internal val DefaultPlatformTextStyle =
    PlatformTextStyle(includeFontPadding = false)

@Stable
internal val DefaultLineHeightStyle =
    LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )

@Stable
internal val DefaultTextStyle =
    TextStyle.Default.copy(
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

@Stable
fun materialTypography(
    brandFontFamily: FontFamily = FontFamily.SansSerif,
    plainFontFamily: FontFamily = FontFamily.SansSerif
): Typography =
    Typography(
        displayLarge = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        displayMedium = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        displaySmall = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),

        headlineLarge = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        headlineMedium = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        headlineSmall = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),

        titleLarge = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        titleMedium = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        titleSmall = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),

        bodyLarge = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        bodyMedium = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        bodySmall = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),

        labelLarge = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        labelMedium = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        labelSmall = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),

        displayLargeEmphasized = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        displayMediumEmphasized = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        displaySmallEmphasized = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),

        headlineLargeEmphasized = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        headlineMediumEmphasized = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        headlineSmallEmphasized = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),

        titleLargeEmphasized = TextStyle.Default.copy(
            fontFamily = brandFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        titleMediumEmphasized = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        titleSmallEmphasized = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),

        bodyLargeEmphasized = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        bodyMediumEmphasized = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        bodySmallEmphasized = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),

        labelLargeEmphasized = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        labelMediumEmphasized = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        ),
        labelSmallEmphasized = TextStyle.Default.copy(
            fontFamily = plainFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            platformStyle = DefaultPlatformTextStyle,
            lineHeightStyle = DefaultLineHeightStyle
        )
    )
