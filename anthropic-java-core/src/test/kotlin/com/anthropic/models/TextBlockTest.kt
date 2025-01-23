// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TextBlockTest {

    @Test
    fun createTextBlock() {
        val textBlock =
            TextBlock.builder()
                .addCitation(
                    CitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .type(CitationCharLocation.Type.CHAR_LOCATION)
                        .build()
                )
                .text("text")
                .type(TextBlock.Type.TEXT)
                .build()
        assertThat(textBlock).isNotNull
        assertThat(textBlock.citations().get())
            .containsExactly(
                TextCitation.ofCitationCharLocation(
                    CitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .type(CitationCharLocation.Type.CHAR_LOCATION)
                        .build()
                )
            )
        assertThat(textBlock.text()).isEqualTo("text")
        assertThat(textBlock.type()).isEqualTo(TextBlock.Type.TEXT)
    }
}
