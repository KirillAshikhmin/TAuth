@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.parcelize")
    id("dagger.hilt.android.plugin")
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
    namespace = "ru.kirillashikhmin.tauth"
}

tasks.whenTaskAdded {
    if (this.name.contains("assembleRelease")) {
        this.doLast {
            val release =
                android.applicationVariants.firstOrNull { v -> v.buildType.name == "release" }
                    ?: return@doLast
            val output = release.outputs.firstOrNull() ?: return@doLast

            val json =
                "{\"version\":\"${release.versionName}\",\"build\":\"${release.versionCode}\",\"link\":\"https://update.dev.trac.i.adt.arrival.co/Traceability_${release.versionName}.apk\"}"
            val file = File(output.outputFile.parent, "update.json")
            file.writeText(json)
            val apk = File(output.outputFile.parent, output.outputFile.name)
            val apkNew = File(output.outputFile.parent, "Traceability_${release.versionName}.apk")
            apk.renameTo(apkNew)
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":common"))
    implementation(project(":core"))

    coreLibraryDesugaring(libs.desugar)

    testImplementation(libs.junit)

    debugImplementation(libs.leakcanary)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.jdk8)

    implementation(libs.datetime)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.serialization.core)


/*
    implementation 'androidx.core:core-ktx:1.9.0'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'
    implementation 'androidx.activity:activity-compose:1.6.1'
    implementation "androidx.compose.ui:ui:$compose_version"
    implementation "androidx.compose.ui:ui-tooling-preview:$compose_version"
    implementation 'androidx.compose.material3:material3:1.0.1'
    androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
    debugImplementation "androidx.compose.ui:ui-tooling:$compose_version"
    debugImplementation "androidx.compose.ui:ui-test-manifest:$compose_version"
    implementation "androidx.navigation:navigation-compose:2.5.3"

    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.4'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.0'
*/

    implementation(libs.activitycompose)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
    implementation(libs.navigation.compose)

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
