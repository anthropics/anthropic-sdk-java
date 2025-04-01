// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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
}
