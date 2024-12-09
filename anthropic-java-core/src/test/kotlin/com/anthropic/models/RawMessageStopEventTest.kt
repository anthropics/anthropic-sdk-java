// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RawMessageStopEventTest {

    @Test
    fun createRawMessageStopEvent() {
        val rawMessageStopEvent =
            RawMessageStopEvent.builder().type(RawMessageStopEvent.Type.MESSAGE_STOP).build()
        assertThat(rawMessageStopEvent).isNotNull
        assertThat(rawMessageStopEvent.type()).isEqualTo(RawMessageStopEvent.Type.MESSAGE_STOP)
    }
}
