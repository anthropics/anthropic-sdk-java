// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.anthropic.models.BetaIterationsUsageItems
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUsageTest {

    @Test
    fun create() {
        val betaUsage =
            BetaUsage.builder()
                .cacheCreation(
                    BetaCacheCreation.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inferenceGeo("inference_geo")
                .inputTokens(2095L)
                .addIteration(
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
                )
                .outputTokens(503L)
                .serverToolUse(
                    BetaServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()
                )
                .serviceTier(BetaUsage.ServiceTier.STANDARD)
                .speed(BetaUsage.Speed.STANDARD)
                .build()

        assertThat(betaUsage.cacheCreation())
            .contains(
                BetaCacheCreation.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
        assertThat(betaUsage.cacheCreationInputTokens()).contains(2051L)
        assertThat(betaUsage.cacheReadInputTokens()).contains(2051L)
        assertThat(betaUsage.inferenceGeo()).contains("inference_geo")
        assertThat(betaUsage.inputTokens()).isEqualTo(2095L)
        assertThat(betaUsage.iterations().getOrNull())
            .containsExactly(
                BetaIterationsUsageItems.ofMessageIterationUsage(
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
                )
            )
        assertThat(betaUsage.outputTokens()).isEqualTo(503L)
        assertThat(betaUsage.serverToolUse())
            .contains(
                BetaServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()
            )
        assertThat(betaUsage.serviceTier()).contains(BetaUsage.ServiceTier.STANDARD)
        assertThat(betaUsage.speed()).contains(BetaUsage.Speed.STANDARD)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUsage =
            BetaUsage.builder()
                .cacheCreation(
                    BetaCacheCreation.builder()
                        .ephemeral1hInputTokens(0L)
                        .ephemeral5mInputTokens(0L)
                        .build()
                )
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inferenceGeo("inference_geo")
                .inputTokens(2095L)
                .addIteration(
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
                )
                .outputTokens(503L)
                .serverToolUse(
                    BetaServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()
                )
                .serviceTier(BetaUsage.ServiceTier.STANDARD)
                .speed(BetaUsage.Speed.STANDARD)
                .build()

        val roundtrippedBetaUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUsage),
                jacksonTypeRef<BetaUsage>(),
            )

        assertThat(roundtrippedBetaUsage).isEqualTo(betaUsage)
    }
}
