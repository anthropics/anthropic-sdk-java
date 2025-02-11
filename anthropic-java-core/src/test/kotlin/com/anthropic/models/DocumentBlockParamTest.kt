// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DocumentBlockParamTest {

    @Test
    fun createDocumentBlockParam() {
        val documentBlockParam =
            DocumentBlockParam.builder()
                .base64PdfSource("U3RhaW5sZXNzIHJvY2tz")
                .cacheControl(CacheControlEphemeral.builder().build())
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .context("x")
                .title("x")
                .build()
        assertThat(documentBlockParam).isNotNull
        assertThat(documentBlockParam.source())
            .isEqualTo(
                DocumentBlockParam.Source.ofBase64Pdf(
                    Base64PdfSource.builder().data("U3RhaW5sZXNzIHJvY2tz").build()
                )
            )
        assertThat(documentBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().build())
        assertThat(documentBlockParam.citations())
            .contains(CitationsConfigParam.builder().enabled(true).build())
        assertThat(documentBlockParam.context()).contains("x")
        assertThat(documentBlockParam.title()).contains("x")
    }
}
