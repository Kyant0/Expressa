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
    uint32_t argb;
} Hct;

__attribute__((unused))
typedef enum {
    Neutral,
    TonalSpot,
    Vibrant,
    Expressive,
} Variant;

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

// primary colors

uint32_t dynamic_scheme_get_primary(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_primary(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_primary_container(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_primary_container(const void *dynamic_scheme);

// secondary colors

uint32_t dynamic_scheme_get_secondary(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_secondary(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_secondary_container(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_secondary_container(const void *dynamic_scheme);

// tertiary colors

uint32_t dynamic_scheme_get_tertiary(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_tertiary(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_tertiary_container(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_tertiary_container(const void *dynamic_scheme);

// error colors

uint32_t dynamic_scheme_get_error(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_error(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_error_container(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_error_container(const void *dynamic_scheme);

// surface colors

uint32_t dynamic_scheme_get_surface(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_surface(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_surface_variant(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_surface_variant(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_surface_container_highest(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_surface_container_high(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_surface_container(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_surface_container_low(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_surface_container_lowest(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_inverse_surface(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_inverse_on_surface(const void *dynamic_scheme);

// outline colors

uint32_t dynamic_scheme_get_outline(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_outline_variant(const void *dynamic_scheme);

// add-on primary colors

uint32_t dynamic_scheme_get_primary_fixed(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_primary_fixed(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_primary_fixed_dim(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_primary_fixed_variant(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_inverse_primary(const void *dynamic_scheme);

// add-on secondary colors

uint32_t dynamic_scheme_get_secondary_fixed(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_secondary_fixed(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_secondary_fixed_dim(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_secondary_fixed_variant(const void *dynamic_scheme);

// add-on tertiary colors

uint32_t dynamic_scheme_get_tertiary_fixed(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_tertiary_fixed(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_tertiary_fixed_dim(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_tertiary_fixed_variant(const void *dynamic_scheme);

// add-on surface colors

uint32_t dynamic_scheme_get_background(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_on_background(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_surface_bright(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_surface_dim(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_scrim(const void *dynamic_scheme);

uint32_t dynamic_scheme_get_shadow(const void *dynamic_scheme);

#ifdef __cplusplus
}
#endif

#endif // MCU_H
