rootProject.name = "anthropic-java-root"

// Declare dependency repositories once for every project; per-project `repositories` blocks are
// rejected so they can't silently diverge from this list.
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        mavenCentral()
    }
}

rootDir.listFiles()
    ?.asSequence()
    .orEmpty()
    .filter { file ->
        file.isDirectory &&
        file.name.startsWith("anthropic-java") &&
        file.listFiles()?.asSequence().orEmpty().any { it.name == "build.gradle.kts" }
    }
    .map { it.name }
    .sorted()
    .forEach { include(it) }
