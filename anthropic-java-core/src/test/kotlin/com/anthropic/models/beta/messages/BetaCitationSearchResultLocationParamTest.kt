// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationSearchResultLocationParamTest {

    @Test
    fun create() {
        val betaCitationSearchResultLocationParam =
            BetaCitationSearchResultLocationParam.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        assertThat(betaCitationSearchResultLocationParam.citedText()).isEqualTo("cited_text")
        assertThat(betaCitationSearchResultLocationParam.endBlockIndex()).isEqualTo(0L)
        assertThat(betaCitationSearchResultLocationParam.searchResultIndex()).isEqualTo(0L)
        assertThat(betaCitationSearchResultLocationParam.source()).isEqualTo("source")
        assertThat(betaCitationSearchResultLocationParam.startBlockIndex()).isEqualTo(0L)
        assertThat(betaCitationSearchResultLocationParam.title()).contains("title")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCitationSearchResultLocationParam =
            BetaCitationSearchResultLocationParam.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        val roundtrippedBetaCitationSearchResultLocationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCitationSearchResultLocationParam),
                jacksonTypeRef<BetaCitationSearchResultLocationParam>(),
            )

        assertThat(roundtrippedBetaCitationSearchResultLocationParam)
            .isEqualTo(betaCitationSearchResultLocationParam)
    }
}
