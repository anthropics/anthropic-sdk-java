// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSearchResultLocationCitationTest {

    @Test
    fun create() {
        val betaSearchResultLocationCitation =
            BetaSearchResultLocationCitation.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        assertThat(betaSearchResultLocationCitation.citedText()).isEqualTo("cited_text")
        assertThat(betaSearchResultLocationCitation.endBlockIndex()).isEqualTo(0L)
        assertThat(betaSearchResultLocationCitation.searchResultIndex()).isEqualTo(0L)
        assertThat(betaSearchResultLocationCitation.source()).isEqualTo("source")
        assertThat(betaSearchResultLocationCitation.startBlockIndex()).isEqualTo(0L)
        assertThat(betaSearchResultLocationCitation.title()).contains("title")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSearchResultLocationCitation =
            BetaSearchResultLocationCitation.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        val roundtrippedBetaSearchResultLocationCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSearchResultLocationCitation),
                jacksonTypeRef<BetaSearchResultLocationCitation>(),
            )

        assertThat(roundtrippedBetaSearchResultLocationCitation)
            .isEqualTo(betaSearchResultLocationCitation)
    }
}
