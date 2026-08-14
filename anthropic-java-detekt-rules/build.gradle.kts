import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    // Everything from `anthropic.kotlin` except `anthropic.detekt`, which would add this project to
    // its own `detektPlugins` configuration.
    id("anthropic.java")
    kotlin("jvm")
    id("anthropic.ktfmt")
}

val libs = the<VersionCatalogsExtension>().named("libs")

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_1_8)
        // detekt 1.23.8 embeds Kotlin 2.0.21; keep this jar's stdlib calls and metadata at 2.0 so
        // it loads cleanly into that runtime.
        apiVersion.set(KotlinVersion.KOTLIN_2_0)
        languageVersion.set(KotlinVersion.KOTLIN_2_0)
        freeCompilerArgs.add("-Xsuppress-version-warnings")
    }
}

dependencies {
    compileOnly(libs.findLibrary("detekt-api").get())

    testImplementation(libs.findLibrary("detekt-test").get())
    testImplementation(libs.findLibrary("junit-jupiter-api").get())
    testImplementation(libs.findLibrary("assertj").get())
    testRuntimeOnly(libs.findLibrary("junit-jupiter-engine").get())
}

tasks.test {
    // detekt-test resolves the snippet compiler's JDK from the `JAVA_HOME` environment variable and only
    // falls back to the test JVM when it's unset. When the environment exports a `JAVA_HOME` that isn't a
    // full JDK home (or differs from the toolchain running the tests), type resolution silently breaks and
    // every rule that relies on it misfires. Pin it to the JDK actually running the tests.
    doFirst { environment("JAVA_HOME", javaLauncher.get().metadata.installationPath.asFile.absolutePath) }
}
