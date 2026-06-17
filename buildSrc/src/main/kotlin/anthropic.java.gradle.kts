import org.gradle.api.artifacts.dsl.LockMode
import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    `java-library`
}

// Pin every resolved configuration to the versions recorded in this module's `gradle.lockfile`.
// STRICT fails the build if a locked configuration is resolved without lock state, so a missing
// lockfile can't silently fall back to dynamic resolution. Regenerate with `./scripts/lock`.
dependencyLocking {
    lockAllConfigurations()
    lockMode.set(LockMode.STRICT)
}

// Resolves every resolvable configuration so `--write-locks` captures all of them in one pass.
// Without this, lock state would only be written for whatever the requested task graph happened
// to resolve.
tasks.register("resolveAndLockAll") {
    group = "Help"
    description = "Resolves all configurations to write dependency lock state."
    notCompatibleWithConfigurationCache("Resolves configurations at execution time")
    val startParameter = project.gradle.startParameter
    doFirst {
        require(startParameter.isWriteDependencyLocks) {
            "Run with --write-locks to update lock state"
        }
    }
    doLast {
        configurations.filter { it.isCanBeResolved }.forEach { it.resolve() }
    }
}

// Precompiled script plugins can't use the generated type-safe `libs` accessors; look the catalog
// up through its extension instead.
val libs = the<VersionCatalogsExtension>().named("libs")
fun lib(alias: String) = libs.findLibrary(alias).get()

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }

    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

// Lifecycle tasks that aggregate the per-language format/lint tasks registered below (and the
// Kotlin ones from `anthropic.kotlin`). Registered here because every module applies this plugin
// directly or transitively through `anthropic.kotlin`.
tasks.register("format") {
    group = "Verification"
    description = "Formats all source files."
}
tasks.register("lint") {
    group = "Verification"
    description = "Verifies all source files are formatted."
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-Werror")
    options.release.set(8)
}

tasks.named<Jar>("jar") {
    manifest {
        attributes(mapOf(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version
        ))
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()

    // Run tests in parallel to some degree.
    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)

    // Mockito's ByteBuddy agent emits a JVM warning when loaded dynamically. Tests that capture
    // stderr (e.g. LoggingHttpClientTest) see this warning interleaved with their expected output
    // when other concurrent tests trigger the agent load. Pre-authorizing dynamic agent loading
    // suppresses the warning at the JVM level.
    jvmArgs("-XX:+EnableDynamicAgentLoading")

    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
    }
}

dependencies {
    // SLF4J lazily initializes on the first `LoggerFactory.getLogger` call in
    // the JVM and, without a provider, prints a warning to stderr. That warning
    // corrupts tests that capture and assert on exact stderr contents when
    // another test races the initialization. Binding a no-op provider keeps
    // SLF4J silent.
    testRuntimeOnly(lib("slf4j-nop"))

    testRuntimeOnly(lib("junit-platform-launcher"))
}

val palantir by configurations.creating
dependencies {
    palantir(lib("palantir-java-format"))
}

fun registerPalantir(
    name: String,
    description: String,
) {
    val javaName = "${name}Java"
    tasks.register<JavaExec>(javaName) {
        group = "Verification"
        this.description = description

        classpath = palantir
        mainClass = "com.palantir.javaformat.java.Main"

        // Avoid an `IllegalAccessError` on Java 9+.
        jvmArgs(
            "--add-exports", "jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED",
            "--add-exports", "jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED",
            "--add-exports", "jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED",
            "--add-exports", "jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED",
            "--add-exports", "jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED",
        )

        // Use paths relative to the current module.
        val argumentFile =
            project.layout.buildDirectory.file("palantir-$name-args.txt").get().asFile
        val lastRunTimeFile =
            project.layout.buildDirectory.file("palantir-$name-last-run.txt").get().asFile

        // Use the stamp file's own mtime (0 when absent) rather than a wall-clock value written
        // into it: a build-cache hit restores the stamp with a fresh local mtime, so the
        // changed-file filter below stays consistent with this machine's source mtimes instead
        // of comparing against the cache producer's clock.
        val lastRunTime = lastRunTimeFile.lastModified()

        // Use a `fileTree` relative to the module's source directory.
        val javaFiles = project.fileTree("src") { include("**/*.java") }

        // Determine if any files need to be formatted or linted and continue only if there is at least
        // one file.
        onlyIf { javaFiles.any { it.lastModified() > lastRunTime } }

        inputs.files(javaFiles).withPathSensitivity(PathSensitivity.RELATIVE)
        // Declaring the stamp file as an output lets Gradle build-cache the lint result by source
        // content, so unchanged sources resolve FROM-CACHE on CI where `build/` is not preserved.
        // `format` mutates sources in place, so only `lint` is safe to cache.
        outputs.file(lastRunTimeFile)
        outputs.cacheIf { name == "lint" }

        doFirst {
            // Create the argument file and set the preferred formatting style.
            argumentFile.parentFile.mkdirs()
            argumentFile.writeText("--palantir\n")

            if (name == "lint") {
                // For lint, do a dry run, so no files are modified. Set the exit code to 1 (instead of
                // the default 0) if any files need to be formatted, indicating that linting has failed.
                argumentFile.appendText("--dry-run\n")
                argumentFile.appendText("--set-exit-if-changed\n")
            } else {
                // `--dry-run` and `--replace` (for in-place formatting) are mutually exclusive.
                argumentFile.appendText("--replace\n")
            }

            // Write the modified files to the argument file.
            javaFiles.filter { it.lastModified() > lastRunTime }
                .forEach { argumentFile.appendText("${it.absolutePath}\n") }
        }

        doLast {
            // Touch the stamp so its mtime records this run; content is unused.
            lastRunTimeFile.writeText("")
        }

        // Pass the argument file via an argument provider rather than `args`: `args` is an
        // `@Input` on `JavaExec`, so the absolute path it carries would otherwise become part of
        // the build-cache key and prevent hits across machines.
        argumentProviders.add(CommandLineArgumentProvider { listOf("@${argumentFile.absolutePath}") })
    }

    tasks.named(name) {
        dependsOn(tasks.named(javaName))
    }
}

registerPalantir(name = "format", description = "Formats all Java source files.")
registerPalantir(name = "lint", description = "Verifies all Java source files are formatted.")
