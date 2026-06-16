import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("anthropic.kotlin")
    id("anthropic.publish")
}

dependencies {
    api(project(":anthropic-java-core"))

    // The MCP Java SDK is exposed in this module's public API (e.g. `BetaMcp.mcpTool` accepts
    // `McpSchema.Tool` and `McpSyncClient`), so it must be an `api` dependency to be transitively
    // visible to consumers.
    api(libs.mcp)

    testImplementation(project(":anthropic-java-client-okhttp"))
    testImplementation(kotlin("test"))
    testImplementation(libs.assertj)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
}

// The MCP Java SDK uses Java Records (Java 16+), so this module requires Java 17+ at compile and
// runtime. Consumers that don't need MCP support should depend on `anthropic-java-core` directly,
// which still targets Java 8.
tasks.withType<JavaCompile>().configureEach { options.release.set(17) }

kotlin {
    compilerOptions {
        // Override the Java 8 settings inherited from `anthropic.kotlin` so we can reference MCP
        // SDK types that extend `java.lang.Record` (introduced in Java 16). This reassigns the
        // whole list, so the flags shared with `anthropic.kotlin` must be repeated here —
        // `-Xsuppress-version-warnings` in particular is still needed because we inherit
        // `languageVersion = 2.0`, whose deprecation notice `-nowarn` does not cover.
        freeCompilerArgs = listOf("-Xjdk-release=17", "-nowarn", "-Xsuppress-version-warnings")
        jvmTarget.set(JvmTarget.JVM_17)
    }
}
