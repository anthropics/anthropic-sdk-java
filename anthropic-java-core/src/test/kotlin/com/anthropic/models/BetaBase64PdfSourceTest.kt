// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaBase64PdfSourceTest {

    @Test
    fun createBetaBase64PdfSource() {
        val betaBase64PdfSource =
            BetaBase64PdfSource.builder()
                .data("U3RhaW5sZXNzIHJvY2tz")
                .mediaType(BetaBase64PdfSource.MediaType.APPLICATION_PDF)
                .type(BetaBase64PdfSource.Type.BASE64)
                .build()
        assertThat(betaBase64PdfSource).isNotNull
        assertThat(betaBase64PdfSource.data()).isEqualTo("U3RhaW5sZXNzIHJvY2tz")
        assertThat(betaBase64PdfSource.mediaType())
            .isEqualTo(BetaBase64PdfSource.MediaType.APPLICATION_PDF)
        assertThat(betaBase64PdfSource.type()).isEqualTo(BetaBase64PdfSource.Type.BASE64)
    }
}
