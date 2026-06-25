plugins {
    id("anthropic.java")
}

val libs = the<VersionCatalogsExtension>().named("libs")

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
