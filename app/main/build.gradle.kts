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

    namespace = "ru.kirillashikhmin.tauth.main"
}


dependencies {
    implementation(project(":common"))
    implementation("com.google.android.material:material:1.7.0")

    coreLibraryDesugaring(libs.desugar)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.jdk8)

    implementation(libs.datetime)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.serialization.core)

    implementation(libs.androidxcore)
    implementation(libs.appcompat)
    implementation(libs.fragment)
    implementation(libs.activity)
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.savedstate)
    implementation(libs.lifecycle.ext)

    implementation(libs.binaryprefs)

    implementation(libs.zxing)
    implementation(libs.timber)

    implementation(libs.sesame.property)
    implementation(libs.sesame.localized)


}
