// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaCitationCharLocationTest {

    @Test
    fun createBetaCitationCharLocation() {
        val betaCitationCharLocation =
            BetaCitationCharLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()
        assertThat(betaCitationCharLocation).isNotNull
        assertThat(betaCitationCharLocation.citedText()).isEqualTo("cited_text")
        assertThat(betaCitationCharLocation.documentIndex()).isEqualTo(0L)
        assertThat(betaCitationCharLocation.documentTitle()).contains("document_title")
        assertThat(betaCitationCharLocation.endCharIndex()).isEqualTo(0L)
        assertThat(betaCitationCharLocation.startCharIndex()).isEqualTo(0L)
    }
}
