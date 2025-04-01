// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ErrorResponseTest {

    @Test
    fun create() {
        val errorResponse = ErrorResponse.builder().invalidRequestErrorError("message").build()

        assertThat(errorResponse.error())
            .isEqualTo(
                ErrorObject.ofInvalidRequestError(
                    InvalidRequestError.builder().message("message").build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val errorResponse = ErrorResponse.builder().invalidRequestErrorError("message").build()

        val roundtrippedErrorResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorResponse),
                jacksonTypeRef<ErrorResponse>(),
            )

        assertThat(roundtrippedErrorResponse).isEqualTo(errorResponse)
    }
}
