plugins {
    id("anthropic.kotlin")
    id("anthropic.publish")
}

// Resolves every documented module's jar and dependencies so Dokka can link the types referenced
// in their signatures. Reaching into the sibling projects' own `compileClasspath` configurations
// instead would break the configuration cache: resolving another project's configuration is
// unsafe, and the cache serializes the Dokka source-set classpaths at store time.
val dokkaAggregationClasspath by configurations.creating {
    isCanBeConsumed = false
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, Usage.JAVA_RUNTIME))
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category::class.java, Category.LIBRARY))
        attribute(
            LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE,
            objects.named(LibraryElements::class.java, LibraryElements.JAR),
        )
    }
}

dependencies {
    api(project(":anthropic-java-client-okhttp"))
}

// The aggregated javadoc covers exactly the modules this umbrella re-exports: derive them from
// this project's resolved runtime classpath so build-tooling and optional add-on modules can never
// leak in.
val documentedModules: Set<Project> =
    configurations.runtimeClasspath
        .get()
        .incoming
        .resolutionResult
        .allComponents
        .map { it.id }
        .filterIsInstance<ProjectComponentIdentifier>()
        .filter { it.projectName != project.name }
        .map { rootProject.project(it.projectPath) }
        .toSet()

dependencies {
    documentedModules.forEach { add(dokkaAggregationClasspath.name, project(it.path)) }
}

// This module's javadoc JAR must document the API of every module it
// re-exports, so add each module's main sources as extra Dokka source sets.
extensions.configure<org.jetbrains.dokka.gradle.DokkaExtension> {
    dokkaSourceSets {
        documentedModules
            .sortedBy { it.name }
            .forEach { subproject ->
                register(subproject.name) {
                    sourceRoots.from(
                        listOf("src/main/kotlin", "src/main/java")
                            .map(subproject::file)
                            .filter { it.exists() }
                    )
                    classpath.from(dokkaAggregationClasspath)
                    jdkVersion.set(8)
                }
            }
    }
}
