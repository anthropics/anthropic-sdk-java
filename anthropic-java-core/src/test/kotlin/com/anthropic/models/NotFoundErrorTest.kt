// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NotFoundErrorTest {

    @Test
    fun createNotFoundError() {
        val notFoundError =
            NotFoundError.builder()
                .message("message")
                .type(NotFoundError.Type.NOT_FOUND_ERROR)
                .build()
        assertThat(notFoundError).isNotNull
        assertThat(notFoundError.message()).isEqualTo("message")
        assertThat(notFoundError.type()).isEqualTo(NotFoundError.Type.NOT_FOUND_ERROR)
    }
}
