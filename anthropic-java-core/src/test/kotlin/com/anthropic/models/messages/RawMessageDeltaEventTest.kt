// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RawMessageDeltaEventTest {

    @Test
    fun create() {
        val rawMessageDeltaEvent =
            RawMessageDeltaEvent.builder()
                .delta(
                    RawMessageDeltaEvent.Delta.builder()
                        .stopReason(StopReason.END_TURN)
                        .stopSequence("stop_sequence")
                        .build()
                )
                .usage(MessageDeltaUsage.builder().outputTokens(503L).build())
                .build()

        assertThat(rawMessageDeltaEvent.delta())
            .isEqualTo(
                RawMessageDeltaEvent.Delta.builder()
                    .stopReason(StopReason.END_TURN)
                    .stopSequence("stop_sequence")
                    .build()
            )
        assertThat(rawMessageDeltaEvent.usage())
            .isEqualTo(MessageDeltaUsage.builder().outputTokens(503L).build())
    }
}
