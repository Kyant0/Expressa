package com.kyant.expressa.m3.typography

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.GenericFontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp

@Immutable
object Typography {

    @Stable
    val plainFontFamily: GenericFontFamily = FontFamily.SansSerif

    @Stable
    val brandFontFamily: GenericFontFamily = FontFamily.SansSerif

    @Stable
    val displayLarge: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val displayMedium: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val displaySmall: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val headlineLarge: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val headlineMedium: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val headlineSmall: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val titleLarge: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val titleMedium: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val titleSmall: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val bodyLarge: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val bodyMedium: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val bodySmall: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val labelLarge: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val labelMedium: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val labelSmall: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val displayLargeEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val displayMediumEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val displaySmallEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val headlineLargeEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val headlineMediumEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val headlineSmallEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val titleLargeEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = brandFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val titleMediumEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val titleSmallEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val bodyLargeEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val bodyMediumEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val bodySmallEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val labelLargeEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val labelMediumEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )

    @Stable
    val labelSmallEmphasized: TextStyle = TextStyle.Default.copy(
        fontFamily = plainFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        platformStyle = DefaultPlatformTextStyle,
        lineHeightStyle = DefaultLineHeightStyle
    )
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
