// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationSearchResultLocationTest {

    @Test
    fun create() {
        val betaCitationSearchResultLocation =
            BetaCitationSearchResultLocation.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        assertThat(betaCitationSearchResultLocation.citedText()).isEqualTo("cited_text")
        assertThat(betaCitationSearchResultLocation.endBlockIndex()).isEqualTo(0L)
        assertThat(betaCitationSearchResultLocation.searchResultIndex()).isEqualTo(0L)
        assertThat(betaCitationSearchResultLocation.source()).isEqualTo("source")
        assertThat(betaCitationSearchResultLocation.startBlockIndex()).isEqualTo(0L)
        assertThat(betaCitationSearchResultLocation.title()).contains("title")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCitationSearchResultLocation =
            BetaCitationSearchResultLocation.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        val roundtrippedBetaCitationSearchResultLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCitationSearchResultLocation),
                jacksonTypeRef<BetaCitationSearchResultLocation>(),
            )

        assertThat(roundtrippedBetaCitationSearchResultLocation)
            .isEqualTo(betaCitationSearchResultLocation)
    }
}
