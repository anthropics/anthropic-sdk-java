import org.gradle.api.artifacts.dsl.LockMode

plugins {
    // Note: don't also apply `kotlin("jvm")` here. `kotlin-dsl` compiles this project with
    // Gradle's embedded Kotlin; applying the standalone Kotlin plugin overrides that pairing with
    // whatever version is on this classpath, which the Kotlin DSL does not support.
    `kotlin-dsl`
}

// Lock the plugin classpath the convention scripts compile against, so the buildscript side of
// the build is as reproducible as the libraries it produces. Regenerate alongside the per-module
// lockfiles via `./scripts/lock` (the `--write-locks` flag propagates to `buildSrc`).
dependencyLocking {
    lockAllConfigurations()
    lockMode.set(LockMode.STRICT)
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

// Plugins used by the precompiled convention scripts in `src/main/kotlin` must be declared as
// regular dependencies here (the scripts' `plugins {}` blocks can't carry versions).
dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.maven.publish.gradle.plugin)
    implementation(libs.dokka.gradle.plugin)
    // Not a real Gradle plugin (it publishes no plugin marker): just a library carrying
    // `proguard.gradle.ProGuardTask` for `anthropic-java-ecosystem-test`.
    implementation(libs.proguard.gradle.plugin)
    implementation(libs.shadow.gradle.plugin)
}
