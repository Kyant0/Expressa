package com.kyant.expressa.mcu

import androidx.annotation.Keep
import androidx.compose.runtime.Immutable
import com.kyant.expressa.graphics.Hct
import dalvik.annotation.optimization.FastNative

@OptIn(LeakableObject::class)
@Immutable
internal data class DynamicScheme
@LeakableObject constructor(
    val sourceHct: Hct,
    val variant: DynamicSchemeVariant,
    val isDark: Boolean,
    val contrastLevel: Double
) : Leakable {

    @Keep
    private var nativeHandle: Long = 0L

    init {
        nativeHandle = nativeInit(sourceHct.toArgb(), variant.ordinal, isDark, contrastLevel)
        if (nativeHandle == 0L) {
            throw IllegalStateException("Failed to initialize DynamicScheme")
        }
    }

    override fun isFreed(): Boolean {
        return nativeHandle == 0L
    }

    override fun free() {
        if (nativeHandle != 0L) {
            nativeFree(nativeHandle)
            nativeHandle = 0L
        }
    }

    // primary colors
    inline val primary: Int get() = getMaterialDynamicColor(MaterialColorRole.Primary)
    inline val onPrimary: Int get() = getMaterialDynamicColor(MaterialColorRole.OnPrimary)
    inline val primaryContainer: Int get() = getMaterialDynamicColor(MaterialColorRole.PrimaryContainer)
    inline val onPrimaryContainer: Int get() = getMaterialDynamicColor(MaterialColorRole.OnPrimaryContainer)

    // secondary colors
    inline val secondary: Int get() = getMaterialDynamicColor(MaterialColorRole.Secondary)
    inline val onSecondary: Int get() = getMaterialDynamicColor(MaterialColorRole.OnSecondary)
    inline val secondaryContainer: Int get() = getMaterialDynamicColor(MaterialColorRole.SecondaryContainer)
    inline val onSecondaryContainer: Int get() = getMaterialDynamicColor(MaterialColorRole.OnSecondaryContainer)

    // tertiary colors
    inline val tertiary: Int get() = getMaterialDynamicColor(MaterialColorRole.Tertiary)
    inline val onTertiary: Int get() = getMaterialDynamicColor(MaterialColorRole.OnTertiary)
    inline val tertiaryContainer: Int get() = getMaterialDynamicColor(MaterialColorRole.TertiaryContainer)
    inline val onTertiaryContainer: Int get() = getMaterialDynamicColor(MaterialColorRole.OnTertiaryContainer)

    // error colors
    inline val error: Int get() = getMaterialDynamicColor(MaterialColorRole.Error)
    inline val onError: Int get() = getMaterialDynamicColor(MaterialColorRole.OnError)
    inline val errorContainer: Int get() = getMaterialDynamicColor(MaterialColorRole.ErrorContainer)
    inline val onErrorContainer: Int get() = getMaterialDynamicColor(MaterialColorRole.OnErrorContainer)

    // surface colors
    inline val surface: Int get() = getMaterialDynamicColor(MaterialColorRole.Surface)
    inline val onSurface: Int get() = getMaterialDynamicColor(MaterialColorRole.OnSurface)
    inline val surfaceVariant: Int get() = getMaterialDynamicColor(MaterialColorRole.SurfaceVariant)
    inline val onSurfaceVariant: Int get() = getMaterialDynamicColor(MaterialColorRole.OnSurfaceVariant)
    inline val surfaceContainerHighest: Int get() = getMaterialDynamicColor(MaterialColorRole.SurfaceContainerHighest)
    inline val surfaceContainerHigh: Int get() = getMaterialDynamicColor(MaterialColorRole.SurfaceContainerHigh)
    inline val surfaceContainer: Int get() = getMaterialDynamicColor(MaterialColorRole.SurfaceContainer)
    inline val surfaceContainerLow: Int get() = getMaterialDynamicColor(MaterialColorRole.SurfaceContainerLow)
    inline val surfaceContainerLowest: Int get() = getMaterialDynamicColor(MaterialColorRole.SurfaceContainerLowest)
    inline val inverseSurface: Int get() = getMaterialDynamicColor(MaterialColorRole.InverseSurface)
    inline val inverseOnSurface: Int get() = getMaterialDynamicColor(MaterialColorRole.InverseOnSurface)

    // outline colors
    inline val outline: Int get() = getMaterialDynamicColor(MaterialColorRole.Outline)
    inline val outlineVariant: Int get() = getMaterialDynamicColor(MaterialColorRole.OutlineVariant)

    // add-on primary colors
    inline val primaryFixed: Int get() = getMaterialDynamicColor(MaterialColorRole.PrimaryFixed)
    inline val onPrimaryFixed: Int get() = getMaterialDynamicColor(MaterialColorRole.OnPrimaryFixed)
    inline val primaryFixedDim: Int get() = getMaterialDynamicColor(MaterialColorRole.PrimaryFixedDim)
    inline val onPrimaryFixedVariant: Int get() = getMaterialDynamicColor(MaterialColorRole.OnPrimaryFixedVariant)
    inline val inversePrimary: Int get() = getMaterialDynamicColor(MaterialColorRole.InversePrimary)

    // add-on secondary colors
    inline val secondaryFixed: Int get() = getMaterialDynamicColor(MaterialColorRole.SecondaryFixed)
    inline val onSecondaryFixed: Int get() = getMaterialDynamicColor(MaterialColorRole.OnSecondaryFixed)
    inline val secondaryFixedDim: Int get() = getMaterialDynamicColor(MaterialColorRole.SecondaryFixedDim)
    inline val onSecondaryFixedVariant: Int get() = getMaterialDynamicColor(MaterialColorRole.OnSecondaryFixedVariant)

    // add-on tertiary colors
    inline val tertiaryFixed: Int get() = getMaterialDynamicColor(MaterialColorRole.TertiaryFixed)
    inline val onTertiaryFixed: Int get() = getMaterialDynamicColor(MaterialColorRole.OnTertiaryFixed)
    inline val tertiaryFixedDim: Int get() = getMaterialDynamicColor(MaterialColorRole.TertiaryFixedDim)
    inline val onTertiaryFixedVariant: Int get() = getMaterialDynamicColor(MaterialColorRole.OnTertiaryFixedVariant)

    // add-on surface colors
    inline val background: Int get() = getMaterialDynamicColor(MaterialColorRole.Background)
    inline val onBackground: Int get() = getMaterialDynamicColor(MaterialColorRole.OnBackground)
    inline val surfaceBright: Int get() = getMaterialDynamicColor(MaterialColorRole.SurfaceBright)
    inline val surfaceDim: Int get() = getMaterialDynamicColor(MaterialColorRole.SurfaceDim)
    inline val scrim: Int get() = getMaterialDynamicColor(MaterialColorRole.Scrim)
    inline val shadow: Int get() = getMaterialDynamicColor(MaterialColorRole.Shadow)

    val primaryTonalPalette: TonalPalette
        get() = getTonalPalette(DynamicSchemeTonalPalette.Primary)

    val secondaryTonalPalette: TonalPalette
        get() = getTonalPalette(DynamicSchemeTonalPalette.Secondary)

    val tertiaryTonalPalette: TonalPalette
        get() = getTonalPalette(DynamicSchemeTonalPalette.Tertiary)

    val neutralTonalPalette: TonalPalette
        get() = getTonalPalette(DynamicSchemeTonalPalette.Neutral)

    val neutralVariantTonalPalette: TonalPalette
        get() = getTonalPalette(DynamicSchemeTonalPalette.NeutralVariant)

    val errorTonalPalette: TonalPalette
        get() = getTonalPalette(DynamicSchemeTonalPalette.Error)

    private fun getMaterialDynamicColor(materialColorRole: MaterialColorRole): Int {
        return nativeGetMaterialDynamicColor(nativeHandle, materialColorRole.ordinal)
    }

    private fun getTonalPalette(tonalPalette: DynamicSchemeTonalPalette): TonalPalette {
        val array = nativeGetTonalPalette(nativeHandle, tonalPalette.ordinal)
        return if (array != null && array.size == 5) {
            TonalPalette(
                hue = array[0],
                chroma = array[1],
                keyColor =
                    Hct(
                        hue = array[2],
                        chroma = array[3],
                        tone = array[4]
                    )
            )
        } else {
            throw IllegalStateException("Invalid tonal palette data")
        }
    }

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

        @FastNative
        external fun nativeGetTonalPalette(
            nativeHandle: Long,
            tonalPalette: Int
        ): DoubleArray?
    }
}
