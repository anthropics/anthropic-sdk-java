// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationWebSearchResultLocationParamTest {

    @Test
    fun create() {
        val citationWebSearchResultLocationParam =
            CitationWebSearchResultLocationParam.builder()
                .citedText("cited_text")
                .encryptedIndex("encrypted_index")
                .title("x")
                .url("x")
                .build()

        assertThat(citationWebSearchResultLocationParam.citedText()).isEqualTo("cited_text")
        assertThat(citationWebSearchResultLocationParam.encryptedIndex())
            .isEqualTo("encrypted_index")
        assertThat(citationWebSearchResultLocationParam.title()).contains("x")
        assertThat(citationWebSearchResultLocationParam.url()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationWebSearchResultLocationParam =
            CitationWebSearchResultLocationParam.builder()
                .citedText("cited_text")
                .encryptedIndex("encrypted_index")
                .title("x")
                .url("x")
                .build()

        val roundtrippedCitationWebSearchResultLocationParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationWebSearchResultLocationParam),
                jacksonTypeRef<CitationWebSearchResultLocationParam>(),
            )

        assertThat(roundtrippedCitationWebSearchResultLocationParam)
            .isEqualTo(citationWebSearchResultLocationParam)
    }
}
