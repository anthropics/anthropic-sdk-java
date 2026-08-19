// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.skills.versions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillVersionTest {

    @Test
    fun create() {
        val skillVersion =
            SkillVersion.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .description("description")
                .name("name")
                .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                .build()

        assertThat(skillVersion.id()).isEqualTo("id")
        assertThat(skillVersion.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(skillVersion.description()).isEqualTo("description")
        assertThat(skillVersion.name()).isEqualTo("name")
        assertThat(skillVersion.skillId()).isEqualTo("skill_01JAbcdefghijklmnopqrstuvw")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val skillVersion =
            SkillVersion.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .description("description")
                .name("name")
                .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                .build()

        val roundtrippedSkillVersion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(skillVersion),
                jacksonTypeRef<SkillVersion>(),
            )

        assertThat(roundtrippedSkillVersion).isEqualTo(skillVersion)
    }
}
