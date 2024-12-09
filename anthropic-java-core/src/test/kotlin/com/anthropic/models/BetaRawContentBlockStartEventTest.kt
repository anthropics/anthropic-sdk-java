// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaRawContentBlockStartEventTest {

    @Test
    fun createBetaRawContentBlockStartEvent() {
        val betaRawContentBlockStartEvent =
            BetaRawContentBlockStartEvent.builder()
                .contentBlock(
                    BetaRawContentBlockStartEvent.ContentBlock.ofBetaTextBlock(
                        BetaTextBlock.builder().text("text").type(BetaTextBlock.Type.TEXT).build()
                    )
                )
                .index(0L)
                .type(BetaRawContentBlockStartEvent.Type.CONTENT_BLOCK_START)
                .build()
        assertThat(betaRawContentBlockStartEvent).isNotNull
        assertThat(betaRawContentBlockStartEvent.contentBlock())
            .isEqualTo(
                BetaRawContentBlockStartEvent.ContentBlock.ofBetaTextBlock(
                    BetaTextBlock.builder().text("text").type(BetaTextBlock.Type.TEXT).build()
                )
            )
        assertThat(betaRawContentBlockStartEvent.index()).isEqualTo(0L)
        assertThat(betaRawContentBlockStartEvent.type())
            .isEqualTo(BetaRawContentBlockStartEvent.Type.CONTENT_BLOCK_START)
    }
}
