// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationsWebSearchResultLocationTest {

    @Test
    fun create() {
        val citationsWebSearchResultLocation =
            CitationsWebSearchResultLocation.builder()
                .citedText("cited_text")
                .encryptedIndex("encrypted_index")
                .title("title")
                .url("url")
                .build()

        assertThat(citationsWebSearchResultLocation.citedText()).isEqualTo("cited_text")
        assertThat(citationsWebSearchResultLocation.encryptedIndex()).isEqualTo("encrypted_index")
        assertThat(citationsWebSearchResultLocation.title()).contains("title")
        assertThat(citationsWebSearchResultLocation.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationsWebSearchResultLocation =
            CitationsWebSearchResultLocation.builder()
                .citedText("cited_text")
                .encryptedIndex("encrypted_index")
                .title("title")
                .url("url")
                .build()

        val roundtrippedCitationsWebSearchResultLocation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationsWebSearchResultLocation),
                jacksonTypeRef<CitationsWebSearchResultLocation>(),
            )

        assertThat(roundtrippedCitationsWebSearchResultLocation)
            .isEqualTo(citationsWebSearchResultLocation)
    }
}
