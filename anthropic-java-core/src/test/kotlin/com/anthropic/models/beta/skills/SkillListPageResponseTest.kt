// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillListPageResponseTest {

    @Test
    fun create() {
        val skillListPageResponse =
            SkillListPageResponse.builder()
                .addData(
                    SkillListResponse.builder()
                        .id("skill_01JAbcdefghijklmnopqrstuvw")
                        .createdAt("2024-10-30T23:58:27.427722Z")
                        .displayTitle("My Custom Skill")
                        .latestVersion("1759178010641129")
                        .source("custom")
                        .type("type")
                        .updatedAt("2024-10-30T23:58:27.427722Z")
                        .build()
                )
                .hasMore(true)
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        assertThat(skillListPageResponse.data())
            .containsExactly(
                SkillListResponse.builder()
                    .id("skill_01JAbcdefghijklmnopqrstuvw")
                    .createdAt("2024-10-30T23:58:27.427722Z")
                    .displayTitle("My Custom Skill")
                    .latestVersion("1759178010641129")
                    .source("custom")
                    .type("type")
                    .updatedAt("2024-10-30T23:58:27.427722Z")
                    .build()
            )
        assertThat(skillListPageResponse.hasMore()).isEqualTo(true)
        assertThat(skillListPageResponse.nextPage()).contains("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val skillListPageResponse =
            SkillListPageResponse.builder()
                .addData(
                    SkillListResponse.builder()
                        .id("skill_01JAbcdefghijklmnopqrstuvw")
                        .createdAt("2024-10-30T23:58:27.427722Z")
                        .displayTitle("My Custom Skill")
                        .latestVersion("1759178010641129")
                        .source("custom")
                        .type("type")
                        .updatedAt("2024-10-30T23:58:27.427722Z")
                        .build()
                )
                .hasMore(true)
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        val roundtrippedSkillListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(skillListPageResponse),
                jacksonTypeRef<SkillListPageResponse>(),
            )

        assertThat(roundtrippedSkillListPageResponse).isEqualTo(skillListPageResponse)
    }
}
