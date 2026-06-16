plugins {
    id("org.jetbrains.dokka")
    id("org.jetbrains.dokka-javadoc")
}

extensions.configure<org.jetbrains.dokka.gradle.DokkaExtension> {
    // Dokka generates in an isolated worker process whose default heap cap is a quarter of physical
    // memory. Cap it explicitly so concurrent generations plus the Gradle and Kotlin daemons stay
    // within the CI runner's 16 GB (see `org.gradle.jvmargs` in `gradle.properties`).
    dokkaGeneratorIsolation.set(ProcessIsolation { maxHeapSize.set("3g") })
}
