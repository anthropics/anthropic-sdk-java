// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaRawContentBlockStopEventTest {

    @Test
    fun createBetaRawContentBlockStopEvent() {
        val betaRawContentBlockStopEvent =
            BetaRawContentBlockStopEvent.builder()
                .index(0L)
                .type(BetaRawContentBlockStopEvent.Type.CONTENT_BLOCK_STOP)
                .build()
        assertThat(betaRawContentBlockStopEvent).isNotNull
        assertThat(betaRawContentBlockStopEvent.index()).isEqualTo(0L)
        assertThat(betaRawContentBlockStopEvent.type())
            .isEqualTo(BetaRawContentBlockStopEvent.Type.CONTENT_BLOCK_STOP)
    }
}