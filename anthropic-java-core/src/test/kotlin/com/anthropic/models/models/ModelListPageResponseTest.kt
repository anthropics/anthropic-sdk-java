// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

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
                    ModelInfo.builder()
                        .id("claude-sonnet-4-20250514")
                        .createdAt(OffsetDateTime.parse("2025-02-19T00:00:00Z"))
                        .displayName("Claude Sonnet 4")
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        assertThat(modelListPageResponse.data())
            .containsExactly(
                ModelInfo.builder()
                    .id("claude-sonnet-4-20250514")
                    .createdAt(OffsetDateTime.parse("2025-02-19T00:00:00Z"))
                    .displayName("Claude Sonnet 4")
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
                    ModelInfo.builder()
                        .id("claude-sonnet-4-20250514")
                        .createdAt(OffsetDateTime.parse("2025-02-19T00:00:00Z"))
                        .displayName("Claude Sonnet 4")
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
