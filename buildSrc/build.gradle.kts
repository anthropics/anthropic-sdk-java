plugins {
    // Note: don't also apply `kotlin("jvm")` here. `kotlin-dsl` compiles this project with
    // Gradle's embedded Kotlin; applying the standalone Kotlin plugin overrides that pairing with
    // whatever version is on this classpath, which the Kotlin DSL does not support.
    `kotlin-dsl`
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
}
