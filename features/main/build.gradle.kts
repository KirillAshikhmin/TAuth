plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.parcelize")
}

android {

    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion
        targetSdk = AndroidConfig.targetSdkVersion
        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kapt {
        correctErrorTypes = true
    }


    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    namespace = "ru.kirillashikhmin.tauth.features.main"
}


dependencies {
    implementation(projects.common)
    implementation(projects.core)
    implementation(projects.shared)

    coreLibraryDesugaring(libs.desugar)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.jdk8)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)


    implementation(libs.activitycompose)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
    implementation(libs.navigation.compose)


    implementation(libs.orbit.core)
    implementation(libs.orbit.viewmodel)
    implementation(libs.orbit.compose)


    implementation(libs.hiltandroid)
    kapt(libs.hiltcompiler)
    kapt(libs.kotlinxmetadata)

    implementation(libs.hilt.navigation.compose)

    implementation(libs.androidxcore)
    implementation(libs.appcompat)
    implementation(libs.fragment)
    implementation(libs.activity)
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.savedstate)
    implementation(libs.lifecycle.ext)

    implementation(libs.serialization.core)

    implementation(libs.sesame.property)
    implementation(libs.sesame.localized)


}
