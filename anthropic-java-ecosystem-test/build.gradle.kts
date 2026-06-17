import org.gradle.process.CommandLineArgumentProvider

plugins {
    id("anthropic.kotlin")
    alias(libs.plugins.shadow)
}

// This module has a Java test source (the Java 8 consumer) but no Java main source. On JDK 21,
// javac flags targeting 8 as obsolete; the project-wide `-Werror` (from `anthropic.java`) would
// turn that into an error. Suppress only that warning, mirroring `anthropic-java-core`.
tasks.named<JavaCompile>("compileTestJava") { options.compilerArgs.add("-Xlint:-options") }

// R8 ships no Gradle plugin, so resolve its jar into a dedicated configuration for the `JavaExec`
// tasks below.
val r8: Configuration by configurations.creating

// The Kotlin 1.8.20 compiler, resolved as a runnable classpath (mirrors how `r8` is invoked).
val kotlinCompatCompiler: Configuration by configurations.creating

// Compile/run classpath shown to the old (1.8.20) compiler and to its executed output: the SDK
// plus everything the shared Kotlin usage source imports. kotlin-stdlib/kotlin-reflect are pinned
// to the SDK's documented floor (1.9.0); the `force(...)` mirrors the Jackson pin in
// `anthropic-java-core` and stops a transitive bump from dragging in a newer stdlib whose metadata
// the 1.8.20 compiler couldn't read.
val kotlinCompatClasspath: Configuration by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
    resolutionStrategy {
        val stdlib = libs.versions.kotlin.compat.stdlib.get()
        force("org.jetbrains.kotlin:kotlin-stdlib:$stdlib")
        force("org.jetbrains.kotlin:kotlin-reflect:$stdlib")
    }
}

dependencies {
    r8(libs.r8)
    kotlinCompatCompiler(libs.kotlin.compiler.embeddable.compat)

    // Everything the shared Kotlin usage source imports, applied to BOTH the build's test
    // classpath (Kotlin 2.x compile, ProGuard/R8/Java8 runs) and the 1.8.20-compiler classpath, so
    // a new import only needs adding once.
    for (cfg in listOf("testImplementation", kotlinCompatClasspath.name)) {
        add(cfg, project(":anthropic-java"))
        add(cfg, libs.junit.jupiter.api)
        add(cfg, libs.assertj)
        add(cfg, libs.jackson.module.kotlin.compat)
    }

    // Compat-only: pin to the SDK's documented stdlib/reflect floor (the `force()` above keeps
    // transitives from bumping it).
    kotlinCompatClasspath(libs.kotlin.stdlib.compat)
    kotlinCompatClasspath(libs.kotlin.reflect.compat)
}

// JDK 8 launcher spec, shared by every task that must run on a real Java 8 runtime. Creating the
// provider resolves nothing; resolution happens in `useJdk8()` only when such a task is actually in
// the requested build.
val jdk8 = javaToolchains.launcherFor { languageVersion.set(JavaLanguageVersion.of(8)) }

// Point this task at a JDK 8 toolchain, or decide what to do when none is installed. The toolchain
// is resolved here (as the task is realized for the build) rather than by binding the launcher
// provider eagerly, so the decision is made before the configuration cache serializes the task —
// otherwise a missing JDK 8 fails at store time even when we'd rather skip:
//   - found        -> run on JDK 8
//   - missing + CI -> fail hard, so an ecosystem check can never silently pass in CI
//   - missing      -> skip, so `./gradlew test` still works locally without a JDK 8 installed
fun JavaExec.useJdk8() {
    val onCi =
        project.providers.environmentVariable("CI").getOrNull().toBoolean() ||
            project.providers.environmentVariable("GITHUB_ACTIONS").getOrNull().toBoolean()

    try {
        javaLauncher.set(jdk8.get())
    } catch (e: Exception) {
        if (onCi) {
            throw GradleException(
                "$path requires a JDK 8 toolchain, but none was found. CI installs Temurin 8 via " +
                    "actions/setup-java; refusing to skip so this ecosystem check can't silently pass.",
                e
            )
        }
        enabled = false
        // Toolchain detection is not a configuration-cache input (gradle/gradle#36734); the
        // `--no-configuration-cache` hint ensures a fresh probe re-enables this task once a JDK 8
        // is in place.
        logger.lifecycle(
            "$path skipped: no JDK 8 toolchain found (${e.message}). Install a JDK 8 (e.g. " +
                "Temurin 8) and rerun once with --no-configuration-cache to pick it up."
        )
    }
}

