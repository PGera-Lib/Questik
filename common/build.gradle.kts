plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.squareup.sqldelight")
}

kotlin {
    androidTarget {
/*        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }*/
    }

/*    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export("dev.icerock.moko:mvvm-core:0.16.1")
        }
    }*/
    jvm("desktop") {
        jvmToolchain(11)
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "common"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)

                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation("com.squareup.sqldelight:runtime:1.5.5")
                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.5")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                // #Precompose
                api(compose.foundation)
                api(compose.animation)
                api("moe.tlaster:precompose-viewmodel:1.5.1") // For ViewModel intergration
                api("moe.tlaster:precompose:1.5.1")
// api("moe.tlaster:precompose-molecule:$precompose_version") // For Molecule intergration

            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.activity:activity-compose:1.7.2")
                api("androidx.appcompat:appcompat:1.6.1")
                implementation("androidx.core:core-ktx:1.10.1")
                implementation("com.squareup.sqldelight:android-driver:1.5.5")
                implementation("org.jetbrains.compose.ui:ui-tooling-preview:1.5.1")

            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.5.5")
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation("com.squareup.sqldelight:sqlite-driver:1.5.5")
                implementation("org.jetbrains.compose.material3:material3-desktop:1.4.1")
                implementation("org.jetbrains.compose.ui:ui-tooling-preview:1.5.1")
            }
        }
    }
}

android {
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    defaultConfig {
        minSdk = 26
        targetSdk = 34
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
sqldelight {
    database("reQuestikDatabase") {
        packageName = "xyz.gmfatoom.common.database"
        sourceFolders = listOf("sqldelight")
    }
}

/*
dependencies {
    implementation("androidx.core:core:1.10.1")
    implementation("androidx.lifecycle:lifecycle-livedata-core-ktx:2.6.1")
    commonMainApi("dev.icerock.moko:mvvm-core:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-compose:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow-compose:0.16.1")
}*/
