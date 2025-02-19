plugins {
    id("anthropic.kotlin")
    id("java")
    application
}

dependencies {
    implementation(project(":anthropic-java"))
}

tasks.withType<JavaCompile>().configureEach {
    // Allow using more modern APIs, like `List.of` and `Map.of`, in examples.
    options.release.set(11)
}

application {
    mainClass = "com.anthropic.example.MessagesExample"
}
