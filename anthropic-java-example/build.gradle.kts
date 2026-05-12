plugins {
    id("anthropic.java")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":anthropic-java"))
    implementation(project(":anthropic-java-aws"))
    implementation(project(":anthropic-java-bedrock"))
    implementation(project(":anthropic-java-foundry"))
    implementation(project(":anthropic-java-vertex"))
    // Microsoft Entra ID library for Foundry examples.
    implementation("com.azure:azure-identity:1.18.1")
    // MCP module for BetaMcpToolRunnerExample (requires JDK 17+ at runtime).
    implementation(project(":anthropic-java-mcp"))
}

tasks.withType<JavaCompile>().configureEach {
    // Allow using more modern APIs in examples. Uses 17 (rather than 11) because the MCP SDK
    // uses Java Records which require Java 16+.
    options.release.set(17)
}

application {
    // Use `./gradlew :anthropic-java-example:run` to run `Main`
    // Use `./gradlew :anthropic-java-example:run -Pexample=Something` to run `SomethingExample`
    mainClass = "com.anthropic.example.${
        if (project.hasProperty("example"))
            "${project.property("example")}Example"
        else
            "Main"
    }"
}
