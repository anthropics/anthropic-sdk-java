plugins {
    id("anthropic.kotlin")
    id("com.gradleup.shadow") version "8.3.8"
}

buildscript {
    repositories {
        google()
    }

    dependencies {
        classpath("com.guardsquare:proguard-gradle:7.9.1")
        classpath("com.android.tools:r8:8.3.37")
    }
}

dependencies {
    testImplementation(project(":anthropic-java"))
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testImplementation("org.assertj:assertj-core:3.27.7")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.0")
}

tasks.shadowJar {
    from(sourceSets.test.get().output)
    configurations = listOf(project.configurations.testRuntimeClasspath.get())
}

val proguardJarPath = "${layout.buildDirectory.get()}/libs/${project.name}-${project.version}-proguard.jar"
val proguardJar by tasks.registering(proguard.gradle.ProGuardTask::class) {
    group = "verification"
    dependsOn(tasks.shadowJar)

    // Pass the archive path rather than the task itself: `Task` objects cannot
    // be serialized to the configuration cache.
    injars(tasks.shadowJar.get().archiveFile.get().asFile.absolutePath)
    outjars(proguardJarPath)
    printmapping("${layout.buildDirectory.get()}/proguard-mapping.txt")

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

    mainClass.set("com.anthropic.proguard.ProGuardCompatibilityTest")
    classpath = files(proguardJarPath)

    // This is a verification task with no file outputs, so rerun it only when
    // the JAR changes.
    outputs.upToDateWhen { true }
}

val r8JarPath = "${layout.buildDirectory.get()}/libs/${project.name}-${project.version}-r8.jar"
val r8Jar by tasks.registering(JavaExec::class) {
    group = "verification"
    dependsOn(tasks.shadowJar)

    mainClass.set("com.android.tools.r8.R8")
    classpath = buildscript.configurations["classpath"]

    val proguardConfigs = listOf(
        "./test.pro",
        "../anthropic-java-core/src/main/resources/META-INF/proguard/anthropic-java-core.pro",
    )

    args = listOf(
        "--release",
        "--classfile",
        "--output", r8JarPath,
        "--lib", System.getProperty("java.home"),
        "--pg-conf", proguardConfigs[0],
        "--pg-conf", proguardConfigs[1],
        "--pg-map-output", "${layout.buildDirectory.get()}/r8-mapping.txt",
        tasks.shadowJar.get().archiveFile.get().asFile.absolutePath,
    )

    // `args` are not tracked as task inputs, so declare them explicitly for
    // up-to-date checking.
    inputs.files(tasks.shadowJar.map { it.archiveFile })
    inputs.files(proguardConfigs)
    outputs.file(r8JarPath)
}

val testR8 by tasks.registering(JavaExec::class) {
    group = "verification"
    dependsOn(r8Jar)

    mainClass.set("com.anthropic.proguard.ProGuardCompatibilityTest")
    classpath = files(r8JarPath)

    // This is a verification task with no file outputs, so rerun it only when
    // the JAR changes.
    outputs.upToDateWhen { true }
}

tasks.test {
    dependsOn(testProGuard)
    dependsOn(testR8)
    // We defer to the tests run via the ProGuard JAR.
    enabled = false
}
