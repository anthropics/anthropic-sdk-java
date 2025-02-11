// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaRawContentBlockDeltaEventTest {

    @Test
    fun createBetaRawContentBlockDeltaEvent() {
        val betaRawContentBlockDeltaEvent =
            BetaRawContentBlockDeltaEvent.builder().betaTextDelta("text").index(0L).build()
        assertThat(betaRawContentBlockDeltaEvent).isNotNull
        assertThat(betaRawContentBlockDeltaEvent.delta())
            .isEqualTo(
                BetaRawContentBlockDeltaEvent.Delta.ofBetaText(
                    BetaTextDelta.builder().text("text").build()
                )
            )
        assertThat(betaRawContentBlockDeltaEvent.index()).isEqualTo(0L)
    }
}
