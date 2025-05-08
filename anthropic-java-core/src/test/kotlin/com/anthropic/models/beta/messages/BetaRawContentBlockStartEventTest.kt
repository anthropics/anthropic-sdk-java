// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRawContentBlockStartEventTest {

    @Test
    fun create() {
        val betaRawContentBlockStartEvent =
            BetaRawContentBlockStartEvent.builder()
                .contentBlock(
                    BetaTextBlock.builder()
                        .addCitation(
                            BetaCitationCharLocation.builder()
                                .citedText("cited_text")
                                .documentIndex(0L)
                                .documentTitle("document_title")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .build()
                        )
                        .text("text")
                        .build()
                )
                .index(0L)
                .build()

        assertThat(betaRawContentBlockStartEvent.contentBlock())
            .isEqualTo(
                BetaRawContentBlockStartEvent.ContentBlock.ofText(
                    BetaTextBlock.builder()
                        .addCitation(
                            BetaCitationCharLocation.builder()
                                .citedText("cited_text")
                                .documentIndex(0L)
                                .documentTitle("document_title")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .build()
                        )
                        .text("text")
                        .build()
                )
            )
        assertThat(betaRawContentBlockStartEvent.index()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRawContentBlockStartEvent =
            BetaRawContentBlockStartEvent.builder()
                .contentBlock(
                    BetaTextBlock.builder()
                        .addCitation(
                            BetaCitationCharLocation.builder()
                                .citedText("cited_text")
                                .documentIndex(0L)
                                .documentTitle("document_title")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .build()
                        )
                        .text("text")
                        .build()
                )
                .index(0L)
                .build()

        val roundtrippedBetaRawContentBlockStartEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRawContentBlockStartEvent),
                jacksonTypeRef<BetaRawContentBlockStartEvent>(),
            )

        assertThat(roundtrippedBetaRawContentBlockStartEvent)
            .isEqualTo(betaRawContentBlockStartEvent)
    }
}
