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
