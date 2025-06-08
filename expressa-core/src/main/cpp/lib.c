#include <jni.h>

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
    void *dynamic_scheme = dynamic_scheme_init(
            &source_color_hct,
            (Variant) variant,
            is_dark,
            contrast_level
    );
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
    const uint32_t color = dynamic_scheme_get_material_dynamic_color(
            dynamic_scheme,
            (MaterialColorRole) material_color_role
    );
    return (jint) color;
}
