// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRawContentBlockDeltaEventTest {

    @Test
    fun create() {
        val betaRawContentBlockDeltaEvent =
            BetaRawContentBlockDeltaEvent.builder().textDelta("text").index(0L).build()

        assertThat(betaRawContentBlockDeltaEvent.delta())
            .isEqualTo(
                BetaRawContentBlockDelta.ofText(BetaTextDelta.builder().text("text").build())
            )
        assertThat(betaRawContentBlockDeltaEvent.index()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockDeltaEvent =
            BetaRawContentBlockDeltaEvent.builder().textDelta("text").index(0L).build()

        val roundtrippedBetaRawContentBlockDeltaEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawContentBlockDeltaEvent),
                jacksonTypeRef<BetaRawContentBlockDeltaEvent>(),
            )

        assertThat(roundtrippedBetaRawContentBlockDeltaEvent)
            .isEqualTo(betaRawContentBlockDeltaEvent)
    }
}
