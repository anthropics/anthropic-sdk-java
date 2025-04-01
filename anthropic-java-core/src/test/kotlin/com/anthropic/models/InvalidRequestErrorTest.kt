// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InvalidRequestErrorTest {

    @Test
    fun create() {
        val invalidRequestError = InvalidRequestError.builder().message("message").build()

        assertThat(invalidRequestError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val invalidRequestError = InvalidRequestError.builder().message("message").build()

        val roundtrippedInvalidRequestError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(invalidRequestError),
                jacksonTypeRef<InvalidRequestError>(),
            )

        assertThat(roundtrippedInvalidRequestError).isEqualTo(invalidRequestError)
    }
}
