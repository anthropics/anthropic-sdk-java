// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ModelInfoTest {

    @Test
    fun createModelInfo() {
        val modelInfo =
            ModelInfo.builder()
                .id("claude-3-7-sonnet-20250219")
                .createdAt(OffsetDateTime.parse("2025-02-19T00:00:00Z"))
                .displayName("Claude 3.7 Sonnet")
                .build()
        assertThat(modelInfo).isNotNull
        assertThat(modelInfo.id()).isEqualTo("claude-3-7-sonnet-20250219")
        assertThat(modelInfo.createdAt()).isEqualTo(OffsetDateTime.parse("2025-02-19T00:00:00Z"))
        assertThat(modelInfo.displayName()).isEqualTo("Claude 3.7 Sonnet")
    }
}
