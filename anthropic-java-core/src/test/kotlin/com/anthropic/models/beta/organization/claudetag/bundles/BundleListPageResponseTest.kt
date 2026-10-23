package com.anthropic.models.beta.organization.claudetag.bundles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BundleListPageResponseTest {

    @Test
    fun create() {
        val bundleListPageResponse =
            BundleListPageResponse.builder()
                .addData(
                    BetaClaudeTagBundle.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .description("description")
                        .displayName("display_name")
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(bundleListPageResponse.data())
            .containsExactly(
                BetaClaudeTagBundle.builder()
                    .id("id")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .description("description")
                    .displayName("display_name")
                    .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )
        assertThat(bundleListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val bundleListPageResponse =
            BundleListPageResponse.builder()
                .addData(
                    BetaClaudeTagBundle.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .description("description")
                        .displayName("display_name")
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedBundleListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bundleListPageResponse),
                jacksonTypeRef<BundleListPageResponse>(),
            )

        assertThat(roundtrippedBundleListPageResponse).isEqualTo(bundleListPageResponse)
    }
}
