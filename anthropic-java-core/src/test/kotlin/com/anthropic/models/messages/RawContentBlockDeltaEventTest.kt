// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RawContentBlockDeltaEventTest {

    @Test
    fun create() {
        val rawContentBlockDeltaEvent =
            RawContentBlockDeltaEvent.builder().textDelta("text").index(0L).build()

        assertThat(rawContentBlockDeltaEvent.delta())
            .isEqualTo(RawContentBlockDelta.ofText(TextDelta.builder().text("text").build()))
        assertThat(rawContentBlockDeltaEvent.index()).isEqualTo(0L)
    }
}
