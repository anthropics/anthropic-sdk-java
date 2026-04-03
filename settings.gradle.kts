rootProject.name = "anthropic-java-root"

val excludedProjects = setOf("anthropic-java-proguard-test")

val projectNames = rootDir.listFiles()
    ?.asSequence()
    .orEmpty()
    .filter { file ->
        file.isDirectory &&
        (file.name.startsWith("anthropic-java") || file.name.startsWith("anthropic-kotlin")) &&
        file.name !in excludedProjects &&
        file.listFiles()?.asSequence().orEmpty().any { it.name == "build.gradle.kts" }
    }
    .map { it.name }
    .toList()
println("projects: $projectNames")
projectNames.forEach { include(it) }
