import org.jetbrains.kotlin.gradle.dsl.JvmDefaultMode
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    id("anthropic.java")
    kotlin("jvm")
}

// Precompiled script plugins can't use the generated type-safe `libs` accessors; look the catalog
// up through its extension instead.
val libs = the<VersionCatalogsExtension>().named("libs")

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }

    compilerOptions {
        freeCompilerArgs = listOf(
            "-Xjdk-release=1.8",
            // Suppress all warnings: generated code may reference and test deprecated members and
            // trigger style lints that we can't act on.
            "-nowarn",
            // `-nowarn` doesn't cover the "language version 2.0 is deprecated" notice. That comes
            // from the version-validation pass. We pin 2.0 deliberately (see `languageVersion`).
            "-Xsuppress-version-warnings",
        )
        // Emit default interface methods for the JVM.
        jvmDefault.set(JvmDefaultMode.NO_COMPATIBILITY)
        // Emit Java 8 bytecode: the SDK supports consumers running Java 8+.
        jvmTarget.set(JvmTarget.JVM_1_8)
        // Compile with Kotlin 2.0 semantics, which stamps classes with `@Metadata(mv=[2,0,0])`.
        // Kotlin special-cases 2.0.0 metadata as the K2 transition bridge (the module file is
        // written as version 1.9.9999), so any consumer compiler >= 1.8.20 can read our classes.
        languageVersion.set(KotlinVersion.KOTLIN_2_0)
        // Only allow calling stdlib APIs that exist in 1.9: consumers' dependency management often
        // forces an older stdlib onto the runtime classpath than the one we compile against (e.g.
        // Spring Boot 3.x's BOM pins kotlin-stdlib 1.9.25). Calling a 2.0-only API would compile
        // here but throw `NoSuchMethodError` at runtime for those users; this makes the compiler
        // reject such calls at build time.
        apiVersion.set(KotlinVersion.KOTLIN_1_9)
        // Compile against and publish a POM dependency on kotlin-stdlib 1.9.0 (instead of
        // defaulting to the compiler's own version). Pure-Java Maven consumers inherit exactly
        // this version (nearest-wins), so it sets the stdlib floor our jar must run on.
        coreLibrariesVersion = libs.findVersion("kotlin-compat-stdlib").get().requiredVersion
    }
}

tasks.withType<Test>().configureEach {
    systemProperty("junit.jupiter.execution.parallel.enabled", true)
    systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")

    // `SKIP_MOCK_TESTS` affects which tests run so it must be added as input for proper cache invalidation.
    inputs.property("skipMockTests", System.getenv("SKIP_MOCK_TESTS")).optional(true)
}

val ktfmt by configurations.creating
dependencies {
    ktfmt(libs.findLibrary("ktfmt").get())
}

fun registerKtfmt(
    name: String,
    description: String,
) {
    val kotlinName = "${name}Kotlin"
    tasks.register<JavaExec>(kotlinName) {
        group = "Verification"
        this.description = description

        classpath = ktfmt
        mainClass = "com.facebook.ktfmt.cli.Main"

        // Use paths relative to the current module.
        val argumentFile = project.layout.buildDirectory.file("ktfmt-$name-args.txt").get().asFile
        val lastRunTimeFile =
            project.layout.buildDirectory.file("ktfmt-$name-last-run.txt").get().asFile

        // Use the stamp file's own mtime (0 when absent) rather than a wall-clock value written
        // into it: a build-cache hit restores the stamp with a fresh local mtime, so the
        // changed-file filter below stays consistent with this machine's source mtimes instead
        // of comparing against the cache producer's clock.
        val lastRunTime = lastRunTimeFile.lastModified()

        // Use a `fileTree` relative to the module's source directory.
        val kotlinFiles = project.fileTree("src") { include("**/*.kt") }

        // Determine if any files need to be formatted or linted and continue only if there is at least
        // one file (otherwise Ktfmt will fail).
        onlyIf { kotlinFiles.any { it.lastModified() > lastRunTime } }

        inputs.files(kotlinFiles).withPathSensitivity(PathSensitivity.RELATIVE)
        // Declaring the stamp file as an output lets Gradle build-cache the lint result by source
        // content, so unchanged sources resolve FROM-CACHE on CI where `build/` is not preserved.
        // `format` mutates sources in place, so only `lint` is safe to cache.
        outputs.file(lastRunTimeFile)
        outputs.cacheIf { name == "lint" }

        doFirst {
            // Create the argument file and set the preferred formatting style.
            argumentFile.parentFile.mkdirs()
            argumentFile.writeText("--kotlinlang-style\n")

            if (name == "lint") {
                // For lint, do a dry run, so no files are modified. Set the exit code to 1 (instead of
                // the default 0) if any files need to be formatted, indicating that linting has failed.
                argumentFile.appendText("--dry-run\n")
                argumentFile.appendText("--set-exit-if-changed\n")
            }

            // Write the modified files to the argument file.
            kotlinFiles.filter { it.lastModified() > lastRunTime }
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
        dependsOn(tasks.named(kotlinName))
    }
}

registerKtfmt(name = "format", description = "Formats all Kotlin source files.")
registerKtfmt(name = "lint", description = "Verifies all Kotlin source files are formatted.")
