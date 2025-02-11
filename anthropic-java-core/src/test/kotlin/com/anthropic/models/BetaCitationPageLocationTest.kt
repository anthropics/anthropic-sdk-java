// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaCitationPageLocationTest {

    @Test
    fun createBetaCitationPageLocation() {
        val betaCitationPageLocation =
            BetaCitationPageLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()
        assertThat(betaCitationPageLocation).isNotNull
        assertThat(betaCitationPageLocation.citedText()).isEqualTo("cited_text")
        assertThat(betaCitationPageLocation.documentIndex()).isEqualTo(0L)
        assertThat(betaCitationPageLocation.documentTitle()).contains("document_title")
        assertThat(betaCitationPageLocation.endPageNumber()).isEqualTo(0L)
        assertThat(betaCitationPageLocation.startPageNumber()).isEqualTo(1L)
    }
}
