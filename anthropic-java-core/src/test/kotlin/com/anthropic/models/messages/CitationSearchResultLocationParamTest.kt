// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationSearchResultLocationParamTest {

    @Test
    fun create() {
        val citationSearchResultLocationParam =
            CitationSearchResultLocationParam.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        assertThat(citationSearchResultLocationParam.citedText()).isEqualTo("cited_text")
        assertThat(citationSearchResultLocationParam.endBlockIndex()).isEqualTo(0L)
        assertThat(citationSearchResultLocationParam.searchResultIndex()).isEqualTo(0L)
        assertThat(citationSearchResultLocationParam.source()).isEqualTo("source")
        assertThat(citationSearchResultLocationParam.startBlockIndex()).isEqualTo(0L)
        assertThat(citationSearchResultLocationParam.title()).contains("title")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationSearchResultLocationParam =
            CitationSearchResultLocationParam.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        val roundtrippedCitationSearchResultLocationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationSearchResultLocationParam),
                jacksonTypeRef<CitationSearchResultLocationParam>(),
            )

        assertThat(roundtrippedCitationSearchResultLocationParam)
            .isEqualTo(citationSearchResultLocationParam)
    }
}
