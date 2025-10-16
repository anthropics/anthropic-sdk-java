// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillListResponseTest {

    @Test
    fun create() {
        val skillListResponse =
            SkillListResponse.builder()
                .id("skill_01JAbcdefghijklmnopqrstuvw")
                .createdAt("2024-10-30T23:58:27.427722Z")
                .displayTitle("My Custom Skill")
                .latestVersion("1759178010641129")
                .source("custom")
                .type("type")
                .updatedAt("2024-10-30T23:58:27.427722Z")
                .build()

        assertThat(skillListResponse.id()).isEqualTo("skill_01JAbcdefghijklmnopqrstuvw")
        assertThat(skillListResponse.createdAt()).isEqualTo("2024-10-30T23:58:27.427722Z")
        assertThat(skillListResponse.displayTitle()).contains("My Custom Skill")
        assertThat(skillListResponse.latestVersion()).contains("1759178010641129")
        assertThat(skillListResponse.source()).isEqualTo("custom")
        assertThat(skillListResponse.type()).isEqualTo("type")
        assertThat(skillListResponse.updatedAt()).isEqualTo("2024-10-30T23:58:27.427722Z")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val skillListResponse =
            SkillListResponse.builder()
                .id("skill_01JAbcdefghijklmnopqrstuvw")
                .createdAt("2024-10-30T23:58:27.427722Z")
                .displayTitle("My Custom Skill")
                .latestVersion("1759178010641129")
                .source("custom")
                .type("type")
                .updatedAt("2024-10-30T23:58:27.427722Z")
                .build()

        val roundtrippedSkillListResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(skillListResponse),
                jacksonTypeRef<SkillListResponse>(),
            )

        assertThat(roundtrippedSkillListResponse).isEqualTo(skillListResponse)
    }
}
