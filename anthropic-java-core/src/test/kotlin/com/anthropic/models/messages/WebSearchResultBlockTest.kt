// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebSearchResultBlockTest {

    @Test
    fun create() {
        val webSearchResultBlock =
            WebSearchResultBlock.builder()
                .encryptedContent("encrypted_content")
                .pageAge("page_age")
                .title("title")
                .url("url")
                .build()

        assertThat(webSearchResultBlock.encryptedContent()).isEqualTo("encrypted_content")
        assertThat(webSearchResultBlock.pageAge()).contains("page_age")
        assertThat(webSearchResultBlock.title()).isEqualTo("title")
        assertThat(webSearchResultBlock.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchResultBlock =
            WebSearchResultBlock.builder()
                .encryptedContent("encrypted_content")
                .pageAge("page_age")
                .title("title")
                .url("url")
                .build()

        val roundtrippedWebSearchResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchResultBlock),
                jacksonTypeRef<WebSearchResultBlock>(),
            )

        assertThat(roundtrippedWebSearchResultBlock).isEqualTo(webSearchResultBlock)
    }
}
