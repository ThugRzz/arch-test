plugins {
    kotlin("multiplatform") version "1.7.20"
    id("com.android.library")
    id("maven-publish")
}

group = "me.thugrzz"
version = "1.0"

val coroutinesVersion = "1.6.2"
val ktorVersion = "2.0.2"
val kodeinVersion = "7.12.0"

repositories {
    google()
    mavenCentral()
    mavenLocal()
}

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }
    ios {
        binaries {
            framework {
                baseName = "arch-test"
            }
        }
    }

    sourceSets {
        val commonMain by getting{
            dependencies{
                //Kodein
                implementation("org.kodein.di:kodein-di:$kodeinVersion")

                //Http ktor
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-json:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                //Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            dependencies {
                //Http ktor
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                //Http Ktor
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

                //ViewModel
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

                //Material
                implementation("com.google.android.material:material:1.7.0")

                //Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
            }
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}