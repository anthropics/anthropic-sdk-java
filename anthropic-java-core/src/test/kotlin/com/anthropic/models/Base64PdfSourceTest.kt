// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Base64PdfSourceTest {

    @Test
    fun createBase64PdfSource() {
        val base64PdfSource = Base64PdfSource.builder().data("U3RhaW5sZXNzIHJvY2tz").build()
        assertThat(base64PdfSource).isNotNull
        assertThat(base64PdfSource.data()).isEqualTo("U3RhaW5sZXNzIHJvY2tz")
    }
}
