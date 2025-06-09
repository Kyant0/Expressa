#ifndef MCU_H
#define MCU_H

#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

typedef struct {
    double hue;
    double chroma;
    double tone;
} Hct;

typedef struct {
    double hue;
    double chroma;
    double key_color_hue;
    double key_color_chroma;
    double key_color_tone;
} TonalPalette;

__attribute__((unused))
typedef enum {
    Neutral,
    TonalSpot,
    Vibrant,
    Expressive,
} Variant;

__attribute__((unused))
typedef enum {
    TonalPalettePrimary,
    TonalPaletteSecondary,
    TonalPaletteTertiary,
    TonalPaletteNeutral,
    TonalPaletteNeutralVariant,
    TonalPaletteError,
} DynamicSchemeTonalPalette;

__attribute__((unused))
typedef enum {
    // primary colors
    Primary,
    OnPrimary,
    PrimaryContainer,
    OnPrimaryContainer,

    // secondary colors
    Secondary,
    OnSecondary,
    SecondaryContainer,
    OnSecondaryContainer,

    // tertiary colors
    Tertiary,
    OnTertiary,
    TertiaryContainer,
    OnTertiaryContainer,

    // error colors
    Error,
    OnError,
    ErrorContainer,
    OnErrorContainer,

    // surface colors
    Surface,
    OnSurface,
    SurfaceVariant,
    OnSurfaceVariant,
    SurfaceContainerHighest,
    SurfaceContainerHigh,
    SurfaceContainer,
    SurfaceContainerLow,
    SurfaceContainerLowest,
    InverseSurface,
    InverseOnSurface,

    // outline colors
    Outline,
    OutlineVariant,

    // add-on primary colors
    PrimaryFixed,
    OnPrimaryFixed,
    PrimaryFixedDim,
    OnPrimaryFixedVariant,
    InversePrimary,

    // add-on secondary colors
    SecondaryFixed,
    OnSecondaryFixed,
    SecondaryFixedDim,
    OnSecondaryFixedVariant,

    // add-on tertiary colors
    TertiaryFixed,
    OnTertiaryFixed,
    TertiaryFixedDim,
    OnTertiaryFixedVariant,

    // add-on surface colors
    Background,
    OnBackground,
    SurfaceBright,
    SurfaceDim,
    Scrim,
    Shadow,
} MaterialColorRole;

void hct_from_int(uint32_t argb, Hct *out);

uint32_t hct_to_int(const Hct *hct);

void *dynamic_scheme_init(
        const Hct *source_color_hct,
        Variant variant,
        int is_dark,
        double contrast_level
);

void dynamic_scheme_free(void *dynamic_scheme);

uint32_t dynamic_scheme_get_material_dynamic_color(
        const void *dynamic_scheme,
        MaterialColorRole material_color_role
);

void dynamic_scheme_get_tonal_palette(
        const void *dynamic_scheme,
        DynamicSchemeTonalPalette dynamic_scheme_tonal_palette,
        TonalPalette *out
);

#ifdef __cplusplus
}
#endif

#endif // MCU_H
