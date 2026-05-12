import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    id("anthropic.kotlin")
    id("anthropic.publish")
}

dependencies {
    api(project(":anthropic-java-core"))

    // The MCP Java SDK is exposed in this module's public API (e.g. `BetaMcp.mcpTool` accepts
    // `McpSchema.Tool` and `McpSyncClient`), so it must be an `api` dependency to be transitively
    // visible to consumers.
    api("io.modelcontextprotocol.sdk:mcp:1.1.1")

    testImplementation(project(":anthropic-java-client-okhttp"))
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.27.7")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.3")
    testImplementation("org.mockito:mockito-core:5.14.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
}

// The MCP Java SDK uses Java Records (Java 16+), so this module requires Java 17+ at compile and
// runtime. Consumers that don't need MCP support should depend on `anthropic-java-core` directly,
// which still targets Java 8.
tasks.withType<JavaCompile>().configureEach { options.release.set(17) }

kotlin {
    compilerOptions {
        // Override the Java 8 settings inherited from `anthropic.kotlin` so we can reference MCP
        // SDK types that extend `java.lang.Record` (introduced in Java 16).
        freeCompilerArgs = listOf("-Xjvm-default=all", "-Xjdk-release=17", "-nowarn")
        jvmTarget.set(JvmTarget.JVM_17)
        languageVersion.set(KotlinVersion.KOTLIN_1_8)
        apiVersion.set(KotlinVersion.KOTLIN_1_8)
    }
}
