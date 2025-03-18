// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextBlockTest {

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
                        .build()
                )
                .text("text")
                .build()
        assertThat(textBlock).isNotNull
        assertThat(textBlock.citations().getOrNull())
            .containsExactly(
                TextCitation.ofCitationCharLocation(
                    CitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
            )
        assertThat(textBlock.text()).isEqualTo("text")
    }
}
