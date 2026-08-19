// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.skills.versions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionListPageResponseTest {

    @Test
    fun create() {
        val versionListPageResponse =
            VersionListPageResponse.builder()
                .addData(
                    SkillVersion.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .description("description")
                        .name("name")
                        .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(versionListPageResponse.data())
            .containsExactly(
                SkillVersion.builder()
                    .id("id")
                    .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .description("description")
                    .name("name")
                    .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                    .build()
            )
        assertThat(versionListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val versionListPageResponse =
            VersionListPageResponse.builder()
                .addData(
                    SkillVersion.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .description("description")
                        .name("name")
                        .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedVersionListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(versionListPageResponse),
                jacksonTypeRef<VersionListPageResponse>(),
            )

        assertThat(roundtrippedVersionListPageResponse).isEqualTo(versionListPageResponse)
    }
}
