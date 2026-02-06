/**
 * Configuración de proyecto TLG Transporte Chofer
 * Path: settings.gradle.kts
 * Última modificación: 2026-02-05 14:30
 * Dev: cpalacios01@gmail.com | +595994648273
 */

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TLGTransporteChofer"
include(":app")