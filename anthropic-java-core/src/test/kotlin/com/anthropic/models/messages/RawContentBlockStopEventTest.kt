// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RawContentBlockStopEventTest {

    @Test
    fun createRawContentBlockStopEvent() {
        val rawContentBlockStopEvent = RawContentBlockStopEvent.builder().index(0L).build()
        assertThat(rawContentBlockStopEvent).isNotNull
        assertThat(rawContentBlockStopEvent.index()).isEqualTo(0L)
    }
}
