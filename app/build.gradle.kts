@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.parcelize")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdkVersion = AndroidConfig.compileSdkVersionStr
    buildToolsVersion = AndroidConfig.buildToolsVersion

    defaultConfig {
        applicationId = AndroidConfig.applicationId
        minSdk = AndroidConfig.minSdkVersion
        targetSdk = AndroidConfig.targetSdkVersion
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        manifestPlaceholders["appAuthRedirectScheme"] = AndroidConfig.applicationId
        ndk {
            abiFilters.add("armeabi-v7a")
            abiFilters.add("arm64-v8a")
            abiFilters.add("x86")
            abiFilters.add("x86_64")
        }
    }


    signingConfigs {
        create("release") {
            storeFile = file("\\key\\keystore.jks")
            storePassword = "store"
            keyAlias = "alias"
            keyPassword = "key"
            enableV1Signing = true
            enableV2Signing = true
        }
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
        }
        create("releaseDebuggable") {
            initWith(getByName("release"))
            isDebuggable = true
            versionNameSuffix = "-rd"
        }
    }

    compileOptions {
        // isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = AndroidConfig.javaVersion
        targetCompatibility = AndroidConfig.javaVersion
    }

    kotlinOptions {
        jvmTarget = "11"
        kotlinOptions.freeCompilerArgs =
            listOf(*kotlinOptions.freeCompilerArgs.toTypedArray(),
                "-opt-in=kotlin.ExperimentalStdlibApi",
                "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.ObsoleteCoroutinesApi",
                "-opt-in=kotlin.RequiresOptIn")
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }


    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
    kapt {
        correctErrorTypes = true
    }


    namespace = "ru.kirillashikhmin.tauth"
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(projects.common)
    implementation(projects.core)
    implementation(projects.shared)

    implementation(projects.features.main)

    coreLibraryDesugaring(libs.desugar)

    testImplementation(libs.junit)

    debugImplementation(libs.leakcanary)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.jdk8)

    implementation(libs.datetime)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.serialization.core)


    implementation(libs.activitycompose)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
    implementation(libs.navigation.compose)

    implementation(libs.hilt.navigation.compose)

    implementation(libs.orbit.core)
    implementation(libs.orbit.viewmodel)
    implementation(libs.orbit.compose)

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

    implementation(libs.hiltandroid)
    kapt(libs.hiltcompiler)
    kapt(libs.kotlinxmetadata)

    implementation(libs.zxing)
    implementation(libs.timber)
    implementation(libs.coil)

    implementation(libs.sesame.property)
    implementation(libs.sesame.localized)
}
