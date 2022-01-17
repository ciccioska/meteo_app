import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.6.10"
}

kotlin {
    val ktorVersion = "1.6.0"
    val libName = "shared"
    val coroutinesVersion = "1.4.2-native-mt"

    android()

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        //iosSimulatorArm64()// sure all ios dependencies support this target
    ).forEach {
        it.binaries.framework {
            baseName = libName
            xcf.add(this)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")

            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
        val iosMain by creating {
            val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
            if (onPhone) {
                iosArm64("ios")
            } else {
                iosX64("ios")
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
            //iosSimulatorArm64Main.dependsOn(this)
        }
        //val iosX64Test by getting
        //val iosArm64Test by getting
        //val iosSimulatorArm64Test by getting
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 26
        targetSdk = 31
    }
}