// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RawContentBlockDeltaEventTest {

    @Test
    fun createRawContentBlockDeltaEvent() {
        val rawContentBlockDeltaEvent =
            RawContentBlockDeltaEvent.builder()
                .delta(TextDelta.builder().text("text").type(TextDelta.Type.TEXT_DELTA).build())
                .index(0L)
                .type(RawContentBlockDeltaEvent.Type.CONTENT_BLOCK_DELTA)
                .build()
        assertThat(rawContentBlockDeltaEvent).isNotNull
        assertThat(rawContentBlockDeltaEvent.delta())
            .isEqualTo(
                RawContentBlockDeltaEvent.Delta.ofText(
                    TextDelta.builder().text("text").type(TextDelta.Type.TEXT_DELTA).build()
                )
            )
        assertThat(rawContentBlockDeltaEvent.index()).isEqualTo(0L)
        assertThat(rawContentBlockDeltaEvent.type())
            .isEqualTo(RawContentBlockDeltaEvent.Type.CONTENT_BLOCK_DELTA)
    }
}
