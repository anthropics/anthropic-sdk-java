// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaModelInfoTest {

    @Test
    fun createBetaModelInfo() {
        val betaModelInfo =
            BetaModelInfo.builder()
                .id("claude-3-7-sonnet-20250219")
                .createdAt(OffsetDateTime.parse("2025-02-19T00:00:00Z"))
                .displayName("Claude 3.7 Sonnet")
                .build()
        assertThat(betaModelInfo).isNotNull
        assertThat(betaModelInfo.id()).isEqualTo("claude-3-7-sonnet-20250219")
        assertThat(betaModelInfo.createdAt())
            .isEqualTo(OffsetDateTime.parse("2025-02-19T00:00:00Z"))
        assertThat(betaModelInfo.displayName()).isEqualTo("Claude 3.7 Sonnet")
    }
}
