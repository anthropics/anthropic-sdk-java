// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills.versions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionCreateResponseTest {

    @Test
    fun create() {
        val versionCreateResponse =
            VersionCreateResponse.builder()
                .id("skillver_01JAbcdefghijklmnopqrstuvw")
                .createdAt("2024-10-30T23:58:27.427722Z")
                .description("A custom skill for doing something useful")
                .directory("my-skill")
                .name("my-skill")
                .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                .type("type")
                .version("1759178010641129")
                .build()

        assertThat(versionCreateResponse.id()).isEqualTo("skillver_01JAbcdefghijklmnopqrstuvw")
        assertThat(versionCreateResponse.createdAt()).isEqualTo("2024-10-30T23:58:27.427722Z")
        assertThat(versionCreateResponse.description())
            .isEqualTo("A custom skill for doing something useful")
        assertThat(versionCreateResponse.directory()).isEqualTo("my-skill")
        assertThat(versionCreateResponse.name()).isEqualTo("my-skill")
        assertThat(versionCreateResponse.skillId()).isEqualTo("skill_01JAbcdefghijklmnopqrstuvw")
        assertThat(versionCreateResponse.type()).isEqualTo("type")
        assertThat(versionCreateResponse.version()).isEqualTo("1759178010641129")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val versionCreateResponse =
            VersionCreateResponse.builder()
                .id("skillver_01JAbcdefghijklmnopqrstuvw")
                .createdAt("2024-10-30T23:58:27.427722Z")
                .description("A custom skill for doing something useful")
                .directory("my-skill")
                .name("my-skill")
                .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                .type("type")
                .version("1759178010641129")
                .build()

        val roundtrippedVersionCreateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(versionCreateResponse),
                jacksonTypeRef<VersionCreateResponse>(),
            )

        assertThat(roundtrippedVersionCreateResponse).isEqualTo(versionCreateResponse)
    }
}
