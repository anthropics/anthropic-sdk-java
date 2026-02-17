// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchBlockParamTest {

    @Test
    fun create() {
        val webFetchBlockParam =
            WebFetchBlockParam.builder()
                .content(
                    DocumentBlockParam.builder()
                        .base64Source("U3RhaW5sZXNzIHJvY2tz")
                        .cacheControl(
                            CacheControlEphemeral.builder()
                                .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .citations(CitationsConfigParam.builder().enabled(true).build())
                        .context("x")
                        .title("x")
                        .build()
                )
                .url("url")
                .retrievedAt("retrieved_at")
                .build()

        assertThat(webFetchBlockParam.content())
            .isEqualTo(
                DocumentBlockParam.builder()
                    .base64Source("U3RhaW5sZXNzIHJvY2tz")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(CitationsConfigParam.builder().enabled(true).build())
                    .context("x")
                    .title("x")
                    .build()
            )
        assertThat(webFetchBlockParam.url()).isEqualTo("url")
        assertThat(webFetchBlockParam.retrievedAt()).contains("retrieved_at")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchBlockParam =
            WebFetchBlockParam.builder()
                .content(
                    DocumentBlockParam.builder()
                        .base64Source("U3RhaW5sZXNzIHJvY2tz")
                        .cacheControl(
                            CacheControlEphemeral.builder()
                                .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .citations(CitationsConfigParam.builder().enabled(true).build())
                        .context("x")
                        .title("x")
                        .build()
                )
                .url("url")
                .retrievedAt("retrieved_at")
                .build()

        val roundtrippedWebFetchBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchBlockParam),
                jacksonTypeRef<WebFetchBlockParam>(),
            )

        assertThat(roundtrippedWebFetchBlockParam).isEqualTo(webFetchBlockParam)
    }
}
