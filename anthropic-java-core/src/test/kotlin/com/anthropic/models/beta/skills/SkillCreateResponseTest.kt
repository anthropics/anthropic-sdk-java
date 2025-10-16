// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillCreateResponseTest {

    @Test
    fun create() {
        val skillCreateResponse =
            SkillCreateResponse.builder()
                .id("skill_01JAbcdefghijklmnopqrstuvw")
                .createdAt("2024-10-30T23:58:27.427722Z")
                .displayTitle("My Custom Skill")
                .latestVersion("1759178010641129")
                .source("custom")
                .type("type")
                .updatedAt("2024-10-30T23:58:27.427722Z")
                .build()

        assertThat(skillCreateResponse.id()).isEqualTo("skill_01JAbcdefghijklmnopqrstuvw")
        assertThat(skillCreateResponse.createdAt()).isEqualTo("2024-10-30T23:58:27.427722Z")
        assertThat(skillCreateResponse.displayTitle()).contains("My Custom Skill")
        assertThat(skillCreateResponse.latestVersion()).contains("1759178010641129")
        assertThat(skillCreateResponse.source()).isEqualTo("custom")
        assertThat(skillCreateResponse.type()).isEqualTo("type")
        assertThat(skillCreateResponse.updatedAt()).isEqualTo("2024-10-30T23:58:27.427722Z")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val skillCreateResponse =
            SkillCreateResponse.builder()
                .id("skill_01JAbcdefghijklmnopqrstuvw")
                .createdAt("2024-10-30T23:58:27.427722Z")
                .displayTitle("My Custom Skill")
                .latestVersion("1759178010641129")
                .source("custom")
                .type("type")
                .updatedAt("2024-10-30T23:58:27.427722Z")
                .build()

        val roundtrippedSkillCreateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(skillCreateResponse),
                jacksonTypeRef<SkillCreateResponse>(),
            )

        assertThat(roundtrippedSkillCreateResponse).isEqualTo(skillCreateResponse)
    }
}
