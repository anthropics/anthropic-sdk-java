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
                .build()
        assertThat(documentBlockParam).isNotNull
        assertThat(documentBlockParam.source())
            .isEqualTo(
                Base64PdfSource.builder()
                    .data("U3RhaW5sZXNzIHJvY2tz")
                    .mediaType(Base64PdfSource.MediaType.APPLICATION_PDF)
                    .type(Base64PdfSource.Type.BASE64)
                    .build()
            )
        assertThat(documentBlockParam.type()).isEqualTo(DocumentBlockParam.Type.DOCUMENT)
        assertThat(documentBlockParam.cacheControl())
            .contains(
                CacheControlEphemeral.builder().type(CacheControlEphemeral.Type.EPHEMERAL).build()
            )
    }
}
