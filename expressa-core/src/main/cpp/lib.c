#include <jni.h>
#include <android/log.h>

#include "mcu_rs.h"

JNIEXPORT jdoubleArray JNICALL
Java_com_kyant_expressa_graphics_Hct_00024Companion_hctFromInt(
        JNIEnv *env, jobject,
        jint argb
) {
    Hct out;
    hct_from_int(argb, &out);

    jdoubleArray result = (*env)->NewDoubleArray(env, 3);
    if (result == nullptr) {
        return nullptr;
    }

    jdouble values[3] = {out.hue, out.chroma, out.tone};
    (*env)->SetDoubleArrayRegion(env, result, 0, 3, values);
    return result;
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_graphics_Hct_00024Companion_hctToInt(
        JNIEnv *, jobject,
        jdouble hue,
        jdouble chroma,
        jdouble tone
) {
    Hct hct = {
            .hue = hue,
            .chroma = chroma,
            .tone = tone,
    };
    uint32_t argb = hct_to_int(&hct);
    return (jint) argb;
}

JNIEXPORT jlong JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeInit(
        JNIEnv *, jobject,
        jint argb,
        jint variant,
        jboolean is_dark,
        jdouble contrast_level
) {
    Hct source_color_hct;
    hct_from_int(argb, &source_color_hct);
    void *dynamic_scheme = dynamic_scheme_init(&source_color_hct, (Variant) variant, is_dark, contrast_level);
    return (jlong) dynamic_scheme;
}

JNIEXPORT void JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeFree(
        JNIEnv *, jobject,
        jlong native_handle
) {
    void *dynamic_scheme = (void *) native_handle;
    dynamic_scheme_free(dynamic_scheme);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetMaterialDynamicColor(
        JNIEnv *, jobject,
        jlong native_handle,
        jint material_color_role
) {
    void *dynamic_scheme = (void *) native_handle;
    const uint32_t color = dynamic_scheme_get_material_dynamic_color(dynamic_scheme,
                                                                     (MaterialColorRole) material_color_role);
    return (jint) color;
}

// primary colors

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetPrimary(JNIEnv *, jobject,
                                                                                                 jlong native_handle) {
    return (jint) dynamic_scheme_get_primary((void *) native_handle);
}

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnPrimary(JNIEnv *, jobject,
                                                                                                   jlong native_handle) {
    return (jint) dynamic_scheme_get_on_primary((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetPrimaryContainer(JNIEnv *, jobject,
                                                                                   jlong native_handle) {
    return (jint) dynamic_scheme_get_primary_container((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnPrimaryContainer(JNIEnv *, jobject,
                                                                                     jlong native_handle) {
    return (jint) dynamic_scheme_get_on_primary_container((void *) native_handle);
}

// secondary colors

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetSecondary(JNIEnv *, jobject,
                                                                                                   jlong native_handle) {
    return (jint) dynamic_scheme_get_secondary((void *) native_handle);
}

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnSecondary(JNIEnv *, jobject,
                                                                                                     jlong native_handle) {
    return (jint) dynamic_scheme_get_on_secondary((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetSecondaryContainer(JNIEnv *, jobject,
                                                                                     jlong native_handle) {
    return (jint) dynamic_scheme_get_secondary_container((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnSecondaryContainer(JNIEnv *, jobject,
                                                                                       jlong native_handle) {
    return (jint) dynamic_scheme_get_on_secondary_container((void *) native_handle);
}

// tertiary colors

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetTertiary(JNIEnv *, jobject,
                                                                                                  jlong native_handle) {
    return (jint) dynamic_scheme_get_tertiary((void *) native_handle);
}

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnTertiary(JNIEnv *, jobject,
                                                                                                    jlong native_handle) {
    return (jint) dynamic_scheme_get_on_tertiary((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetTertiaryContainer(JNIEnv *, jobject,
                                                                                    jlong native_handle) {
    return (jint) dynamic_scheme_get_tertiary_container((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnTertiaryContainer(JNIEnv *, jobject,
                                                                                      jlong native_handle) {
    return (jint) dynamic_scheme_get_on_tertiary_container((void *) native_handle);
}

// error colors

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetError(JNIEnv *, jobject,
                                                                                               jlong native_handle) {
    return (jint) dynamic_scheme_get_error((void *) native_handle);
}

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnError(JNIEnv *, jobject,
                                                                                                 jlong native_handle) {
    return (jint) dynamic_scheme_get_on_error((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetErrorContainer(JNIEnv *, jobject,
                                                                                 jlong native_handle) {
    return (jint) dynamic_scheme_get_error_container((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnErrorContainer(JNIEnv *, jobject,
                                                                                   jlong native_handle) {
    return (jint) dynamic_scheme_get_on_error_container((void *) native_handle);
}

// surface colors

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetSurface(JNIEnv *, jobject,
                                                                                                 jlong native_handle) {
    return (jint) dynamic_scheme_get_surface((void *) native_handle);
}

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnSurface(JNIEnv *, jobject,
                                                                                                   jlong native_handle) {
    return (jint) dynamic_scheme_get_on_surface((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetSurfaceVariant(JNIEnv *, jobject,
                                                                                 jlong native_handle) {
    return (jint) dynamic_scheme_get_surface_variant((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnSurfaceVariant(JNIEnv *, jobject,
                                                                                   jlong native_handle) {
    return (jint) dynamic_scheme_get_on_surface_variant((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetSurfaceContainerHighest(JNIEnv *, jobject,
                                                                                          jlong native_handle) {
    return (jint) dynamic_scheme_get_surface_container_highest((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetSurfaceContainerHigh(JNIEnv *, jobject,
                                                                                       jlong native_handle) {
    return (jint) dynamic_scheme_get_surface_container_high((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetSurfaceContainer(JNIEnv *, jobject,
                                                                                   jlong native_handle) {
    return (jint) dynamic_scheme_get_surface_container((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetSurfaceContainerLow(JNIEnv *, jobject,
                                                                                      jlong native_handle) {
    return (jint) dynamic_scheme_get_surface_container_low((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetSurfaceContainerLowest(JNIEnv *, jobject,
                                                                                         jlong native_handle) {
    return (jint) dynamic_scheme_get_surface_container_lowest((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetInverseSurface(JNIEnv *, jobject,
                                                                                 jlong native_handle) {
    return (jint) dynamic_scheme_get_inverse_surface((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetInverseOnSurface(JNIEnv *, jobject,
                                                                                   jlong native_handle) {
    return (jint) dynamic_scheme_get_inverse_on_surface((void *) native_handle);
}

// outline colors

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOutline(JNIEnv *, jobject,
                                                                                                 jlong native_handle) {
    return (jint) dynamic_scheme_get_outline((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOutlineVariant(JNIEnv *, jobject,
                                                                                 jlong native_handle) {
    return (jint) dynamic_scheme_get_outline_variant((void *) native_handle);
}

// add-on primary colors

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetPrimaryFixed(JNIEnv *, jobject,
                                                                                                      jlong native_handle) {
    return (jint) dynamic_scheme_get_primary_fixed((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnPrimaryFixed(JNIEnv *, jobject,
                                                                                 jlong native_handle) {
    return (jint) dynamic_scheme_get_on_primary_fixed((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetPrimaryFixedDim(JNIEnv *, jobject,
                                                                                  jlong native_handle) {
    return (jint) dynamic_scheme_get_primary_fixed_dim((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnPrimaryFixedVariant(JNIEnv *, jobject,
                                                                                        jlong native_handle) {
    return (jint) dynamic_scheme_get_on_primary_fixed_variant((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetInversePrimary(JNIEnv *, jobject,
                                                                                 jlong native_handle) {
    return (jint) dynamic_scheme_get_inverse_primary((void *) native_handle);
}

// add-on secondary colors

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetSecondaryFixed(JNIEnv *, jobject,
                                                                                 jlong native_handle) {
    return (jint) dynamic_scheme_get_secondary_fixed((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnSecondaryFixed(JNIEnv *, jobject,
                                                                                   jlong native_handle) {
    return (jint) dynamic_scheme_get_on_secondary_fixed((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetSecondaryFixedDim(JNIEnv *, jobject,
                                                                                    jlong native_handle) {
    return (jint) dynamic_scheme_get_secondary_fixed_dim((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnSecondaryFixedVariant(JNIEnv *, jobject,
                                                                                          jlong native_handle) {
    return (jint) dynamic_scheme_get_on_secondary_fixed_variant((void *) native_handle);
}

// add-on tertiary colors

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetTertiaryFixed(JNIEnv *, jobject,
                                                                                jlong native_handle) {
    return (jint) dynamic_scheme_get_tertiary_fixed((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnTertiaryFixed(JNIEnv *, jobject,
                                                                                  jlong native_handle) {
    return (jint) dynamic_scheme_get_on_tertiary_fixed((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetTertiaryFixedDim(JNIEnv *, jobject,
                                                                                   jlong native_handle) {
    return (jint) dynamic_scheme_get_tertiary_fixed_dim((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnTertiaryFixedVariant(JNIEnv *, jobject,
                                                                                         jlong native_handle) {
    return (jint) dynamic_scheme_get_on_tertiary_fixed_variant((void *) native_handle);
}

// add-on surface colors

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetBackground(JNIEnv *, jobject,
                                                                                                    jlong native_handle) {
    return (jint) dynamic_scheme_get_background((void *) native_handle);
}

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetOnBackground(JNIEnv *, jobject,
                                                                                                      jlong native_handle) {
    return (jint) dynamic_scheme_get_on_background((void *) native_handle);
}

JNIEXPORT jint JNICALL
Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetSurfaceBright(JNIEnv *, jobject,
                                                                                jlong native_handle) {
    return (jint) dynamic_scheme_get_surface_bright((void *) native_handle);
}

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetSurfaceDim(JNIEnv *, jobject,
                                                                                                    jlong native_handle) {
    return (jint) dynamic_scheme_get_surface_dim((void *) native_handle);
}

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetScrim(JNIEnv *, jobject,
                                                                                               jlong native_handle) {
    return (jint) dynamic_scheme_get_scrim((void *) native_handle);
}

JNIEXPORT jint JNICALL Java_com_kyant_expressa_mcu_DynamicScheme_00024Companion_nativeGetShadow(JNIEnv *, jobject,
                                                                                                jlong native_handle) {
    return (jint) dynamic_scheme_get_shadow((void *) native_handle);
}
