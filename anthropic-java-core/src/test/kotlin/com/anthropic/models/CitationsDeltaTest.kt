// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CitationsDeltaTest {

    @Test
    fun createCitationsDelta() {
        val citationsDelta =
            CitationsDelta.builder()
                .citation(
                    CitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .build()
        assertThat(citationsDelta).isNotNull
        assertThat(citationsDelta.citation())
            .isEqualTo(
                CitationsDelta.Citation.ofCharLocation(
                    CitationCharLocation.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
            )
    }
}
