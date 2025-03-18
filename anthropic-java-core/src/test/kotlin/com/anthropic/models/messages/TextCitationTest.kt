// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextCitationTest {

    @Test
    fun ofCitationCharLocation() {
        val citationCharLocation =
            CitationCharLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val textCitation = TextCitation.ofCitationCharLocation(citationCharLocation)

        assertThat(textCitation.citationCharLocation()).contains(citationCharLocation)
        assertThat(textCitation.citationPageLocation()).isEmpty
        assertThat(textCitation.citationContentBlockLocation()).isEmpty
    }

    @Test
    fun ofCitationPageLocation() {
        val citationPageLocation =
            CitationPageLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val textCitation = TextCitation.ofCitationPageLocation(citationPageLocation)

        assertThat(textCitation.citationCharLocation()).isEmpty
        assertThat(textCitation.citationPageLocation()).contains(citationPageLocation)
        assertThat(textCitation.citationContentBlockLocation()).isEmpty
    }

    @Test
    fun ofCitationContentBlockLocation() {
        val citationContentBlockLocation =
            CitationContentBlockLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val textCitation = TextCitation.ofCitationContentBlockLocation(citationContentBlockLocation)

        assertThat(textCitation.citationCharLocation()).isEmpty
        assertThat(textCitation.citationPageLocation()).isEmpty
        assertThat(textCitation.citationContentBlockLocation())
            .contains(citationContentBlockLocation)
    }
}
