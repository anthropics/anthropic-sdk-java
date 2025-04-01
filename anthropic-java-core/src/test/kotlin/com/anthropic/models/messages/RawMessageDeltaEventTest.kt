// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
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

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
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

        val roundtrippedRawMessageDeltaEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawMessageDeltaEvent),
                jacksonTypeRef<RawMessageDeltaEvent>(),
            )

        assertThat(roundtrippedRawMessageDeltaEvent).isEqualTo(rawMessageDeltaEvent)
    }
}
