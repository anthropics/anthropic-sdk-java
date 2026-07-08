// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextBlockTest {

    @Test
    fun create() {
        val textBlock =
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

        assertThat(textBlock.citations().getOrNull())
            .containsExactly(
                TextCitation.ofCharLocation(
                    CitationCharLocation.builder()
                        .citedText("The grass is green. The sky is blue.")
                        .documentIndex(0L)
                        .documentTitle("My Document")
                        .endCharIndex(0L)
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .startCharIndex(0L)
                        .build()
                )
            )
        assertThat(textBlock.text()).isEqualTo("text")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textBlock =
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

        val roundtrippedTextBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textBlock),
                jacksonTypeRef<TextBlock>(),
            )

        assertThat(roundtrippedTextBlock).isEqualTo(textBlock)
    }
}
