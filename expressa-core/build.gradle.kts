import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("androidx.baselineprofile")
    id("dev.shreyaspatil.compose-compiler-report-generator")
}

android {
    namespace = "com.kyant.expressa"
    compileSdk = 36
    buildToolsVersion = "36.0.0"
    ndkVersion = "29.0.13599879"

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    externalNativeBuild {
        cmake {
            path("src/main/cpp/CMakeLists.txt")
            version = "4.0.2"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlin {
        compilerOptions {
            apiVersion = KotlinVersion.KOTLIN_2_3
            languageVersion = KotlinVersion.KOTLIN_2_3
            jvmTarget = JvmTarget.JVM_21
            freeCompilerArgs.addAll(
                "-jvm-default=no-compatibility",
                "-Xcontext-parameters",
                "-Xcontext-sensetive-resolution",
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeCompiler {
        stabilityConfigurationFiles = listOf(layout.projectDirectory.file("compose-stability.config"))
    }
}

baselineProfile {
    saveInSrc = true
    filter {
        exclude("com.kyant.expressa.catalog.**")
        exclude("com.kyant.expressa.components.**")
        include("com.kyant.expressa.**")
    }
}

dependencies {
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.foundation)
    api(libs.androidx.material.ripple)
    api(libs.androidx.graphics.shapes)
    baselineProfile(project(":baselineprofile"))
}
