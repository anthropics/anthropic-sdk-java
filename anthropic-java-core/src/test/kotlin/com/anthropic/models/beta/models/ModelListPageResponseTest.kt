// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ModelListPageResponseTest {

    @Test
    fun create() {
        val modelListPageResponse =
            ModelListPageResponse.builder()
                .addData(
                    BetaModelInfo.builder()
                        .id("claude-3-7-sonnet-20250219")
                        .createdAt(OffsetDateTime.parse("2025-02-19T00:00:00Z"))
                        .displayName("Claude 3.7 Sonnet")
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        assertThat(modelListPageResponse.data())
            .containsExactly(
                BetaModelInfo.builder()
                    .id("claude-3-7-sonnet-20250219")
                    .createdAt(OffsetDateTime.parse("2025-02-19T00:00:00Z"))
                    .displayName("Claude 3.7 Sonnet")
                    .build()
            )
        assertThat(modelListPageResponse.firstId()).contains("first_id")
        assertThat(modelListPageResponse.hasMore()).isEqualTo(true)
        assertThat(modelListPageResponse.lastId()).contains("last_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val modelListPageResponse =
            ModelListPageResponse.builder()
                .addData(
                    BetaModelInfo.builder()
                        .id("claude-3-7-sonnet-20250219")
                        .createdAt(OffsetDateTime.parse("2025-02-19T00:00:00Z"))
                        .displayName("Claude 3.7 Sonnet")
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        val roundtrippedModelListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(modelListPageResponse),
                jacksonTypeRef<ModelListPageResponse>(),
            )

        assertThat(roundtrippedModelListPageResponse).isEqualTo(modelListPageResponse)
    }
}
