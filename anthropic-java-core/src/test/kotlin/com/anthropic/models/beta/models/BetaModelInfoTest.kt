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
                .id("claude-opus-4-6")
                .createdAt(OffsetDateTime.parse("2026-02-04T00:00:00Z"))
                .displayName("Claude Opus 4.6")
                .build()

        assertThat(betaModelInfo.id()).isEqualTo("claude-opus-4-6")
        assertThat(betaModelInfo.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-02-04T00:00:00Z"))
        assertThat(betaModelInfo.displayName()).isEqualTo("Claude Opus 4.6")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaModelInfo =
            BetaModelInfo.builder()
                .id("claude-opus-4-6")
                .createdAt(OffsetDateTime.parse("2026-02-04T00:00:00Z"))
                .displayName("Claude Opus 4.6")
                .build()

        val roundtrippedBetaModelInfo =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaModelInfo),
                jacksonTypeRef<BetaModelInfo>(),
            )

        assertThat(roundtrippedBetaModelInfo).isEqualTo(betaModelInfo)
    }
}
