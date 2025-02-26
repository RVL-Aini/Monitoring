plugins {
    kotlin("android") version "1.9.24" apply false
    id("com.android.application") version "8.1.1" apply false
}
allprojects{
    repositories{
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

kotlin {
    jvmToolchain(17)
}
