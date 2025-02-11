// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CitationCharLocationTest {

    @Test
    fun createCitationCharLocation() {
        val citationCharLocation =
            CitationCharLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()
        assertThat(citationCharLocation).isNotNull
        assertThat(citationCharLocation.citedText()).isEqualTo("cited_text")
        assertThat(citationCharLocation.documentIndex()).isEqualTo(0L)
        assertThat(citationCharLocation.documentTitle()).contains("document_title")
        assertThat(citationCharLocation.endCharIndex()).isEqualTo(0L)
        assertThat(citationCharLocation.startCharIndex()).isEqualTo(0L)
    }
}
