// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRawMessageDeltaEventTest {

    @Test
    fun create() {
        val betaRawMessageDeltaEvent =
            BetaRawMessageDeltaEvent.builder()
                .delta(
                    BetaRawMessageDeltaEvent.Delta.builder()
                        .stopReason(BetaStopReason.END_TURN)
                        .stopSequence("stop_sequence")
                        .build()
                )
                .usage(
                    BetaMessageDeltaUsage.builder()
                        .cacheCreationInputTokens(2051L)
                        .cacheReadInputTokens(2051L)
                        .inputTokens(2095L)
                        .outputTokens(503L)
                        .serverToolUse(BetaServerToolUsage.builder().webSearchRequests(0L).build())
                        .build()
                )
                .build()

        assertThat(betaRawMessageDeltaEvent.delta())
            .isEqualTo(
                BetaRawMessageDeltaEvent.Delta.builder()
                    .stopReason(BetaStopReason.END_TURN)
                    .stopSequence("stop_sequence")
                    .build()
            )
        assertThat(betaRawMessageDeltaEvent.usage())
            .isEqualTo(
                BetaMessageDeltaUsage.builder()
                    .cacheCreationInputTokens(2051L)
                    .cacheReadInputTokens(2051L)
                    .inputTokens(2095L)
                    .outputTokens(503L)
                    .serverToolUse(BetaServerToolUsage.builder().webSearchRequests(0L).build())
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawMessageDeltaEvent =
            BetaRawMessageDeltaEvent.builder()
                .delta(
                    BetaRawMessageDeltaEvent.Delta.builder()
                        .stopReason(BetaStopReason.END_TURN)
                        .stopSequence("stop_sequence")
                        .build()
                )
                .usage(
                    BetaMessageDeltaUsage.builder()
                        .cacheCreationInputTokens(2051L)
                        .cacheReadInputTokens(2051L)
                        .inputTokens(2095L)
                        .outputTokens(503L)
                        .serverToolUse(BetaServerToolUsage.builder().webSearchRequests(0L).build())
                        .build()
                )
                .build()

        val roundtrippedBetaRawMessageDeltaEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawMessageDeltaEvent),
                jacksonTypeRef<BetaRawMessageDeltaEvent>(),
            )

        assertThat(roundtrippedBetaRawMessageDeltaEvent).isEqualTo(betaRawMessageDeltaEvent)
    }
}
