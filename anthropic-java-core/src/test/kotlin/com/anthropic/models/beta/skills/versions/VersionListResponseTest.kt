// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills.versions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionListResponseTest {

    @Test
    fun create() {
        val versionListResponse =
            VersionListResponse.builder()
                .id("skillver_01JAbcdefghijklmnopqrstuvw")
                .createdAt("2024-10-30T23:58:27.427722Z")
                .description("A custom skill for doing something useful")
                .directory("my-skill")
                .name("my-skill")
                .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                .type("type")
                .version("1759178010641129")
                .build()

        assertThat(versionListResponse.id()).isEqualTo("skillver_01JAbcdefghijklmnopqrstuvw")
        assertThat(versionListResponse.createdAt()).isEqualTo("2024-10-30T23:58:27.427722Z")
        assertThat(versionListResponse.description())
            .isEqualTo("A custom skill for doing something useful")
        assertThat(versionListResponse.directory()).isEqualTo("my-skill")
        assertThat(versionListResponse.name()).isEqualTo("my-skill")
        assertThat(versionListResponse.skillId()).isEqualTo("skill_01JAbcdefghijklmnopqrstuvw")
        assertThat(versionListResponse.type()).isEqualTo("type")
        assertThat(versionListResponse.version()).isEqualTo("1759178010641129")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val versionListResponse =
            VersionListResponse.builder()
                .id("skillver_01JAbcdefghijklmnopqrstuvw")
                .createdAt("2024-10-30T23:58:27.427722Z")
                .description("A custom skill for doing something useful")
                .directory("my-skill")
                .name("my-skill")
                .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                .type("type")
                .version("1759178010641129")
                .build()

        val roundtrippedVersionListResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(versionListResponse),
                jacksonTypeRef<VersionListResponse>(),
            )

        assertThat(roundtrippedVersionListResponse).isEqualTo(versionListResponse)
    }
}
