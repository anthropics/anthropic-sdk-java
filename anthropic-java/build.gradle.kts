plugins {
    id("anthropic.kotlin")
    id("anthropic.publish")
}

// Resolves every documented module's dependencies so Dokka can link the types referenced
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

// The documented modules' third-party dependencies only. Dokka reads the modules' own types from
// their sources (see `dependentSourceSets` below), so it can run while they compile.
val dokkaExternalClasspath: FileCollection =
    dokkaAggregationClasspath.incoming
        .artifactView { componentFilter { it !is ProjectComponentIdentifier } }
        .files

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
                    classpath.from(dokkaExternalClasspath)
                    jdkVersion.set(8)
                }
            }
        // This module's own `main` source set has nothing to document, and its compile classpath
        // would make Dokka wait for the documented modules' classes.
        named("main") { suppress.set(true) }
        // Every other documented module depends on core alone, so resolve its references to core's
        // types against core's sources. A module that used another documented module's types would
        // need that module's source set here too, or Dokka could not resolve those types.
        val core = named("anthropic-java-core")
        documentedModules
            .filter { it.name != "anthropic-java-core" }
            .forEach { subproject ->
                named(subproject.name) {
                    dependentSourceSets.addLater(core.flatMap { it.sourceSetId })
                }
            }
    }
}
