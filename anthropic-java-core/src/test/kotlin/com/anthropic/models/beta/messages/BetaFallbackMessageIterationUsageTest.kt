// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFallbackMessageIterationUsageTest {

    @Test
    fun create() {
        val betaFallbackMessageIterationUsage =
            BetaFallbackMessageIterationUsage.builder()
                .cacheCreation(
                    BetaCacheCreation.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheCreationInputTokens(0L)
                .cacheReadInputTokens(0L)
                .inputTokens(0L)
                .model(Model.CLAUDE_SONNET_5)
                .outputTokens(0L)
                .build()

        assertThat(betaFallbackMessageIterationUsage.cacheCreation())
            .contains(
                BetaCacheCreation.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
        assertThat(betaFallbackMessageIterationUsage.cacheCreationInputTokens()).isEqualTo(0L)
        assertThat(betaFallbackMessageIterationUsage.cacheReadInputTokens()).isEqualTo(0L)
        assertThat(betaFallbackMessageIterationUsage.inputTokens()).isEqualTo(0L)
        assertThat(betaFallbackMessageIterationUsage.model()).isEqualTo(Model.CLAUDE_SONNET_5)
        assertThat(betaFallbackMessageIterationUsage.outputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFallbackMessageIterationUsage =
            BetaFallbackMessageIterationUsage.builder()
                .cacheCreation(
                    BetaCacheCreation.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheCreationInputTokens(0L)
                .cacheReadInputTokens(0L)
                .inputTokens(0L)
                .model(Model.CLAUDE_SONNET_5)
                .outputTokens(0L)
                .build()

        val roundtrippedBetaFallbackMessageIterationUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFallbackMessageIterationUsage),
                jacksonTypeRef<BetaFallbackMessageIterationUsage>(),
            )

        assertThat(roundtrippedBetaFallbackMessageIterationUsage)
            .isEqualTo(betaFallbackMessageIterationUsage)
    }
}
