// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationCharLocationTest {

    @Test
    fun create() {
        val citationCharLocation =
            CitationCharLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        assertThat(citationCharLocation.citedText()).isEqualTo("cited_text")
        assertThat(citationCharLocation.documentIndex()).isEqualTo(0L)
        assertThat(citationCharLocation.documentTitle()).contains("document_title")
        assertThat(citationCharLocation.endCharIndex()).isEqualTo(0L)
        assertThat(citationCharLocation.startCharIndex()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationCharLocation =
            CitationCharLocation.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("document_title")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val roundtrippedCitationCharLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationCharLocation),
                jacksonTypeRef<CitationCharLocation>(),
            )

        assertThat(roundtrippedCitationCharLocation).isEqualTo(citationCharLocation)
    }
}
