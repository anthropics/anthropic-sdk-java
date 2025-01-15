// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ErrorResponseTest {

    @Test
    fun createErrorResponse() {
        val errorResponse =
            ErrorResponse.builder()
                .error(
                    InvalidRequestError.builder()
                        .message("message")
                        .type(InvalidRequestError.Type.INVALID_REQUEST_ERROR)
                        .build()
                )
                .type(ErrorResponse.Type.ERROR)
                .build()
        assertThat(errorResponse).isNotNull
        assertThat(errorResponse.error())
            .isEqualTo(
                ErrorObject.ofInvalidRequestError(
                    InvalidRequestError.builder()
                        .message("message")
                        .type(InvalidRequestError.Type.INVALID_REQUEST_ERROR)
                        .build()
                )
            )
        assertThat(errorResponse.type()).isEqualTo(ErrorResponse.Type.ERROR)
    }
}
