// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCompactionIterationUsageTest {

    @Test
    fun create() {
        val betaCompactionIterationUsage =
            BetaCompactionIterationUsage.builder()
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

        assertThat(betaCompactionIterationUsage.cacheCreation())
            .contains(
                BetaCacheCreation.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
        assertThat(betaCompactionIterationUsage.cacheCreationInputTokens()).isEqualTo(0L)
        assertThat(betaCompactionIterationUsage.cacheReadInputTokens()).isEqualTo(0L)
        assertThat(betaCompactionIterationUsage.inputTokens()).isEqualTo(0L)
        assertThat(betaCompactionIterationUsage.outputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCompactionIterationUsage =
            BetaCompactionIterationUsage.builder()
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

        val roundtrippedBetaCompactionIterationUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCompactionIterationUsage),
                jacksonTypeRef<BetaCompactionIterationUsage>(),
            )

        assertThat(roundtrippedBetaCompactionIterationUsage).isEqualTo(betaCompactionIterationUsage)
    }
}
