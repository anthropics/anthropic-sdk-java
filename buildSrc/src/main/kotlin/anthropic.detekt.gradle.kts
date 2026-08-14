import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    id("io.gitlab.arturbosch.detekt")
}

val libs = the<VersionCatalogsExtension>().named("libs")

configure<DetektExtension> {
    // Allowlist mode: run only rules enabled in `detekt.yml`. detekt's defaults conflict with the
    // SDK's Java-first builder style.
    buildUponDefaultConfig = false
    config.setFrom(rootProject.layout.projectDirectory.file("config/detekt/detekt.yml"))
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

// Hook the type-resolving variants into `lint` so `./scripts/lint` and CI run them alongside ktfmt.
tasks.named("lint") {
    dependsOn(tasks.named("detektMain"), tasks.named("detektTest"))
}
