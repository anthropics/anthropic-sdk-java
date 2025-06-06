// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRequestDocumentBlockTest {

    @Test
    fun create() {
        val betaRequestDocumentBlock =
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

        assertThat(betaRequestDocumentBlock.source())
            .isEqualTo(
                BetaRequestDocumentBlock.Source.ofBase64(
                    BetaBase64PdfSource.builder().data("U3RhaW5sZXNzIHJvY2tz").build()
                )
            )
        assertThat(betaRequestDocumentBlock.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaRequestDocumentBlock.citations())
            .contains(BetaCitationsConfigParam.builder().enabled(true).build())
        assertThat(betaRequestDocumentBlock.context()).contains("x")
        assertThat(betaRequestDocumentBlock.title()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRequestDocumentBlock =
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

        val roundtrippedBetaRequestDocumentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRequestDocumentBlock),
                jacksonTypeRef<BetaRequestDocumentBlock>(),
            )

        assertThat(roundtrippedBetaRequestDocumentBlock).isEqualTo(betaRequestDocumentBlock)
    }
}
