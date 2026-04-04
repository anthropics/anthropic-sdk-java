plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    application
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.anthropic.apigen.MainKt")
}

dependencies {
    // Spec parsers (stable libs)
    implementation("io.swagger.parser.v3:swagger-parser:2.1.39")

    // Code emission
    implementation("com.squareup:kotlinpoet:2.2.0")

    // Serialization (for config files, spec extensions)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")

    // CLI

    // File I/O
    implementation("com.squareup.okio:okio:3.17.0")

    // Test
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
}

tasks.test {
    useJUnitPlatform()
}
