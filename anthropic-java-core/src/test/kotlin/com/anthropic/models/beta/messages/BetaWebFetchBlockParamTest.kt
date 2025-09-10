// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchBlockParamTest {

    @Test
    fun create() {
        val betaWebFetchBlockParam =
            BetaWebFetchBlockParam.builder()
                .content(
                    BetaRequestDocumentBlock.builder()
                        .base64Source("U3RhaW5sZXNzIHJvY2tz")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                        .context("x")
                        .title("x")
                        .build()
                )
                .url("url")
                .retrievedAt("retrieved_at")
                .build()

        assertThat(betaWebFetchBlockParam.content())
            .isEqualTo(
                BetaRequestDocumentBlock.builder()
                    .base64Source("U3RhaW5sZXNzIHJvY2tz")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                    .context("x")
                    .title("x")
                    .build()
            )
        assertThat(betaWebFetchBlockParam.url()).isEqualTo("url")
        assertThat(betaWebFetchBlockParam.retrievedAt()).contains("retrieved_at")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchBlockParam =
            BetaWebFetchBlockParam.builder()
                .content(
                    BetaRequestDocumentBlock.builder()
                        .base64Source("U3RhaW5sZXNzIHJvY2tz")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                        .context("x")
                        .title("x")
                        .build()
                )
                .url("url")
                .retrievedAt("retrieved_at")
                .build()

        val roundtrippedBetaWebFetchBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchBlockParam),
                jacksonTypeRef<BetaWebFetchBlockParam>(),
            )

        assertThat(roundtrippedBetaWebFetchBlockParam).isEqualTo(betaWebFetchBlockParam)
    }
}
