// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ModelInfoTest {

    @Test
    fun create() {
        val modelInfo =
            ModelInfo.builder()
                .id("claude-opus-4-6")
                .createdAt(OffsetDateTime.parse("2026-02-04T00:00:00Z"))
                .displayName("Claude Opus 4.6")
                .build()

        assertThat(modelInfo.id()).isEqualTo("claude-opus-4-6")
        assertThat(modelInfo.createdAt()).isEqualTo(OffsetDateTime.parse("2026-02-04T00:00:00Z"))
        assertThat(modelInfo.displayName()).isEqualTo("Claude Opus 4.6")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val modelInfo =
            ModelInfo.builder()
                .id("claude-opus-4-6")
                .createdAt(OffsetDateTime.parse("2026-02-04T00:00:00Z"))
                .displayName("Claude Opus 4.6")
                .build()

        val roundtrippedModelInfo =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(modelInfo),
                jacksonTypeRef<ModelInfo>(),
            )

        assertThat(roundtrippedModelInfo).isEqualTo(modelInfo)
    }
}
