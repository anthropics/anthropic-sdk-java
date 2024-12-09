// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaRawMessageStopEventTest {

    @Test
    fun createBetaRawMessageStopEvent() {
        val betaRawMessageStopEvent =
            BetaRawMessageStopEvent.builder()
                .type(BetaRawMessageStopEvent.Type.MESSAGE_STOP)
                .build()
        assertThat(betaRawMessageStopEvent).isNotNull
        assertThat(betaRawMessageStopEvent.type())
            .isEqualTo(BetaRawMessageStopEvent.Type.MESSAGE_STOP)
    }
}
