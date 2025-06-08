package com.kyant.expressa.mcu

import androidx.annotation.Keep
import androidx.compose.runtime.Immutable
import com.kyant.expressa.graphics.Hct
import dalvik.annotation.optimization.FastNative

@Immutable
data class DynamicScheme(
    val sourceHct: Hct,
    val variant: DynamicSchemeVariant,
    val isDark: Boolean,
    val contrastLevel: Double
) {

    @Keep
    private var nativeHandle: Long = 0L

    init {
        nativeHandle = nativeInit(sourceHct.toArgb(), variant.ordinal, isDark, contrastLevel)
        if (nativeHandle == 0L) {
            throw IllegalStateException("Failed to initialize DynamicScheme")
        }
    }

    fun free() {
        if (nativeHandle != 0L) {
            nativeFree(nativeHandle)
            nativeHandle = 0L
        }
    }

    fun getMaterialDynamicColor(materialColorRole: MaterialColorRole): Int {
        return if (nativeHandle != 0L) {
            nativeGetMaterialDynamicColor(nativeHandle, materialColorRole.ordinal)
        } else {
            throw IllegalStateException("DynamicScheme has been freed")
        }
    }

    // primary colors
    val primary: Int get() = nativeGetPrimary(nativeHandle)
    val onPrimary: Int get() = nativeGetOnPrimary(nativeHandle)
    val primaryContainer: Int get() = nativeGetPrimaryContainer(nativeHandle)
    val onPrimaryContainer: Int get() = nativeGetOnPrimaryContainer(nativeHandle)

    // secondary colors
    val secondary: Int get() = nativeGetSecondary(nativeHandle)
    val onSecondary: Int get() = nativeGetOnSecondary(nativeHandle)
    val secondaryContainer: Int get() = nativeGetSecondaryContainer(nativeHandle)
    val onSecondaryContainer: Int get() = nativeGetOnSecondaryContainer(nativeHandle)

    // tertiary colors
    val tertiary: Int get() = nativeGetTertiary(nativeHandle)
    val onTertiary: Int get() = nativeGetOnTertiary(nativeHandle)
    val tertiaryContainer: Int get() = nativeGetTertiaryContainer(nativeHandle)
    val onTertiaryContainer: Int get() = nativeGetOnTertiaryContainer(nativeHandle)

    // error colors
    val error: Int get() = nativeGetError(nativeHandle)
    val onError: Int get() = nativeGetOnError(nativeHandle)
    val errorContainer: Int get() = nativeGetErrorContainer(nativeHandle)
    val onErrorContainer: Int get() = nativeGetOnErrorContainer(nativeHandle)

    // surface colors
    val surface: Int get() = nativeGetSurface(nativeHandle)
    val onSurface: Int get() = nativeGetOnSurface(nativeHandle)
    val surfaceVariant: Int get() = nativeGetSurfaceVariant(nativeHandle)
    val onSurfaceVariant: Int get() = nativeGetOnSurfaceVariant(nativeHandle)
    val surfaceContainerHighest: Int get() = nativeGetSurfaceContainerHighest(nativeHandle)
    val surfaceContainerHigh: Int get() = nativeGetSurfaceContainerHigh(nativeHandle)
    val surfaceContainer: Int get() = nativeGetSurfaceContainer(nativeHandle)
    val surfaceContainerLow: Int get() = nativeGetSurfaceContainerLow(nativeHandle)
    val surfaceContainerLowest: Int get() = nativeGetSurfaceContainerLowest(nativeHandle)
    val inverseSurface: Int get() = nativeGetInverseSurface(nativeHandle)
    val inverseOnSurface: Int get() = nativeGetInverseOnSurface(nativeHandle)

    // outline colors
    val outline: Int get() = nativeGetOutline(nativeHandle)
    val outlineVariant: Int get() = nativeGetOutlineVariant(nativeHandle)

    // add-on primary colors
    val primaryFixed: Int get() = nativeGetPrimaryFixed(nativeHandle)
    val onPrimaryFixed: Int get() = nativeGetOnPrimaryFixed(nativeHandle)
    val primaryFixedDim: Int get() = nativeGetPrimaryFixedDim(nativeHandle)
    val onPrimaryFixedVariant: Int get() = nativeGetOnPrimaryFixedVariant(nativeHandle)
    val inversePrimary: Int get() = nativeGetInversePrimary(nativeHandle)

    // add-on secondary colors
    val secondaryFixed: Int get() = nativeGetSecondaryFixed(nativeHandle)
    val onSecondaryFixed: Int get() = nativeGetOnSecondaryFixed(nativeHandle)
    val secondaryFixedDim: Int get() = nativeGetSecondaryFixedDim(nativeHandle)
    val onSecondaryFixedVariant: Int get() = nativeGetOnSecondaryFixedVariant(nativeHandle)

    // add-on tertiary colors
    val tertiaryFixed: Int get() = nativeGetTertiaryFixed(nativeHandle)
    val onTertiaryFixed: Int get() = nativeGetOnTertiaryFixed(nativeHandle)
    val tertiaryFixedDim: Int get() = nativeGetTertiaryFixedDim(nativeHandle)
    val onTertiaryFixedVariant: Int get() = nativeGetOnTertiaryFixedVariant(nativeHandle)

    // add-on surface colors
    val background: Int get() = nativeGetBackground(nativeHandle)
    val onBackground: Int get() = nativeGetOnBackground(nativeHandle)
    val surfaceBright: Int get() = nativeGetSurfaceBright(nativeHandle)
    val surfaceDim: Int get() = nativeGetSurfaceDim(nativeHandle)
    val scrim: Int get() = nativeGetScrim(nativeHandle)
    val shadow: Int get() = nativeGetShadow(nativeHandle)

    private companion object {

        init {
            System.loadLibrary("mcu_jni")
        }

        @FastNative
        external fun nativeInit(
            argb: Int,
            variant: Int,
            isDark: Boolean,
            contrastLevel: Double
        ): Long

        @FastNative
        external fun nativeFree(nativeHandle: Long)

        @FastNative
        external fun nativeGetMaterialDynamicColor(
            nativeHandle: Long,
            materialColorRole: Int
        ): Int

        // primary colors

        @FastNative
        external fun nativeGetPrimary(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnPrimary(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetPrimaryContainer(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnPrimaryContainer(nativeHandle: Long): Int

        // secondary colors

        @FastNative
        external fun nativeGetSecondary(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnSecondary(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetSecondaryContainer(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnSecondaryContainer(nativeHandle: Long): Int

        // tertiary colors

        @FastNative
        external fun nativeGetTertiary(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnTertiary(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetTertiaryContainer(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnTertiaryContainer(nativeHandle: Long): Int

        // error colors

        @FastNative
        external fun nativeGetError(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnError(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetErrorContainer(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnErrorContainer(nativeHandle: Long): Int

        // surface colors

        @FastNative
        external fun nativeGetSurface(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnSurface(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetSurfaceVariant(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnSurfaceVariant(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetSurfaceContainerHighest(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetSurfaceContainerHigh(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetSurfaceContainer(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetSurfaceContainerLow(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetSurfaceContainerLowest(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetInverseSurface(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetInverseOnSurface(nativeHandle: Long): Int

        // outline colors

        @FastNative
        external fun nativeGetOutline(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOutlineVariant(nativeHandle: Long): Int

        // add-on primary colors

        @FastNative
        external fun nativeGetPrimaryFixed(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnPrimaryFixed(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetPrimaryFixedDim(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnPrimaryFixedVariant(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetInversePrimary(nativeHandle: Long): Int

        // add-on secondary colors

        @FastNative
        external fun nativeGetSecondaryFixed(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnSecondaryFixed(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetSecondaryFixedDim(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnSecondaryFixedVariant(nativeHandle: Long): Int

        // add-on tertiary colors

        @FastNative
        external fun nativeGetTertiaryFixed(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnTertiaryFixed(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetTertiaryFixedDim(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnTertiaryFixedVariant(nativeHandle: Long): Int

        // add-on surface colors

        @FastNative
        external fun nativeGetBackground(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetOnBackground(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetSurfaceBright(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetSurfaceDim(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetScrim(nativeHandle: Long): Int

        @FastNative
        external fun nativeGetShadow(nativeHandle: Long): Int
    }
}
