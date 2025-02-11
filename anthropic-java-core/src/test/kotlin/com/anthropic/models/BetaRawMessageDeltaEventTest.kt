// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaRawMessageDeltaEventTest {

    @Test
    fun createBetaRawMessageDeltaEvent() {
        val betaRawMessageDeltaEvent =
            BetaRawMessageDeltaEvent.builder()
                .delta(
                    BetaRawMessageDeltaEvent.Delta.builder()
                        .stopReason(BetaRawMessageDeltaEvent.Delta.StopReason.END_TURN)
                        .stopSequence("stop_sequence")
                        .build()
                )
                .usage(BetaMessageDeltaUsage.builder().outputTokens(503L).build())
                .build()
        assertThat(betaRawMessageDeltaEvent).isNotNull
        assertThat(betaRawMessageDeltaEvent.delta())
            .isEqualTo(
                BetaRawMessageDeltaEvent.Delta.builder()
                    .stopReason(BetaRawMessageDeltaEvent.Delta.StopReason.END_TURN)
                    .stopSequence("stop_sequence")
                    .build()
            )
        assertThat(betaRawMessageDeltaEvent.usage())
            .isEqualTo(BetaMessageDeltaUsage.builder().outputTokens(503L).build())
    }
}
