// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills.versions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSkillVersionTest {

    @Test
    fun create() {
        val betaSkillVersion =
            BetaSkillVersion.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .description("description")
                .name("name")
                .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                .build()

        assertThat(betaSkillVersion.id()).isEqualTo("id")
        assertThat(betaSkillVersion.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaSkillVersion.description()).isEqualTo("description")
        assertThat(betaSkillVersion.name()).isEqualTo("name")
        assertThat(betaSkillVersion.skillId()).isEqualTo("skill_01JAbcdefghijklmnopqrstuvw")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSkillVersion =
            BetaSkillVersion.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .description("description")
                .name("name")
                .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                .build()

        val roundtrippedBetaSkillVersion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSkillVersion),
                jacksonTypeRef<BetaSkillVersion>(),
            )

        assertThat(roundtrippedBetaSkillVersion).isEqualTo(betaSkillVersion)
    }
}
