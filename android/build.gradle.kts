plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

group = "xyz.gmfatoom"
version = "1.0-SNAPSHOT"


kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(compose.material)
            }
        }
    }
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "xyz.gmfatoom.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
/*    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }*/
}