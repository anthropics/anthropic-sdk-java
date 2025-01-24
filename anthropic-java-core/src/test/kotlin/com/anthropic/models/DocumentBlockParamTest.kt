// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DocumentBlockParamTest {

    @Test
    fun createDocumentBlockParam() {
        val documentBlockParam =
            DocumentBlockParam.builder()
                .source(
                    Base64PdfSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(Base64PdfSource.MediaType.APPLICATION_PDF)
                        .type(Base64PdfSource.Type.BASE64)
                        .build()
                )
                .type(DocumentBlockParam.Type.DOCUMENT)
                .cacheControl(
                    CacheControlEphemeral.builder()
                        .type(CacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .context("x")
                .title("x")
                .build()
        assertThat(documentBlockParam).isNotNull
        assertThat(documentBlockParam.source())
            .isEqualTo(
                DocumentBlockParam.Source.ofBase64Pdf(
                    Base64PdfSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(Base64PdfSource.MediaType.APPLICATION_PDF)
                        .type(Base64PdfSource.Type.BASE64)
                        .build()
                )
            )
        assertThat(documentBlockParam.type()).isEqualTo(DocumentBlockParam.Type.DOCUMENT)
        assertThat(documentBlockParam.cacheControl())
            .contains(
                CacheControlEphemeral.builder().type(CacheControlEphemeral.Type.EPHEMERAL).build()
            )
        assertThat(documentBlockParam.citations())
            .contains(CitationsConfigParam.builder().enabled(true).build())
        assertThat(documentBlockParam.context()).contains("x")
        assertThat(documentBlockParam.title()).contains("x")
    }
}
