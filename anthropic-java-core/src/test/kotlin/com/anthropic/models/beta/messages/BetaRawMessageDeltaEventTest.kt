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
                .usage(BetaMessageDeltaUsage.builder().outputTokens(503L).build())
                .build()

        assertThat(betaRawMessageDeltaEvent.delta())
            .isEqualTo(
                BetaRawMessageDeltaEvent.Delta.builder()
                    .stopReason(BetaStopReason.END_TURN)
                    .stopSequence("stop_sequence")
                    .build()
            )
        assertThat(betaRawMessageDeltaEvent.usage())
            .isEqualTo(BetaMessageDeltaUsage.builder().outputTokens(503L).build())
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
                .usage(BetaMessageDeltaUsage.builder().outputTokens(503L).build())
                .build()

        val roundtrippedBetaRawMessageDeltaEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawMessageDeltaEvent),
                jacksonTypeRef<BetaRawMessageDeltaEvent>(),
            )

        assertThat(roundtrippedBetaRawMessageDeltaEvent).isEqualTo(betaRawMessageDeltaEvent)
    }
}
