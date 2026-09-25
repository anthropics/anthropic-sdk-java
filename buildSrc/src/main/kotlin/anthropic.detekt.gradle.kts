import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    id("io.gitlab.arturbosch.detekt")
}

val libs = the<VersionCatalogsExtension>().named("libs")

configure<DetektExtension> {
    // Allowlist mode: run only rules enabled in `detekt.yml`. detekt's defaults conflict with the
    // SDK's Java-first builder style.
    buildUponDefaultConfig = false
    config.setFrom(rootProject.layout.projectDirectory.file("config/detekt/detekt.yml"))
    parallel = true
}

dependencies {
    // Add-on rule sets are discovered via the `detektPlugins` configuration.
    "detektPlugins"(libs.findLibrary("detekt-rules-libraries").get())
    // Our Java-interop house rules; that module does not apply this convention.
    "detektPlugins"(project(":anthropic-java-detekt-rules"))
}

private val baselineDir = rootProject.layout.projectDirectory.dir("config/detekt")

// detekt registers `detekt<SourceSet>` and `detektBaseline<SourceSet>` per JVM source set. By
// default the baseline task writes `<ext.baseline>-<sourceSet>.xml` but the analysis task reads
// `<ext.baseline>`. Point both at the same per-module, per-source-set file so the regenerated
// baseline is the one consulted.
private fun baselineFileFor(taskName: String): File {
    val sourceSet = taskName.removePrefix("detekt").removePrefix("Baseline").ifEmpty { "Plain" }
    return baselineDir.file("baseline-${project.name}-${sourceSet.lowercase()}.xml").asFile
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = "1.8"
    // `baseline` is `@Optional @InputFile`: an absent provider is fine; a present-but-missing file
    // is not. Defer the check to task-input snapshotting so a same-invocation `detektBaseline*` can
    // write the file first.
    val baselineFile = baselineFileFor(name)
    baseline.fileProvider(provider { baselineFile.takeIf(File::exists) })
    reports {
        html.required.set(true)
        sarif.required.set(true)
    }
}

tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = "1.8"
    baseline.set(baselineFileFor(name))
}

// The plain `detekt` task (no type resolution) has no matching `detektBaseline` task and would
// resolve to a `-plain` baseline that never exists, failing on every grandfathered finding.
// `detektMain`/`detektTest` are the supported entry points; disable the plain one to avoid the
// red herring.
tasks.named("detekt") {
    enabled = false
}

// Hook the type-resolving variants, which check every file, into `lint` alongside ktfmt.
tasks.named("lint") {
    dependsOn(tasks.named("detektMain"), tasks.named("detektTest"))
}

private val toolchainJdkHome =
    extensions
        .getByType<JavaToolchainService>()
        .launcherFor(extensions.getByType<JavaPluginExtension>().toolchain)
        .map { it.metadata.installationPath }

listOf("main", "test").forEach { sourceSetName ->
    val sourceSet = the<SourceSetContainer>()[sourceSetName]
    // The files that `detektMain` and `detektTest` check.
    val kotlinFiles =
        the<KotlinJvmProjectExtension>().sourceSets[sourceSetName].kotlin.matching {
            include("**/*.kt", "**/*.kts")
        }
    val taskSuffix = sourceSetName.replaceFirstChar(Char::uppercase)
    val typeResolutionClasspath = files(sourceSet.output.classesDirs, sourceSet.compileClasspath)
    val configFiles = the<DetektExtension>().config
    val baselineFile = baselineFileFor("detekt$taskSuffix")
    val passedFile = layout.buildDirectory.file("detekt-changed/$sourceSetName-passed")
    val changedFiles =
        tasks.register<DetektChangedFiles>("detektChangedFiles$taskSuffix") {
            source.from(kotlinFiles)
            classpath.from(typeResolutionClasspath)
            settings.from(
                configFiles,
                baselineFile,
                configurations["detekt"],
                configurations["detektPlugins"],
            )
            passed.set(passedFile)
            fileList.set(layout.buildDirectory.file("detekt-changed/$sourceSetName.txt"))
        }
    tasks.register<Detekt>("detektChanged$taskSuffix") {
        description =
            "Runs detekt on the $sourceSetName Kotlin files changed since this task last passed."
        val fileList = changedFiles.flatMap { it.fileList }.map { it.asFile }
        // There is no list in a module with no Kotlin files.
        setSource(files(fileList.map { if (it.exists()) it.readLines() else emptyList() }))
        // `source` is compared by file name and content. With the list as an input too, a result
        // is taken from the build cache only for the same paths.
        inputs.files(fileList).withPropertyName("fileList")
        // The plugin sets these only on tasks it registers. Keep them as on `detekt$taskSuffix`.
        classpath.setFrom(typeResolutionClasspath)
        jdkHome.set(toolchainJdkHome)
        config.setFrom(configFiles)
        parallel = project.the<DetektExtension>().parallel
        baseline.fileProvider(provider { baselineFile.takeIf(File::exists) })
        // `detektChangedMain` and `detektChangedTest` would write the same report files.
        reports { listOf(xml, html, txt, sarif, md).forEach { it.required.set(false) } }
        // See `DetektChangedFiles.passed`.
        outputs.file(passedFile).withPropertyName("passed")
        doLast { passedFile.get().asFile.writeText("") }
    }
}
