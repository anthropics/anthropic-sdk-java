// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAdvisorMessageIterationUsageTest {

    @Test
    fun create() {
        val betaAdvisorMessageIterationUsage =
            BetaAdvisorMessageIterationUsage.builder()
                .cacheCreation(
                    BetaCacheCreation.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheCreationInputTokens(0L)
                .cacheReadInputTokens(0L)
                .inputTokens(0L)
                .model(Model.CLAUDE_MYTHOS_PREVIEW)
                .outputTokens(0L)
                .build()

        assertThat(betaAdvisorMessageIterationUsage.cacheCreation())
            .contains(
                BetaCacheCreation.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
        assertThat(betaAdvisorMessageIterationUsage.cacheCreationInputTokens()).isEqualTo(0L)
        assertThat(betaAdvisorMessageIterationUsage.cacheReadInputTokens()).isEqualTo(0L)
        assertThat(betaAdvisorMessageIterationUsage.inputTokens()).isEqualTo(0L)
        assertThat(betaAdvisorMessageIterationUsage.model()).isEqualTo(Model.CLAUDE_MYTHOS_PREVIEW)
        assertThat(betaAdvisorMessageIterationUsage.outputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAdvisorMessageIterationUsage =
            BetaAdvisorMessageIterationUsage.builder()
                .cacheCreation(
                    BetaCacheCreation.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheCreationInputTokens(0L)
                .cacheReadInputTokens(0L)
                .inputTokens(0L)
                .model(Model.CLAUDE_MYTHOS_PREVIEW)
                .outputTokens(0L)
                .build()

        val roundtrippedBetaAdvisorMessageIterationUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAdvisorMessageIterationUsage),
                jacksonTypeRef<BetaAdvisorMessageIterationUsage>(),
            )

        assertThat(roundtrippedBetaAdvisorMessageIterationUsage)
            .isEqualTo(betaAdvisorMessageIterationUsage)
    }
}