tasks.shadowJar {
    from(sourceSets.test.get().output)
    configurations = listOf(project.configurations.testRuntimeClasspath.get())
}

// ProGuard + R8 shrinking compatibility

val shadowJarFile = tasks.shadowJar.flatMap { it.archiveFile }
val proguardJarFile = layout.buildDirectory.file("libs/${project.name}-${project.version}-proguard.jar")
val proguardJar by tasks.registering(proguard.gradle.ProGuardTask::class) {
    group = "verification"
    dependsOn(tasks.shadowJar)

    // `ProGuardTask` takes plain paths (and `Task` objects can't be serialized to the configuration
    // cache anyway), so resolve the file providers to paths here.
    injars(shadowJarFile.get().asFile.absolutePath)
    outjars(proguardJarFile.get().asFile.absolutePath)
    printmapping(layout.buildDirectory.file("proguard-mapping.txt").get().asFile.absolutePath)

    val javaHome = System.getProperty("java.home")
    if (System.getProperty("java.version").startsWith("1.")) {
        // Before Java 9, the runtime classes were packaged in a single jar file.
        libraryjars("$javaHome/lib/rt.jar")
    } else {
        // As of Java 9, the runtime classes are packaged in modular jmod files.
        libraryjars(
            // Filters must be specified first, as a map.
            mapOf("jarfilter" to "!**.jar", "filter" to "!module-info.class"),
            "$javaHome/jmods/java.base.jmod"
        )
    }

    configuration("./test.pro")
    configuration("../anthropic-java-core/src/main/resources/META-INF/proguard/anthropic-java-core.pro")
}

val testProGuard by tasks.registering(JavaExec::class) {
    group = "verification"
    dependsOn(proguardJar)

    mainClass.set("com.anthropic.ecosystem.EcosystemCompatibilityTest")
    classpath = files(proguardJarFile)

    // This is a verification task with no file outputs, so rerun it only when
    // the JAR changes.
    outputs.upToDateWhen { true }
}

val r8JarFile = layout.buildDirectory.file("libs/${project.name}-${project.version}-r8.jar")
val r8Jar by tasks.registering(JavaExec::class) {
    group = "verification"
    dependsOn(tasks.shadowJar)

    mainClass.set("com.android.tools.r8.R8")
    classpath = r8

    val proguardConfigs = listOf(
        "./test.pro",
        "../anthropic-java-core/src/main/resources/META-INF/proguard/anthropic-java-core.pro",
    )

    args = listOf(
        "--release",
        "--classfile",
        "--output", r8JarFile.get().asFile.absolutePath,
        "--lib", System.getProperty("java.home"),
        "--pg-conf", proguardConfigs[0],
        "--pg-conf", proguardConfigs[1],
        "--pg-map-output", layout.buildDirectory.file("r8-mapping.txt").get().asFile.absolutePath,
        shadowJarFile.get().asFile.absolutePath,
    )

    // `args` is an `@Input` on `JavaExec` (so the paths above already affect
    // the cache key), but the file *contents* behind those paths are not
    // tracked. Declare them explicitly so a changed shadow jar or rule file
    // invalidates the task. The mapping file is a debug side-effect nothing
    // consumes, so it is intentionally not a declared output (it would add
    // ~29 MB to every cache entry).
    inputs.files(shadowJarFile)
    inputs.files(proguardConfigs)
    outputs.file(r8JarFile)
    // `JavaExec` is not cacheable by default. Inputs/outputs above are
    // complete, so the result is safe to pull from the build cache on CI
    // (where the project `build/` directory does not survive between runs).
    outputs.cacheIf { true }
}

