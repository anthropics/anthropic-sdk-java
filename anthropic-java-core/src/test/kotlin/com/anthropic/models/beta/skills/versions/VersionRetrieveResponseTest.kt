// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills.versions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionRetrieveResponseTest {

    @Test
    fun create() {
        val versionRetrieveResponse =
            VersionRetrieveResponse.builder()
                .id("skillver_01JAbcdefghijklmnopqrstuvw")
                .createdAt("2024-10-30T23:58:27.427722Z")
                .description("A custom skill for doing something useful")
                .directory("my-skill")
                .name("my-skill")
                .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                .type("type")
                .version("1759178010641129")
                .build()

        assertThat(versionRetrieveResponse.id()).isEqualTo("skillver_01JAbcdefghijklmnopqrstuvw")
        assertThat(versionRetrieveResponse.createdAt()).isEqualTo("2024-10-30T23:58:27.427722Z")
        assertThat(versionRetrieveResponse.description())
            .isEqualTo("A custom skill for doing something useful")
        assertThat(versionRetrieveResponse.directory()).isEqualTo("my-skill")
        assertThat(versionRetrieveResponse.name()).isEqualTo("my-skill")
        assertThat(versionRetrieveResponse.skillId()).isEqualTo("skill_01JAbcdefghijklmnopqrstuvw")
        assertThat(versionRetrieveResponse.type()).isEqualTo("type")
        assertThat(versionRetrieveResponse.version()).isEqualTo("1759178010641129")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val versionRetrieveResponse =
            VersionRetrieveResponse.builder()
                .id("skillver_01JAbcdefghijklmnopqrstuvw")
                .createdAt("2024-10-30T23:58:27.427722Z")
                .description("A custom skill for doing something useful")
                .directory("my-skill")
                .name("my-skill")
                .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                .type("type")
                .version("1759178010641129")
                .build()

        val roundtrippedVersionRetrieveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(versionRetrieveResponse),
                jacksonTypeRef<VersionRetrieveResponse>(),
            )

        assertThat(roundtrippedVersionRetrieveResponse).isEqualTo(versionRetrieveResponse)
    }
}
