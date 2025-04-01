// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaModelInfoTest {

    @Test
    fun create() {
        val betaModelInfo =
            BetaModelInfo.builder()
                .id("claude-3-7-sonnet-20250219")
                .createdAt(OffsetDateTime.parse("2025-02-19T00:00:00Z"))
                .displayName("Claude 3.7 Sonnet")
                .build()

        assertThat(betaModelInfo.id()).isEqualTo("claude-3-7-sonnet-20250219")
        assertThat(betaModelInfo.createdAt())
            .isEqualTo(OffsetDateTime.parse("2025-02-19T00:00:00Z"))
        assertThat(betaModelInfo.displayName()).isEqualTo("Claude 3.7 Sonnet")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaModelInfo =
            BetaModelInfo.builder()
                .id("claude-3-7-sonnet-20250219")
                .createdAt(OffsetDateTime.parse("2025-02-19T00:00:00Z"))
                .displayName("Claude 3.7 Sonnet")
                .build()

        val roundtrippedBetaModelInfo =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaModelInfo),
                jacksonTypeRef<BetaModelInfo>(),
            )

        assertThat(roundtrippedBetaModelInfo).isEqualTo(betaModelInfo)
    }
}
