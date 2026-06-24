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
        // Override the Java 8 target inherited from `anthropic.kotlin` so we can reference MCP SDK
        // types that extend `java.lang.Record` (Java 16+). Swap only `-Xjdk-release` rather than
        // reassigning the list, so the convention plugin's warning-suppression flags stay in sync.
        freeCompilerArgs.set(
            freeCompilerArgs.get().map { if (it.startsWith("-Xjdk-release=")) "-Xjdk-release=17" else it }
        )
        jvmTarget.set(JvmTarget.JVM_17)
    }
}
