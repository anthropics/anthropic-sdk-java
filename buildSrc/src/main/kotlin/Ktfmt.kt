import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.file.FileTree
import org.gradle.api.plugins.JvmEcosystemPlugin
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.PathSensitivity
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.the
import org.gradle.process.CommandLineArgumentProvider

/**
 * Registers `formatKotlin` and `lintKotlin`, which run ktfmt over [kotlinFiles], and adds them to
 * the project's `format` and `lint` lifecycle tasks. Modules call this through the
 * `anthropic.ktfmt` convention for their own sources and the root project calls it for the build
 * logic in `buildSrc`, so both are held to the same ktfmt version and style.
 */
fun Project.configureKtfmt(kotlinFiles: FileTree) {
    // The JVM attribute schema is what lets a bare `ktfmt` configuration pick the library's runtime
    // variant; modules get it from `java-library`, the root project from here.
    pluginManager.apply(JvmEcosystemPlugin::class.java)

    val libs = the<VersionCatalogsExtension>().named("libs")
    val ktfmt = configurations.create("ktfmt")
    dependencies.add(ktfmt.name, libs.findLibrary("ktfmt").get())

    registerKtfmt("format", "Formats all Kotlin source files.", ktfmt, kotlinFiles)
    registerKtfmt("lint", "Verifies all Kotlin source files are formatted.", ktfmt, kotlinFiles)
}

private fun Project.registerKtfmt(
    name: String,
    description: String,
    ktfmt: Configuration,
    kotlinFiles: FileTree,
) {
    val kotlinName = "${name}Kotlin"
    tasks.register<JavaExec>(kotlinName) {
        group = "Verification"
        this.description = description

        classpath = ktfmt
        mainClass.set("com.facebook.ktfmt.cli.Main")

        // Use paths relative to the current project.
        val argumentFile = layout.buildDirectory.file("ktfmt-$name-args.txt").get().asFile
        val lastRunTimeFile = layout.buildDirectory.file("ktfmt-$name-last-run.txt").get().asFile

        // Use the stamp file's own mtime (0 when absent) rather than a wall-clock value written
        // into it: a build-cache hit restores the stamp with a fresh local mtime, so the
        // changed-file filter below stays consistent with this machine's source mtimes instead
        // of comparing against the cache producer's clock.
        val lastRunTime = lastRunTimeFile.lastModified()

        // Determine if any files need to be formatted or linted and continue only if there is at
        // least one file (otherwise ktfmt will fail).
        onlyIf { kotlinFiles.any { it.lastModified() > lastRunTime } }

        inputs.files(kotlinFiles).withPathSensitivity(PathSensitivity.RELATIVE)
        // Declaring the stamp file as an output lets Gradle build-cache the lint result by source
        // content, so unchanged sources resolve FROM-CACHE on CI where `build/` is not preserved.
        // `format` mutates sources in place, so only `lint` is safe to cache, and it must rerun
        // even when its inputs match the last run's (a file regenerated back to the same
        // unformatted content would otherwise be reported up to date and left unformatted).
        outputs.file(lastRunTimeFile)
        outputs.cacheIf { name == "lint" }
        outputs.upToDateWhen { name == "lint" }

        doFirst {
            // Create the argument file and set the preferred formatting style.
            argumentFile.parentFile.mkdirs()
            argumentFile.writeText("--kotlinlang-style\n")

            if (name == "lint") {
                // For lint, do a dry run, so no files are modified. Set the exit code to 1 (instead
                // of the default 0) if any files need to be formatted, indicating that linting has
                // failed.
                argumentFile.appendText("--dry-run\n")
                argumentFile.appendText("--set-exit-if-changed\n")
            }

            // Write the modified files to the argument file.
            kotlinFiles
                .filter { it.lastModified() > lastRunTime }
                .forEach { argumentFile.appendText("${it.absolutePath}\n") }
        }

        doLast {
            // Touch the stamp so its mtime records this run; content is unused.
            lastRunTimeFile.writeText("")
        }

        // Pass the argument file via an argument provider rather than `args`: `args` is an
        // `@Input` on `JavaExec`, so the absolute path it carries would otherwise become part of
        // the build-cache key and prevent hits across machines.
        argumentProviders.add(
            CommandLineArgumentProvider { listOf("@${argumentFile.absolutePath}") }
        )
    }

    tasks.named(name) { dependsOn(kotlinName) }
}
