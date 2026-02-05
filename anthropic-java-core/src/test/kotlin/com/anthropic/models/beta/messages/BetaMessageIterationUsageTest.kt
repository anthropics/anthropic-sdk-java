// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageIterationUsageTest {

    @Test
    fun create() {
        val betaMessageIterationUsage =
            BetaMessageIterationUsage.builder()
                .cacheCreation(
                    BetaCacheCreation.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheCreationInputTokens(0L)
                .cacheReadInputTokens(0L)
                .inputTokens(0L)
                .outputTokens(0L)
                .build()

        assertThat(betaMessageIterationUsage.cacheCreation())
            .contains(
                BetaCacheCreation.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
        assertThat(betaMessageIterationUsage.cacheCreationInputTokens()).isEqualTo(0L)
        assertThat(betaMessageIterationUsage.cacheReadInputTokens()).isEqualTo(0L)
        assertThat(betaMessageIterationUsage.inputTokens()).isEqualTo(0L)
        assertThat(betaMessageIterationUsage.outputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageIterationUsage =
            BetaMessageIterationUsage.builder()
                .cacheCreation(
                    BetaCacheCreation.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheCreationInputTokens(0L)
                .cacheReadInputTokens(0L)
                .inputTokens(0L)
                .outputTokens(0L)
                .build()

        val roundtrippedBetaMessageIterationUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageIterationUsage),
                jacksonTypeRef<BetaMessageIterationUsage>(),
            )

        assertThat(roundtrippedBetaMessageIterationUsage).isEqualTo(betaMessageIterationUsage)
    }
}
