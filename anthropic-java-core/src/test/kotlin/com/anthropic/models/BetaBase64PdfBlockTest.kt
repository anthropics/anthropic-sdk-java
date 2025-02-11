// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaBase64PdfBlockTest {

    @Test
    fun createBetaBase64PdfBlock() {
        val betaBase64PdfBlock =
            BetaBase64PdfBlock.builder()
                .betaBase64PdfSource("U3RhaW5sZXNzIHJvY2tz")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .context("x")
                .title("x")
                .build()
        assertThat(betaBase64PdfBlock).isNotNull
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
}
