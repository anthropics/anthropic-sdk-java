// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaRawContentBlockStopEventTest {

    @Test
    fun createBetaRawContentBlockStopEvent() {
        val betaRawContentBlockStopEvent = BetaRawContentBlockStopEvent.builder().index(0L).build()
        assertThat(betaRawContentBlockStopEvent).isNotNull
        assertThat(betaRawContentBlockStopEvent.index()).isEqualTo(0L)
    }
}
