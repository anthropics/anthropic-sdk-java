// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSkillTest {

    @Test
    fun create() {
        val betaSkill =
            BetaSkill.builder()
                .id("skill_01JAbcdefghijklmnopqrstuvw")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .displayName("display_name")
                .latestVersionId("latest_version_id")
                .source(BetaSkillSource.of(BetaSkillSource.Type.CUSTOM))
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        assertThat(betaSkill.id()).isEqualTo("skill_01JAbcdefghijklmnopqrstuvw")
        assertThat(betaSkill.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaSkill.displayName()).isEqualTo("display_name")
        assertThat(betaSkill.latestVersionId()).isEqualTo("latest_version_id")
        assertThat(betaSkill.source()).isEqualTo(BetaSkillSource.of(BetaSkillSource.Type.CUSTOM))
        assertThat(betaSkill.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSkill =
            BetaSkill.builder()
                .id("skill_01JAbcdefghijklmnopqrstuvw")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .displayName("display_name")
                .latestVersionId("latest_version_id")
                .source(BetaSkillSource.of(BetaSkillSource.Type.CUSTOM))
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        val roundtrippedBetaSkill =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSkill),
                jacksonTypeRef<BetaSkill>(),
            )

        assertThat(roundtrippedBetaSkill).isEqualTo(betaSkill)
    }
}
