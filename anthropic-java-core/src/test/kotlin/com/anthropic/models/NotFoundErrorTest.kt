// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NotFoundErrorTest {

    @Test
    fun create() {
        val notFoundError = NotFoundError.builder().message("message").build()

        assertThat(notFoundError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val notFoundError = NotFoundError.builder().message("message").build()

        val roundtrippedNotFoundError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(notFoundError),
                jacksonTypeRef<NotFoundError>(),
            )

        assertThat(roundtrippedNotFoundError).isEqualTo(notFoundError)
    }
}
