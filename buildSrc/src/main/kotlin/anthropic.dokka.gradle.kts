plugins {
    id("org.jetbrains.dokka")
    id("org.jetbrains.dokka-javadoc")
}

extensions.configure<org.jetbrains.dokka.gradle.DokkaExtension> {
    // Dokka generates modules' docs in separate worker processes, several at once, each with this
    // heap cap. If the cap is too low for the largest module, the task fails at an arbitrary symbol
    // with "Error in translating of symbol", and the message says nothing about memory.
    dokkaGeneratorIsolation.set(ProcessIsolation { maxHeapSize.set("6g") })
}
