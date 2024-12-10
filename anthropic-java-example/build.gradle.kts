plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(":anthropic-java"))
    implementation(project(mapOf("path" to ":anthropic-java-core")))
}
