plugins {
    id("anthropic.kotlin")
    id("anthropic.publish")
}

// This module has Java test sources, but no Java main sources. Compiling them to Java 8 makes javac
// emit an "obsolete source/target 8" warning, which the project-wide `-Werror` would turn into an
// error. Suppress only that warning here.
tasks.named<JavaCompile>("compileTestJava") { options.compilerArgs.add("-Xlint:-options") }

// Runtime classpath for `testJacksonPublished`: the same dependencies as `testRuntimeClasspath`,
// but exempt from the 2.14.0 force below, so Jackson resolves to the version declared in
// `dependencies {}` (the one we publish in the POM).
val jacksonPublishedRuntime: Configuration by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
    extendsFrom(configurations.testRuntimeClasspath.get())
    // `extendsFrom` inherits dependencies but not attributes; without a runtime usage attribute,
    // variant-aware resolution can't pick a variant for project and multi-variant dependencies.
    attributes { attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME)) }
}

configurations.matching { it.name != jacksonPublishedRuntime.name }.configureEach {
    resolutionStrategy {
        // Compile and test against a lower Jackson version to ensure we're compatible with it. Note that
        // we generally support 2.13.4, but test against 2.14.0 because 2.13.4 has some annoying (but
        // niche) bugs (users should upgrade if they encounter them). We publish with a higher version
        // (see below) to ensure users depend on a secure version by default.
        val jacksonCompat = libs.versions.jackson.compat.get()
        force("com.fasterxml.jackson.core:jackson-core:$jacksonCompat")
        force("com.fasterxml.jackson.core:jackson-databind:$jacksonCompat")
        force("com.fasterxml.jackson.core:jackson-annotations:$jacksonCompat")
        force("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:$jacksonCompat")
        force("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonCompat")
        force("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonCompat")
    }
}

// Re-runs the already-compiled test classes against the published Jackson version. The main
// `test` task covers the supported floor; this covers the version users get by default. Service
// tests are excluded: they exercise HTTP plumbing (mock server, WireMock) rather than Jackson edge
// cases, and skipping them keeps this pass cheap and runnable without a mock server.
val testJacksonPublished by tasks.registering(Test::class) {
    group = "verification"
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().output + sourceSets.main.get().output + jacksonPublishedRuntime
    filter { excludeTestsMatching("com.anthropic.services.*") }
    // Asserted by `JacksonVersionTest` so a resolution-strategy regression can't pass silently.
    systemProperty("expected.jackson.version", libs.versions.jackson.asProvider().get())
}

tasks.test {
    systemProperty("expected.jackson.version", libs.versions.jackson.compat.get())
}

// Wired into `check` (rather than `test.dependsOn`) so Gradle can schedule it concurrently with
// `test` instead of forcing the two passes to run sequentially.
tasks.check { dependsOn(testJacksonPublished) }

dependencies {
    api(libs.jackson.core)
    api(libs.jackson.databind)
    api(libs.errorprone.annotations)
    api(libs.standardwebhooks)
    // No references in this module's main sources, but NOT unused: consumers annotate their
    // structured-output classes with Swagger annotations (e.g. `@Schema`), which
    // `jsonschema-module-swagger2` below reads when generating tool JSON schemas.
    api(libs.swagger.annotations)

    implementation(libs.jackson.annotations)
    implementation(libs.jackson.datatype.jdk8)
    implementation(libs.jackson.datatype.jsr310)
    implementation(libs.jackson.module.kotlin)
    implementation(kotlin("reflect"))
    implementation(libs.jsonschema.generator)
    implementation(libs.jsonschema.module.jackson)
    implementation(libs.jsonschema.module.swagger2)

    testImplementation(kotlin("test"))
    testImplementation(project(":anthropic-java-client-okhttp"))
    testImplementation(libs.checker.qual)
    testImplementation(libs.jsr305)
    testImplementation(libs.wiremock)
    testImplementation(libs.assertj)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.junit.pioneer)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.junit.jupiter)
    testImplementation(libs.mockito.kotlin)
}
