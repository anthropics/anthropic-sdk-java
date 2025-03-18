// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Base64PdfSourceTest {

    @Test
    fun create() {
        val base64PdfSource = Base64PdfSource.builder().data("U3RhaW5sZXNzIHJvY2tz").build()

        assertThat(base64PdfSource.data()).isEqualTo("U3RhaW5sZXNzIHJvY2tz")
    }
}
