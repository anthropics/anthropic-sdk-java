// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ErrorResponseTest {

    @Test
    fun createErrorResponse() {
        val errorResponse = ErrorResponse.builder().invalidRequestErrorError("message").build()
        assertThat(errorResponse).isNotNull
        assertThat(errorResponse.error())
            .isEqualTo(
                ErrorObject.ofInvalidRequestError(
                    InvalidRequestError.builder().message("message").build()
                )
            )
    }
}
