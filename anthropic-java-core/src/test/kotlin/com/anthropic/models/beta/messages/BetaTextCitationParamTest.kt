// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextCitationParamTest {

    @Test
    fun ofCitationCharLocation() {
        val citationCharLocation =
            BetaCitationCharLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val betaTextCitationParam =
            BetaTextCitationParam.ofCitationCharLocation(citationCharLocation)

        assertThat(betaTextCitationParam.citationCharLocation()).contains(citationCharLocation)
        assertThat(betaTextCitationParam.citationPageLocation()).isEmpty
        assertThat(betaTextCitationParam.citationContentBlockLocation()).isEmpty
    }

    @Test
    fun ofCitationPageLocation() {
        val citationPageLocation =
            BetaCitationPageLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val betaTextCitationParam =
            BetaTextCitationParam.ofCitationPageLocation(citationPageLocation)

        assertThat(betaTextCitationParam.citationCharLocation()).isEmpty
        assertThat(betaTextCitationParam.citationPageLocation()).contains(citationPageLocation)
        assertThat(betaTextCitationParam.citationContentBlockLocation()).isEmpty
    }

    @Test
    fun ofCitationContentBlockLocation() {
        val citationContentBlockLocation =
            BetaCitationContentBlockLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endBlockIndex(0L)
                .startBlockIndex(0L)
                .build()

        val betaTextCitationParam =
            BetaTextCitationParam.ofCitationContentBlockLocation(citationContentBlockLocation)

        assertThat(betaTextCitationParam.citationCharLocation()).isEmpty
        assertThat(betaTextCitationParam.citationPageLocation()).isEmpty
        assertThat(betaTextCitationParam.citationContentBlockLocation())
            .contains(citationContentBlockLocation)
    }
}
