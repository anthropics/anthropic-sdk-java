// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RawContentBlockStopEventTest {

    @Test
    fun createRawContentBlockStopEvent() {
        val rawContentBlockStopEvent =
            RawContentBlockStopEvent.builder()
                .index(0L)
                .type(RawContentBlockStopEvent.Type.CONTENT_BLOCK_STOP)
                .build()
        assertThat(rawContentBlockStopEvent).isNotNull
        assertThat(rawContentBlockStopEvent.index()).isEqualTo(0L)
        assertThat(rawContentBlockStopEvent.type())
            .isEqualTo(RawContentBlockStopEvent.Type.CONTENT_BLOCK_STOP)
    }
}