// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills.versions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionListPageResponseTest {

    @Test
    fun create() {
        val versionListPageResponse =
            VersionListPageResponse.builder()
                .addData(
                    VersionListResponse.builder()
                        .id("skillver_01JAbcdefghijklmnopqrstuvw")
                        .createdAt("2024-10-30T23:58:27.427722Z")
                        .description("A custom skill for doing something useful")
                        .directory("my-skill")
                        .name("my-skill")
                        .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                        .type("type")
                        .version("1759178010641129")
                        .build()
                )
                .hasMore(true)
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        assertThat(versionListPageResponse.data())
            .containsExactly(
                VersionListResponse.builder()
                    .id("skillver_01JAbcdefghijklmnopqrstuvw")
                    .createdAt("2024-10-30T23:58:27.427722Z")
                    .description("A custom skill for doing something useful")
                    .directory("my-skill")
                    .name("my-skill")
                    .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                    .type("type")
                    .version("1759178010641129")
                    .build()
            )
        assertThat(versionListPageResponse.hasMore()).isEqualTo(true)
        assertThat(versionListPageResponse.nextPage()).contains("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val versionListPageResponse =
            VersionListPageResponse.builder()
                .addData(
                    VersionListResponse.builder()
                        .id("skillver_01JAbcdefghijklmnopqrstuvw")
                        .createdAt("2024-10-30T23:58:27.427722Z")
                        .description("A custom skill for doing something useful")
                        .directory("my-skill")
                        .name("my-skill")
                        .skillId("skill_01JAbcdefghijklmnopqrstuvw")
                        .type("type")
                        .version("1759178010641129")
                        .build()
                )
                .hasMore(true)
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        val roundtrippedVersionListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(versionListPageResponse),
                jacksonTypeRef<VersionListPageResponse>(),
            )

        assertThat(roundtrippedVersionListPageResponse).isEqualTo(versionListPageResponse)
    }
}
