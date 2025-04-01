// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationCharLocationParamTest {

    @Test
    fun create() {
        val citationCharLocationParam =
            CitationCharLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        assertThat(citationCharLocationParam.citedText()).isEqualTo("cited_text")
        assertThat(citationCharLocationParam.documentIndex()).isEqualTo(0L)
        assertThat(citationCharLocationParam.documentTitle()).contains("x")
        assertThat(citationCharLocationParam.endCharIndex()).isEqualTo(0L)
        assertThat(citationCharLocationParam.startCharIndex()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationCharLocationParam =
            CitationCharLocationParam.builder()
                .citedText("cited_text")
                .documentIndex(0L)
                .documentTitle("x")
                .endCharIndex(0L)
                .startCharIndex(0L)
                .build()

        val roundtrippedCitationCharLocationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationCharLocationParam),
                jacksonTypeRef<CitationCharLocationParam>(),
            )

        assertThat(roundtrippedCitationCharLocationParam).isEqualTo(citationCharLocationParam)
    }
}
