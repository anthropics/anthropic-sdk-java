plugins {
    id("org.jetbrains.dokka") version "2.0.0"
}

repositories {
    mavenCentral()
}

allprojects {
    group = "com.anthropic"
    version = "0.1.0-alpha.9" // x-release-please-version
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
}
