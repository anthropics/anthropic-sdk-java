plugins {
    id("anthropic.kotlin")
    id("anthropic.publish")
}

dependencies {
    api(project(":anthropic-java-client-okhttp"))
}

// This module's javadoc JAR must document the API of every module it
// re-exports, so add each module's main sources to this module's `dokkaJavadoc`
// task as extra source sets.
tasks.named<org.jetbrains.dokka.gradle.DokkaTask>("dokkaJavadoc").configure {
    // Run after every other module's `dokkaJavadoc`: this task's documentation generation is by
    // far the largest, and Dokka generates in-process, so running it concurrently with another
    // large generation can exhaust the Gradle daemon's heap.
    rootProject.subprojects
        .filter { it.name != project.name }
        .forEach { subproject -> mustRunAfter(subproject.tasks.matching { it.name == "dokkaJavadoc" }) }

    dokkaSourceSets {
        rootProject.subprojects
            .filter { it.file("src/main/kotlin").exists() }
            .sortedBy { it.name }
            .forEach { subproject ->
                register(subproject.name) {
                    sourceRoots.from(
                        listOf("src/main/kotlin", "src/main/java")
                            .map(subproject::file)
                            .filter { it.exists() }
                    )
                    // Resolve lazily: sibling projects may not be configured
                    // yet when this runs.
                    classpath.from(
                        project.provider {
                            subproject.configurations.getByName("compileClasspath")
                        }
                    )
                    jdkVersion.set(8)
                }
            }
    }
}
