// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RawContentBlockStartEventTest {

    @Test
    fun create() {
        val rawContentBlockStartEvent =
            RawContentBlockStartEvent.builder()
                .contentBlock(
                    TextBlock.builder()
                        .addCitation(
                            CitationCharLocation.builder()
                                .citedText("The grass is green. The sky is blue.")
                                .documentIndex(0L)
                                .documentTitle("My Document")
                                .endCharIndex(0L)
                                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                                .startCharIndex(0L)
                                .build()
                        )
                        .text("text")
                        .build()
                )
                .index(0L)
                .build()

        assertThat(rawContentBlockStartEvent.contentBlock())
            .isEqualTo(
                RawContentBlockStartEvent.ContentBlock.ofText(
                    TextBlock.builder()
                        .addCitation(
                            CitationCharLocation.builder()
                                .citedText("The grass is green. The sky is blue.")
                                .documentIndex(0L)
                                .documentTitle("My Document")
                                .endCharIndex(0L)
                                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                                .startCharIndex(0L)
                                .build()
                        )
                        .text("text")
                        .build()
                )
            )
        assertThat(rawContentBlockStartEvent.index()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rawContentBlockStartEvent =
            RawContentBlockStartEvent.builder()
                .contentBlock(
                    TextBlock.builder()
                        .addCitation(
                            CitationCharLocation.builder()
                                .citedText("The grass is green. The sky is blue.")
                                .documentIndex(0L)
                                .documentTitle("My Document")
                                .endCharIndex(0L)
                                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                                .startCharIndex(0L)
                                .build()
                        )
                        .text("text")
                        .build()
                )
                .index(0L)
                .build()

        val roundtrippedRawContentBlockStartEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rawContentBlockStartEvent),
                jacksonTypeRef<RawContentBlockStartEvent>(),
            )

        assertThat(roundtrippedRawContentBlockStartEvent).isEqualTo(rawContentBlockStartEvent)
    }
}
