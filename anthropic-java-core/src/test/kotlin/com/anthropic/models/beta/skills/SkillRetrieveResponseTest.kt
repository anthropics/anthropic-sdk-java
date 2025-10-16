// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillRetrieveResponseTest {

    @Test
    fun create() {
        val skillRetrieveResponse =
            SkillRetrieveResponse.builder()
                .id("skill_01JAbcdefghijklmnopqrstuvw")
                .createdAt("2024-10-30T23:58:27.427722Z")
                .displayTitle("My Custom Skill")
                .latestVersion("1759178010641129")
                .source("custom")
                .type("type")
                .updatedAt("2024-10-30T23:58:27.427722Z")
                .build()

        assertThat(skillRetrieveResponse.id()).isEqualTo("skill_01JAbcdefghijklmnopqrstuvw")
        assertThat(skillRetrieveResponse.createdAt()).isEqualTo("2024-10-30T23:58:27.427722Z")
        assertThat(skillRetrieveResponse.displayTitle()).contains("My Custom Skill")
        assertThat(skillRetrieveResponse.latestVersion()).contains("1759178010641129")
        assertThat(skillRetrieveResponse.source()).isEqualTo("custom")
        assertThat(skillRetrieveResponse.type()).isEqualTo("type")
        assertThat(skillRetrieveResponse.updatedAt()).isEqualTo("2024-10-30T23:58:27.427722Z")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val skillRetrieveResponse =
            SkillRetrieveResponse.builder()
                .id("skill_01JAbcdefghijklmnopqrstuvw")
                .createdAt("2024-10-30T23:58:27.427722Z")
                .displayTitle("My Custom Skill")
                .latestVersion("1759178010641129")
                .source("custom")
                .type("type")
                .updatedAt("2024-10-30T23:58:27.427722Z")
                .build()

        val roundtrippedSkillRetrieveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(skillRetrieveResponse),
                jacksonTypeRef<SkillRetrieveResponse>(),
            )

        assertThat(roundtrippedSkillRetrieveResponse).isEqualTo(skillRetrieveResponse)
    }
}
