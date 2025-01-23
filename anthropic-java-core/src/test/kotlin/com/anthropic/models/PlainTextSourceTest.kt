// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlainTextSourceTest {

    @Test
    fun createPlainTextSource() {
        val plainTextSource =
            PlainTextSource.builder()
                .data("data")
                .mediaType(PlainTextSource.MediaType.TEXT_PLAIN)
                .type(PlainTextSource.Type.TEXT)
                .build()
        assertThat(plainTextSource).isNotNull
        assertThat(plainTextSource.data()).isEqualTo("data")
        assertThat(plainTextSource.mediaType()).isEqualTo(PlainTextSource.MediaType.TEXT_PLAIN)
        assertThat(plainTextSource.type()).isEqualTo(PlainTextSource.Type.TEXT)
    }
}
