// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SearchResultBlockParamTest {

    @Test
    fun create() {
        val searchResultBlockParam =
            SearchResultBlockParam.builder()
                .addContent(
                    TextBlockParam.builder()
                        .text("x")
                        .cacheControl(CacheControlEphemeral.builder().build())
                        .addCitation(
                            CitationCharLocationParam.builder()
                                .citedText("cited_text")
                                .documentIndex(0L)
                                .documentTitle("x")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .build()
                        )
                        .build()
                )
                .source("source")
                .title("title")
                .cacheControl(CacheControlEphemeral.builder().build())
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .build()

        assertThat(searchResultBlockParam.content())
            .containsExactly(
                TextBlockParam.builder()
                    .text("x")
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .addCitation(
                        CitationCharLocationParam.builder()
                            .citedText("cited_text")
                            .documentIndex(0L)
                            .documentTitle("x")
                            .endCharIndex(0L)
                            .startCharIndex(0L)
                            .build()
                    )
                    .build()
            )
        assertThat(searchResultBlockParam.source()).isEqualTo("source")
        assertThat(searchResultBlockParam.title()).isEqualTo("title")
        assertThat(searchResultBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().build())
        assertThat(searchResultBlockParam.citations())
            .contains(CitationsConfigParam.builder().enabled(true).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val searchResultBlockParam =
            SearchResultBlockParam.builder()
                .addContent(
                    TextBlockParam.builder()
                        .text("x")
                        .cacheControl(CacheControlEphemeral.builder().build())
                        .addCitation(
                            CitationCharLocationParam.builder()
                                .citedText("cited_text")
                                .documentIndex(0L)
                                .documentTitle("x")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .build()
                        )
                        .build()
                )
                .source("source")
                .title("title")
                .cacheControl(CacheControlEphemeral.builder().build())
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .build()

        val roundtrippedSearchResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(searchResultBlockParam),
                jacksonTypeRef<SearchResultBlockParam>(),
            )

        assertThat(roundtrippedSearchResultBlockParam).isEqualTo(searchResultBlockParam)
    }
}
