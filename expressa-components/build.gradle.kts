import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("androidx.baselineprofile")
    id("dev.shreyaspatil.compose-compiler-report-generator")
}

android {
    namespace = "com.kyant.expressa.components"
    compileSdk = 36
    buildToolsVersion = "36.0.0"

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_21
            freeCompilerArgs.addAll(
                "-jvm-default=no-compatibility",
                "-Xcontext-parameters",
            )
        }
    }
    buildFeatures {
        compose = true
    }
}

baselineProfile {
    saveInSrc = true
    filter {
        include("com.kyant.expressa.components.**")
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    api(project(":expressa-core"))
    baselineProfile(project(":baselineprofile"))
}
