import org.gradle.api.JavaVersion

object AndroidConfig {
    val compileSdkVersionStr = "android-33"
    val compileSdkVersion = 33
    val targetSdkVersion = 33
    val minSdkVersion = 24 // for release min 21
    val buildToolsVersion = "33.0.1"
    val applicationId = "ru.kirillashikhmin.tauth"
    val javaVersion = JavaVersion.VERSION_11

    val versionCode = 1
    val versionName = "0.0.1"
}
