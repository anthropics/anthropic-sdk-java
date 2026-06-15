plugins {
    alias(libs.plugins.dokka) apply false
    alias(libs.plugins.dokka.javadoc) apply false
}

allprojects {
    group = "com.anthropic"
    version = "2.40.1" // x-release-please-version
}

subprojects {
    // These are populated with dependencies by `buildSrc` scripts.
    tasks.register("format") {
        group = "Verification"
        description = "Formats all source files."
    }
    tasks.register("lint") {
        group = "Verification"
        description = "Verifies all source files are formatted."
    }
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "org.jetbrains.dokka-javadoc")

    extensions.configure<org.jetbrains.dokka.gradle.DokkaExtension> {
        // Dokka generates in an isolated worker process whose default heap cap is a quarter of
        // physical memory. Cap it explicitly so concurrent generations plus the Gradle and Kotlin
        // daemons stay within the CI runner's 16 GB (see `org.gradle.jvmargs` in
        // `gradle.properties`).
        dokkaGeneratorIsolation.set(ProcessIsolation { maxHeapSize.set("3g") })
    }
}
