// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Base64PdfSourceTest {

    @Test
    fun create() {
        val base64PdfSource = Base64PdfSource.builder().data("U3RhaW5sZXNzIHJvY2tz").build()

        assertThat(base64PdfSource.data()).isEqualTo("U3RhaW5sZXNzIHJvY2tz")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val base64PdfSource = Base64PdfSource.builder().data("U3RhaW5sZXNzIHJvY2tz").build()

        val roundtrippedBase64PdfSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(base64PdfSource),
                jacksonTypeRef<Base64PdfSource>(),
            )

        assertThat(roundtrippedBase64PdfSource).isEqualTo(base64PdfSource)
    }
}
