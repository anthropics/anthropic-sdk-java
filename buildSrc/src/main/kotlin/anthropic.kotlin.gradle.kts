import org.jetbrains.kotlin.gradle.dsl.JvmDefaultMode
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    id("anthropic.java")
    kotlin("jvm")
    id("anthropic.ktfmt")
    id("anthropic.detekt")
}

// Precompiled script plugins can't use the generated type-safe `libs` accessors; look the catalog
// up through its extension instead.
val libs = the<VersionCatalogsExtension>().named("libs")

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }

    compilerOptions {
        allWarningsAsErrors = true
        freeCompilerArgs = listOf(
            "-Xjdk-release=1.8",
            // The "language version 2.0 is deprecated" notice comes from the version-validation
            // pass; we pin 2.0 deliberately (see `languageVersion`).
            "-Xsuppress-version-warnings",
            // The SDK necessarily references its own `@Deprecated` members (delegating overloads,
            // `validate()`, tests), so deprecation can't be promoted to an error.
            "-Xwarning-level=DEPRECATION:disabled",
            // Generated `validity()` helpers emit `.toInt()` on `Int` values; harmless and not
            // worth special-casing in the generator.
            "-Xwarning-level=REDUNDANT_CALL_OF_CONVERSION_METHOD:disabled",
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
