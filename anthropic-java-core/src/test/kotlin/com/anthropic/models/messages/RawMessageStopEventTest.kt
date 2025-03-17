// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RawMessageStopEventTest {

    @Test
    fun createRawMessageStopEvent() {
        val rawMessageStopEvent = RawMessageStopEvent.builder().build()
        assertThat(rawMessageStopEvent).isNotNull
    }
}
