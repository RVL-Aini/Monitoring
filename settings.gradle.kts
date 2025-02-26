pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
//    plugins {
//       id("com.android.application") version "8.1.1"
//        id("org.jetbrains.kotlin.android") version "1.9.21"
//    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Monitoring"
include (":app")
