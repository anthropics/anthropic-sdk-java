// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRawMessageStopEventTest {

    @Test
    fun createBetaRawMessageStopEvent() {
        val betaRawMessageStopEvent = BetaRawMessageStopEvent.builder().build()
        assertThat(betaRawMessageStopEvent).isNotNull
    }
}
