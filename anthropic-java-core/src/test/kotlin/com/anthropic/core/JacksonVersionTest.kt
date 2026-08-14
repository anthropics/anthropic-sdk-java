package com.anthropic.core

import com.fasterxml.jackson.core.Version
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test

/**
 * Guards the build configuration: `test` runs against the Jackson floor and `testJacksonPublished`
 * runs against the published version. Each task passes its expected version via the
 * `expected.jackson.version` system property; this test fails if the runtime Jackson on the
 * classpath disagrees, so a resolution-strategy change that quietly puts both tasks on the same
 * version cannot pass CI.
 */
internal class JacksonVersionTest {

    @Test
    fun runtimeJacksonMatchesExpected() {
        val expected = System.getProperty("expected.jackson.version")
        // Skipped (not passed) when run outside Gradle, where the property is not set.
        assumeTrue(expected != null, "expected.jackson.version not set; run via Gradle")

        val versions =
            mapOf(
                "jackson-core" to com.fasterxml.jackson.core.json.PackageVersion.VERSION,
                "jackson-databind" to com.fasterxml.jackson.databind.cfg.PackageVersion.VERSION,
                "jackson-datatype-jdk8" to
                    com.fasterxml.jackson.datatype.jdk8.PackageVersion.VERSION,
                "jackson-datatype-jsr310" to
                    com.fasterxml.jackson.datatype.jsr310.PackageVersion.VERSION,
                "jackson-module-kotlin" to
                    com.fasterxml.jackson.module.kotlin.PackageVersion.VERSION,
            )
        assertThat(versions.mapValues { (_, v) -> v.semver() }).allSatisfy { artifact, version ->
            assertThat(version).describedAs(artifact).isEqualTo(expected)
        }
    }

    private fun Version.semver(): String = "$majorVersion.$minorVersion.$patchLevel"
}
