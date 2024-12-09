// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RawContentBlockStartEventTest {

    @Test
    fun createRawContentBlockStartEvent() {
        val rawContentBlockStartEvent =
            RawContentBlockStartEvent.builder()
                .contentBlock(
                    RawContentBlockStartEvent.ContentBlock.ofTextBlock(
                        TextBlock.builder().text("text").type(TextBlock.Type.TEXT).build()
                    )
                )
                .index(0L)
                .type(RawContentBlockStartEvent.Type.CONTENT_BLOCK_START)
                .build()
        assertThat(rawContentBlockStartEvent).isNotNull
        assertThat(rawContentBlockStartEvent.contentBlock())
            .isEqualTo(
                RawContentBlockStartEvent.ContentBlock.ofTextBlock(
                    TextBlock.builder().text("text").type(TextBlock.Type.TEXT).build()
                )
            )
        assertThat(rawContentBlockStartEvent.index()).isEqualTo(0L)
        assertThat(rawContentBlockStartEvent.type())
            .isEqualTo(RawContentBlockStartEvent.Type.CONTENT_BLOCK_START)
    }
}
