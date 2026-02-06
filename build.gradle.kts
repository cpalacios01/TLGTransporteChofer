/**
 * Build configuration raíz
 * Path: build.gradle.kts
 * Última modificación: 2026-02-05 16:00
 * Dev: cpalacios01@gmail.com | +595994648273
 */

plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}