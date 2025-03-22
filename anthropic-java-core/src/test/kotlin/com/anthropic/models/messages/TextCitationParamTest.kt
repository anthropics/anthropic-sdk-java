// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextCitationParamTest {

    @Test
    fun ofCitationCharLocation() {
        val citationCharLocation =
            CitationCharLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val textCitationParam = TextCitationParam.ofCitationCharLocation(citationCharLocation)

        assertThat(textCitationParam.citationCharLocation()).contains(citationCharLocation)
        assertThat(textCitationParam.citationPageLocation()).isEmpty
        assertThat(textCitationParam.citationContentBlockLocation()).isEmpty
    }

    @Test
    fun ofCitationPageLocation() {
        val citationPageLocation =
            CitationPageLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val textCitationParam = TextCitationParam.ofCitationPageLocation(citationPageLocation)

        assertThat(textCitationParam.citationCharLocation()).isEmpty
        assertThat(textCitationParam.citationPageLocation()).contains(citationPageLocation)
        assertThat(textCitationParam.citationContentBlockLocation()).isEmpty
    }

    @Test
    fun ofCitationContentBlockLocation() {
        val citationContentBlockLocation =
            CitationContentBlockLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val textCitationParam =
            TextCitationParam.ofCitationContentBlockLocation(citationContentBlockLocation)

        assertThat(textCitationParam.citationCharLocation()).isEmpty
        assertThat(textCitationParam.citationPageLocation()).isEmpty
        assertThat(textCitationParam.citationContentBlockLocation())
            .contains(citationContentBlockLocation)
    }
}
