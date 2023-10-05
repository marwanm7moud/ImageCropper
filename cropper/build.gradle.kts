import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("convention.publication")
    id("maven-publish")
    id("org.jetbrains.dokka") version "1.5.0"
}
group = "io.github.marwanm7moud"
version = "Beta-0.0.4"

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()
    android{
        publishLibraryVariants("debug","release" )
    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    jvm("desktop")

    ios{}
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "cropper"
        }
    }

    sourceSets {

        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.material3)
                implementation(compose.foundation)

            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activityCompose)
                implementation(libs.compose.uitooling)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)
            }
        }

        val iosMain by getting {
            dependencies {
            }
        }

    }

}

android {
    namespace = "com.awesome.cropper"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
dependencies {
    implementation(libs.androidx.ui.graphics.android)
}
tasks.named("publishDesktopPublicationToSonatypeRepository").configure { dependsOn("signKotlinMultiplatformPublication") }
tasks.named("publishDesktopPublicationToSonatypeRepository").configure { mustRunAfter("signAndroidReleasePublication") }
tasks.named("publishKotlinMultiplatformPublicationToSonatypeRepository").configure { dependsOn("signDesktopPublication") }
tasks.named("publishKotlinMultiplatformPublicationToSonatypeRepository").configure { mustRunAfter("signKotlinMultiplatformPublication") }
tasks.named("publishDesktopPublicationToMavenLocal").configure { dependsOn("signKotlinMultiplatformPublication") }
tasks.named("publishKotlinMultiplatformPublicationToMavenLocal").configure { dependsOn("signDesktopPublication") }
tasks.named("signDesktopPublication").configure { mustRunAfter("publishAndroidReleasePublicationToSonatypeRepository") }
tasks.named("signDesktopPublication").configure { mustRunAfter("publishAndroidDebugPublicationToSonatypeRepository") }
tasks.named("signKotlinMultiplatformPublication").configure { dependsOn("publishAndroidDebugPublicationToSonatypeRepository") }


val dokkaOutputDir = "$buildDir/dokka"

tasks.dokkaHtml {
    outputDirectory.set(file(dokkaOutputDir))
}

val deleteDokkaOutputDir by tasks.register<Delete>("deleteDokkaOutputDirectory") {
    delete(dokkaOutputDir)
}