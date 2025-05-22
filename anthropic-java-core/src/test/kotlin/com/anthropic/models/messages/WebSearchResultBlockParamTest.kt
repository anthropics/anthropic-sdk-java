// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebSearchResultBlockParamTest {

    @Test
    fun create() {
        val webSearchResultBlockParam =
            WebSearchResultBlockParam.builder()
                .encryptedContent("encrypted_content")
                .title("title")
                .url("url")
                .pageAge("page_age")
                .build()

        assertThat(webSearchResultBlockParam.encryptedContent()).isEqualTo("encrypted_content")
        assertThat(webSearchResultBlockParam.title()).isEqualTo("title")
        assertThat(webSearchResultBlockParam.url()).isEqualTo("url")
        assertThat(webSearchResultBlockParam.pageAge()).contains("page_age")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchResultBlockParam =
            WebSearchResultBlockParam.builder()
                .encryptedContent("encrypted_content")
                .title("title")
                .url("url")
                .pageAge("page_age")
                .build()

        val roundtrippedWebSearchResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchResultBlockParam),
                jacksonTypeRef<WebSearchResultBlockParam>(),
            )

        assertThat(roundtrippedWebSearchResultBlockParam).isEqualTo(webSearchResultBlockParam)
    }
}
