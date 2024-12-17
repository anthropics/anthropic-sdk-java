// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Base64PdfSourceTest {

    @Test
    fun createBase64PdfSource() {
        val base64PdfSource =
            Base64PdfSource.builder()
                .data("U3RhaW5sZXNzIHJvY2tz")
                .mediaType(Base64PdfSource.MediaType.APPLICATION_PDF)
                .type(Base64PdfSource.Type.BASE64)
                .build()
        assertThat(base64PdfSource).isNotNull
        assertThat(base64PdfSource.data()).isEqualTo("U3RhaW5sZXNzIHJvY2tz")
        assertThat(base64PdfSource.mediaType()).isEqualTo(Base64PdfSource.MediaType.APPLICATION_PDF)
        assertThat(base64PdfSource.type()).isEqualTo(Base64PdfSource.Type.BASE64)
    }
}
