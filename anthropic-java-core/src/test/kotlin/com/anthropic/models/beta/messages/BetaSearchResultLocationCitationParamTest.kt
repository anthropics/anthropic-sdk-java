// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSearchResultLocationCitationParamTest {

    @Test
    fun create() {
        val betaSearchResultLocationCitationParam =
            BetaSearchResultLocationCitationParam.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        assertThat(betaSearchResultLocationCitationParam.citedText()).isEqualTo("cited_text")
        assertThat(betaSearchResultLocationCitationParam.endBlockIndex()).isEqualTo(0L)
        assertThat(betaSearchResultLocationCitationParam.searchResultIndex()).isEqualTo(0L)
        assertThat(betaSearchResultLocationCitationParam.source()).isEqualTo("source")
        assertThat(betaSearchResultLocationCitationParam.startBlockIndex()).isEqualTo(0L)
        assertThat(betaSearchResultLocationCitationParam.title()).contains("title")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSearchResultLocationCitationParam =
            BetaSearchResultLocationCitationParam.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        val roundtrippedBetaSearchResultLocationCitationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSearchResultLocationCitationParam),
                jacksonTypeRef<BetaSearchResultLocationCitationParam>(),
            )

        assertThat(roundtrippedBetaSearchResultLocationCitationParam)
            .isEqualTo(betaSearchResultLocationCitationParam)
    }
}
