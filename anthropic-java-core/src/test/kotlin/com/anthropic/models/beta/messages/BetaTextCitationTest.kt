// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextCitationTest {

    @Test
    fun ofCitationCharLocation() {
        val citationCharLocation =
            BetaCitationCharLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val betaTextCitation = BetaTextCitation.ofCitationCharLocation(citationCharLocation)

        assertThat(betaTextCitation.citationCharLocation()).contains(citationCharLocation)
        assertThat(betaTextCitation.citationPageLocation()).isEmpty
        assertThat(betaTextCitation.citationContentBlockLocation()).isEmpty
    }

    @Test
    fun ofCitationPageLocation() {
        val citationPageLocation =
            BetaCitationPageLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val betaTextCitation = BetaTextCitation.ofCitationPageLocation(citationPageLocation)

        assertThat(betaTextCitation.citationCharLocation()).isEmpty
        assertThat(betaTextCitation.citationPageLocation()).contains(citationPageLocation)
        assertThat(betaTextCitation.citationContentBlockLocation()).isEmpty
    }

    @Test
    fun ofCitationContentBlockLocation() {
        val citationContentBlockLocation =
            BetaCitationContentBlockLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val betaTextCitation =
            BetaTextCitation.ofCitationContentBlockLocation(citationContentBlockLocation)

        assertThat(betaTextCitation.citationCharLocation()).isEmpty
        assertThat(betaTextCitation.citationPageLocation()).isEmpty
        assertThat(betaTextCitation.citationContentBlockLocation())
            .contains(citationContentBlockLocation)
    }
}
