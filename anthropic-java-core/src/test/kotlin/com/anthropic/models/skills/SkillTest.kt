// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.skills

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillTest {

    @Test
    fun create() {
        val skill =
            Skill.builder()
                .id("skill_01JAbcdefghijklmnopqrstuvw")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .displayName("display_name")
                .latestVersionId("latest_version_id")
                .source(SkillSource.of(SkillSource.Type.CUSTOM))
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        assertThat(skill.id()).isEqualTo("skill_01JAbcdefghijklmnopqrstuvw")
        assertThat(skill.createdAt()).isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(skill.displayName()).isEqualTo("display_name")
        assertThat(skill.latestVersionId()).isEqualTo("latest_version_id")
        assertThat(skill.source()).isEqualTo(SkillSource.of(SkillSource.Type.CUSTOM))
        assertThat(skill.updatedAt()).isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val skill =
            Skill.builder()
                .id("skill_01JAbcdefghijklmnopqrstuvw")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .displayName("display_name")
                .latestVersionId("latest_version_id")
                .source(SkillSource.of(SkillSource.Type.CUSTOM))
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        val roundtrippedSkill =
            jsonMapper.readValue(jsonMapper.writeValueAsString(skill), jacksonTypeRef<Skill>())

        assertThat(roundtrippedSkill).isEqualTo(skill)
    }
}