val testR8 by tasks.registering(JavaExec::class) {
    group = "verification"
    dependsOn(r8Jar)

    mainClass.set("com.anthropic.ecosystem.EcosystemCompatibilityTest")
    classpath = files(r8JarFile)

    // This is a verification task with no file outputs, so rerun it only when
    // the JAR changes.
    outputs.upToDateWhen { true }
}

// Java 8 usage smoke test: Java 8 bytecode, executed on a real JDK 8

val testJava8 by tasks.registering(JavaExec::class) {
    group = "verification"
    description = "Runs a small Java SDK consumer on a real JDK 8 runtime."
    dependsOn(tasks.named("testClasses"))

    useJdk8()
    mainClass.set("com.anthropic.ecosystem.Java8UsageMain")
    classpath = sourceSets.test.get().output + configurations.testRuntimeClasspath.get()

    outputs.upToDateWhen { true }
}

// Kotlin 1.8.20 usage smoke test: compiled by kotlinc 1.8.20, then executed on JDK 8

val kotlin1820Out = layout.buildDirectory.dir("kotlin-1820-classes")
val kotlinUsageSources = fileTree("src/test/kotlin") { include("**/*.kt") }

val compileKotlin1820 by tasks.registering(JavaExec::class) {
    group = "verification"
    description =
        "Compiles the shared Kotlin SDK consumer with kotlinc 1.8.20 to verify the SDK's " +
            "@Metadata is readable by old (>= 1.8.20) consumer compilers."

    // The 1.8.20 compiler predates JDK 21, so host it on JDK 8. Running on JDK 8 also avoids the
    // `--add-opens` dance the palantir/javac tasks need on JDK 9+.
    useJdk8()

    classpath = kotlinCompatCompiler
    mainClass.set("org.jetbrains.kotlin.cli.jvm.K2JVMCompiler")

    val outDir = kotlin1820Out
    val compileClasspath: FileCollection = kotlinCompatClasspath
    val sources = kotlinUsageSources

    // `args` is eager and would resolve the classpath at configuration time; an argument provider
    // keeps it lazy so the configuration cache stays valid. stdlib/reflect are supplied explicitly
    // on `-classpath`, so disable the compiler's own bundled copies.
    argumentProviders.add(CommandLineArgumentProvider {
        buildList {
            add("-no-stdlib")
            add("-no-reflect")
            add("-jvm-target")
            add("1.8")
            add("-classpath")
            add(compileClasspath.asPath)
            add("-d")
            add(outDir.get().asFile.absolutePath)
            addAll(sources.files.map { it.absolutePath })
        }
    })

    inputs.files(sources)
        .withPropertyName("kotlinUsageSources")
        .withPathSensitivity(PathSensitivity.RELATIVE)
    inputs.files(compileClasspath)
        .withPropertyName("kotlinCompatClasspath")
        .withNormalizer(ClasspathNormalizer::class)
    outputs.dir(outDir)
    // `JavaExec` is not cacheable by default; inputs/outputs above are
    // complete (the compiler version is fixed by `kotlinCompatCompiler`).
    outputs.cacheIf { true }

    // Gradle only auto-cleans declared output directories for incremental tasks (those taking
    // `InputChanges`); `JavaExec` is not one, so stale .class files from since-removed sources
    // would otherwise survive and reach `runKotlin1820`'s classpath.
    doFirst { outDir.get().asFile.deleteRecursively() }
}

val runKotlin1820 by tasks.registering(JavaExec::class) {
    group = "verification"
    description = "Executes the Kotlin 1.8.20-compiled consumer on JDK 8 against the 1.9.0 stdlib floor."
    dependsOn(compileKotlin1820)

    useJdk8()
    mainClass.set("com.anthropic.ecosystem.EcosystemCompatibilityTest")
    classpath = files(kotlin1820Out) + kotlinCompatClasspath

    outputs.upToDateWhen { true }
}

// Wire every check into `./gradlew test` (and `check`)
tasks.test {
    dependsOn(testProGuard)
    dependsOn(testR8)
    dependsOn(testJava8)
    dependsOn(runKotlin1820) // transitively runs compileKotlin1820
    // We defer to the tests run via the verification tasks above.
    enabled = false
}
