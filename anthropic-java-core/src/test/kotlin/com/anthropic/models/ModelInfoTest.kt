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
                .id("claude-3-5-sonnet-20241022")
                .createdAt(OffsetDateTime.parse("2024-10-22T00:00:00Z"))
                .displayName("Claude 3.5 Sonnet (New)")
                .build()
        assertThat(modelInfo).isNotNull
        assertThat(modelInfo.id()).isEqualTo("claude-3-5-sonnet-20241022")
        assertThat(modelInfo.createdAt()).isEqualTo(OffsetDateTime.parse("2024-10-22T00:00:00Z"))
        assertThat(modelInfo.displayName()).isEqualTo("Claude 3.5 Sonnet (New)")
    }
}
