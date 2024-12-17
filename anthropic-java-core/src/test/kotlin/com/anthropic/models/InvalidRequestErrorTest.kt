// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InvalidRequestErrorTest {

    @Test
    fun createInvalidRequestError() {
        val invalidRequestError =
            InvalidRequestError.builder()
                .message("message")
                .type(InvalidRequestError.Type.INVALID_REQUEST_ERROR)
                .build()
        assertThat(invalidRequestError).isNotNull
        assertThat(invalidRequestError.message()).isEqualTo("message")
        assertThat(invalidRequestError.type())
            .isEqualTo(InvalidRequestError.Type.INVALID_REQUEST_ERROR)
    }
}
