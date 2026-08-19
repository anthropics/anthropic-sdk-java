// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.skills

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillListPageResponseTest {

    @Test
    fun create() {
        val skillListPageResponse =
            SkillListPageResponse.builder()
                .addData(
                    Skill.builder()
                        .id("skill_01JAbcdefghijklmnopqrstuvw")
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .displayName("display_name")
                        .latestVersionId("latest_version_id")
                        .source(SkillSource.of(SkillSource.Type.CUSTOM))
                        .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(skillListPageResponse.data())
            .containsExactly(
                Skill.builder()
                    .id("skill_01JAbcdefghijklmnopqrstuvw")
                    .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .displayName("display_name")
                    .latestVersionId("latest_version_id")
                    .source(SkillSource.of(SkillSource.Type.CUSTOM))
                    .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .build()
            )
        assertThat(skillListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val skillListPageResponse =
            SkillListPageResponse.builder()
                .addData(
                    Skill.builder()
                        .id("skill_01JAbcdefghijklmnopqrstuvw")
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .displayName("display_name")
                        .latestVersionId("latest_version_id")
                        .source(SkillSource.of(SkillSource.Type.CUSTOM))
                        .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedSkillListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(skillListPageResponse),
                jacksonTypeRef<SkillListPageResponse>(),
            )

        assertThat(roundtrippedSkillListPageResponse).isEqualTo(skillListPageResponse)
    }
}
