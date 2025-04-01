// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationPageLocationParamTest {

    @Test
    fun create() {
        val citationPageLocationParam =
            CitationPageLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        assertThat(citationPageLocationParam.citedText()).isEqualTo("cited_text")
        assertThat(citationPageLocationParam.documentIndex()).isEqualTo(0L)
        assertThat(citationPageLocationParam.documentTitle()).contains("x")
        assertThat(citationPageLocationParam.endPageNumber()).isEqualTo(0L)
        assertThat(citationPageLocationParam.startPageNumber()).isEqualTo(1L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationPageLocationParam =
            CitationPageLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endPageNumber(0L)
                .startPageNumber(1L)
                .build()

        val roundtrippedCitationPageLocationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationPageLocationParam),
                jacksonTypeRef<CitationPageLocationParam>(),
            )

        assertThat(roundtrippedCitationPageLocationParam).isEqualTo(citationPageLocationParam)
    }
}
