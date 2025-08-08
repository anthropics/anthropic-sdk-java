// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationsSearchResultLocationTest {

    @Test
    fun create() {
        val citationsSearchResultLocation =
            CitationsSearchResultLocation.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        assertThat(citationsSearchResultLocation.citedText()).isEqualTo("cited_text")
        assertThat(citationsSearchResultLocation.endBlockIndex()).isEqualTo(0L)
        assertThat(citationsSearchResultLocation.searchResultIndex()).isEqualTo(0L)
        assertThat(citationsSearchResultLocation.source()).isEqualTo("source")
        assertThat(citationsSearchResultLocation.startBlockIndex()).isEqualTo(0L)
        assertThat(citationsSearchResultLocation.title()).contains("title")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationsSearchResultLocation =
            CitationsSearchResultLocation.builder()
                .citedText("cited_text")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        val roundtrippedCitationsSearchResultLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationsSearchResultLocation),
                jacksonTypeRef<CitationsSearchResultLocation>(),
            )

        assertThat(roundtrippedCitationsSearchResultLocation)
            .isEqualTo(citationsSearchResultLocation)
    }
}
