plugins {
    id("anthropic.java")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":anthropic-java"))
    implementation(project(":anthropic-java-bedrock"))
    implementation(project(":anthropic-java-vertex"))
}

tasks.withType<JavaCompile>().configureEach {
    // Allow using more modern APIs, like `List.of` and `Map.of`, in examples.
    options.release.set(11)
}

application {
    // Use `./gradlew :anthropic-java-example:run` to run `Main`
    // Use `./gradlew :anthropic-java-example:run -Dexample=Something` to run `SomethingExample`
    mainClass = "com.anthropic.example.${
        if (project.hasProperty("example"))
            "${project.property("example")}Example"
        else
            "Main"
    }"
}
