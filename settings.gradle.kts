enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "TAuth"
rootProject.buildFileName = "build.gradle.kts"

include(":common")
include(":core")
include(":shared")
include(":app")

include(":tech:totp")

include(":features:main")

/**
 * You can check dependencies versions by run in terminal:
 * .\gradlew dependencyUpdates
 */

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    versionCatalogs {

        create("libs") {
            val compose = version("compose_version", "1.3.3")
            val hilt = version("hilt_version", "2.44.2")
            val kotlin = version("kotlin_version", "1.7.20")
            val coroutines = version("coroutines_version", "1.6.4")
            val serialization = version("serialization_version", "1.4.1")
            val sesame = version("sesame_version", "1.5.0")
            val lifecycle = version("lifecycle_version", "2.5.1")
            val orbit = version("orbit_version", "4.5.0")

            library("desugar", "com.android.tools", "desugar_jdk_libs").version("1.2.0")

            library("junit", "junit", "junit").version("4.13.2")

            library("kotlin.stdlib", "org.jetbrains.kotlin", "kotlin-stdlib").versionRef(kotlin)
            library("kotlin.jdk7", "org.jetbrains.kotlin", "kotlin-stdlib-jdk7").versionRef(kotlin)
            library("kotlin.jdk8", "org.jetbrains.kotlin", "kotlin-stdlib-jdk8").versionRef(kotlin)

            library("dagger2", "com.google.dagger", "dagger").versionRef(hilt)
            library("hiltandroid", "com.google.dagger", "hilt-android").versionRef(hilt)
            library("hiltcompiler", "com.google.dagger", "hilt-compiler").versionRef(hilt)
            library("kotlinxmetadata", "org.jetbrains.kotlinx", "kotlinx-metadata-jvm").version("0.5.0")

            library("androidxcore", "androidx.core", "core-ktx").version("1.9.0")
            library("appcompat", "androidx.appcompat", "appcompat").version("1.6.0")
            library("fragment", "androidx.fragment", "fragment-ktx").version("1.5.5")
            library("activity", "androidx.activity", "activity-ktx").version("1.6.1")
            library("activitycompose", "androidx.activity", "activity-compose").version("1.6.1")
            library("navigation.compose", "androidx.navigation", "navigation-compose").version("2.5.3")

            library("hilt.navigation.compose", "androidx.hilt", "hilt-navigation-compose").version("1.0.0")


            library("compose.ui", "androidx.compose.ui", "ui").versionRef(compose)
            library("compose.material3", "androidx.compose.material3", "material3").version("1.0.1")
            library("compose.ui-tooling-preview", "androidx.compose.ui", "ui-tooling-preview").versionRef(compose)
            library("compose.ui-test-junit4", "androidx.compose.ui", "ui-test-junit4").versionRef(compose)
            library("compose.ui-tooling", "androidx.compose.ui", "ui-tooling").versionRef(compose)
            library("compose.ui-test-manifest", "androidx.compose.ui", "ui-test-manifest").versionRef(compose)

            library("orbit.core", "org.orbit-mvi", "orbit-core").versionRef(orbit)
            library("orbit.viewmodel", "org.orbit-mvi", "orbit-viewmodel").versionRef(orbit)
            library("orbit.compose", "org.orbit-mvi", "orbit-compose").versionRef(orbit)
            library("orbit.test", "org.orbit-mvi", "orbit-test").versionRef(orbit)


            library("lifecycle.runtime", "androidx.lifecycle", "lifecycle-runtime-ktx").versionRef(lifecycle)
            library("lifecycle.viewmodel", "androidx.lifecycle", "lifecycle-viewmodel-ktx").versionRef(lifecycle)
            library("lifecycle.livedata", "androidx.lifecycle", "lifecycle-livedata-ktx").versionRef(lifecycle)
            library("lifecycle.savedstate", "androidx.lifecycle", "lifecycle-viewmodel-savedstate").versionRef(lifecycle)
            library("lifecycle.ext", "androidx.lifecycle", "lifecycle-extensions").version("2.2.0")


            library("coroutines-core", "org.jetbrains.kotlinx", "kotlinx-coroutines-core").versionRef(coroutines)
            library("coroutines-android", "org.jetbrains.kotlinx", "kotlinx-coroutines-android").versionRef(coroutines)

            library("serialization-core", "org.jetbrains.kotlinx", "kotlinx-serialization-core").versionRef(serialization)
            library("serialization-json", "org.jetbrains.kotlinx", "kotlinx-serialization-json").versionRef(serialization)

            library("leakcanary", "com.squareup.leakcanary", "leakcanary-android").version("2.10")

            library("timber", "com.jakewharton.timber", "timber").version("5.0.1")
            library("zxing", "com.journeyapps", "zxing-android-embedded").version("4.3.0")
            library("binaryprefs", "com.github.yandextaxitech", "binaryprefs").version("1.0.1")
            library("datetime", "org.jetbrains.kotlinx", "kotlinx-datetime").version("0.4.0")

            library("coil", "io.coil-kt", "coil").version("2.2.2")

            library("sesame.property", "com.github.aartikov", "sesame-property").versionRef(sesame)
            library("sesame.localized", "com.github.aartikov", "sesame-localized-string").versionRef(sesame)
        }
    }
}
