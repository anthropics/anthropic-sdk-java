// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebSearchToolResultBlockParamTest {

    @Test
    fun create() {
        val webSearchToolResultBlockParam =
            WebSearchToolResultBlockParam.builder()
                .contentOfItem(
                    listOf(
                        WebSearchResultBlockParam.builder()
                            .encryptedContent("encrypted_content")
                            .title("title")
                            .url("url")
                            .pageAge("page_age")
                            .build()
                    )
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        assertThat(webSearchToolResultBlockParam.content())
            .isEqualTo(
                WebSearchToolResultBlockParamContent.ofItem(
                    listOf(
                        WebSearchResultBlockParam.builder()
                            .encryptedContent("encrypted_content")
                            .title("title")
                            .url("url")
                            .pageAge("page_age")
                            .build()
                    )
                )
            )
        assertThat(webSearchToolResultBlockParam.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(webSearchToolResultBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchToolResultBlockParam =
            WebSearchToolResultBlockParam.builder()
                .contentOfItem(
                    listOf(
                        WebSearchResultBlockParam.builder()
                            .encryptedContent("encrypted_content")
                            .title("title")
                            .url("url")
                            .pageAge("page_age")
                            .build()
                    )
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val roundtrippedWebSearchToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchToolResultBlockParam),
                jacksonTypeRef<WebSearchToolResultBlockParam>(),
            )

        assertThat(roundtrippedWebSearchToolResultBlockParam)
            .isEqualTo(webSearchToolResultBlockParam)
    }
}
