// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaBase64PdfBlockTest {

    @Test
    fun createBetaBase64PdfBlock() {
        val betaBase64PdfBlock =
            BetaBase64PdfBlock.builder()
                .source(
                    BetaBase64PdfSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(BetaBase64PdfSource.MediaType.APPLICATION_PDF)
                        .type(BetaBase64PdfSource.Type.BASE64)
                        .build()
                )
                .type(BetaBase64PdfBlock.Type.DOCUMENT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .build()
        assertThat(betaBase64PdfBlock).isNotNull
        assertThat(betaBase64PdfBlock.source())
            .isEqualTo(
                BetaBase64PdfSource.builder()
                    .data("U3RhaW5sZXNzIHJvY2tz")
                    .mediaType(BetaBase64PdfSource.MediaType.APPLICATION_PDF)
                    .type(BetaBase64PdfSource.Type.BASE64)
                    .build()
            )
        assertThat(betaBase64PdfBlock.type()).isEqualTo(BetaBase64PdfBlock.Type.DOCUMENT)
        assertThat(betaBase64PdfBlock.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                    .build()
            )
    }
}
