// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBase64PdfBlockTest {

    @Test
    fun create() {
        val betaBase64PdfBlock =
            BetaBase64PdfBlock.builder()
                .betaBase64PdfSource("U3RhaW5sZXNzIHJvY2tz")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .context("x")
                .title("x")
                .build()

        assertThat(betaBase64PdfBlock.source())
            .isEqualTo(
                BetaBase64PdfBlock.Source.ofBetaBase64Pdf(
                    BetaBase64PdfSource.builder().data("U3RhaW5sZXNzIHJvY2tz").build()
                )
            )
        assertThat(betaBase64PdfBlock.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
        assertThat(betaBase64PdfBlock.citations())
            .contains(BetaCitationsConfigParam.builder().enabled(true).build())
        assertThat(betaBase64PdfBlock.context()).contains("x")
        assertThat(betaBase64PdfBlock.title()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBase64PdfBlock =
            BetaBase64PdfBlock.builder()
                .betaBase64PdfSource("U3RhaW5sZXNzIHJvY2tz")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .context("x")
                .title("x")
                .build()

        val roundtrippedBetaBase64PdfBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBase64PdfBlock),
                jacksonTypeRef<BetaBase64PdfBlock>(),
            )

        assertThat(roundtrippedBetaBase64PdfBlock).isEqualTo(betaBase64PdfBlock)
    }
}
