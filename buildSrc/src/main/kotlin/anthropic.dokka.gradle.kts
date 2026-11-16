plugins {
    id("org.jetbrains.dokka")
    id("org.jetbrains.dokka-javadoc")
}

extensions.configure<org.jetbrains.dokka.gradle.DokkaExtension> {
    // Dokka generates modules' docs in separate worker processes, several at once, each with this
    // heap cap. If the cap is too low for the largest module, the task fails at an arbitrary symbol
    // with "Error in translating of symbol", and the message says nothing about memory.
    dokkaGeneratorIsolation.set(ProcessIsolation { maxHeapSize.set("6g") })

    // Dokka links a JDK or Kotlin type only if its package is in a package list. By default it
    // downloads the lists from the docs sites. If that fails, it leaves the links out and passes.
    val packageLists = rootProject.layout.projectDirectory.dir("config/dokka")
    dokkaSourceSets.configureEach {
        val jdkPackageList = jdkVersion.map { version ->
            packageLists.file(if (version == 8) "jdk-8-packages" else "jdk-packages").asFile.toURI()
        }
        externalDocumentationLinks.named("jdk") { packageListUrl.set(jdkPackageList) }
        externalDocumentationLinks.named("kotlinStdlib") {
            packageListUrl.set(packageLists.file("kotlin-packages").asFile.toURI())
        }
    }
}
