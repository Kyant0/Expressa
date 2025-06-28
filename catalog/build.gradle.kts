import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.baselineprofile)
}

android {
    namespace = "com.kyant.expressa.catalog"
    compileSdk = 36
    buildToolsVersion = "36.0.0"

    defaultConfig {
        applicationId = "com.kyant.expressa.catalog"
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "0.1.0-dev"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            vcsInfo.include = false
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
                "-Xcontext-sensitive-resolution",
            )
        }
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            pickFirsts += arrayOf(
                "META-INF/androidx.compose.ui_ui.version"
            )
            excludes += arrayOf(
                "DebugProbesKt.bin",
                "kotlin-tooling-metadata.json",
                "kotlin/**",
                "META-INF/*.version",
                "META-INF/**/LICENSE.txt"
            )
        }
        dex {
            useLegacyPackaging = true
        }
        jniLibs {
            useLegacyPackaging = true
        }
    }
    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }
    lint {
        checkReleaseBuilds = false
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.runtime) {
        version {
            strictly("1.9.0-alpha04")
        }
    }
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.protobuf)
    implementation(project(":expressa-core"))
    implementation(project(":expressa-components"))
    implementation(libs.androidx.profileinstaller)
    "baselineProfile"(project(":baselineprofile"))
}
