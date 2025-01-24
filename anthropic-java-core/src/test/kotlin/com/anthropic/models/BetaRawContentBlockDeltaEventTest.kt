// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaRawContentBlockDeltaEventTest {

    @Test
    fun createBetaRawContentBlockDeltaEvent() {
        val betaRawContentBlockDeltaEvent =
            BetaRawContentBlockDeltaEvent.builder()
                .delta(
                    BetaTextDelta.builder().text("text").type(BetaTextDelta.Type.TEXT_DELTA).build()
                )
                .index(0L)
                .type(BetaRawContentBlockDeltaEvent.Type.CONTENT_BLOCK_DELTA)
                .build()
        assertThat(betaRawContentBlockDeltaEvent).isNotNull
        assertThat(betaRawContentBlockDeltaEvent.delta())
            .isEqualTo(
                BetaRawContentBlockDeltaEvent.Delta.ofBetaText(
                    BetaTextDelta.builder().text("text").type(BetaTextDelta.Type.TEXT_DELTA).build()
                )
            )
        assertThat(betaRawContentBlockDeltaEvent.index()).isEqualTo(0L)
        assertThat(betaRawContentBlockDeltaEvent.type())
            .isEqualTo(BetaRawContentBlockDeltaEvent.Type.CONTENT_BLOCK_DELTA)
    }
}
